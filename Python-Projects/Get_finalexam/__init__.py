import smtplib
import time
import requests
import schedule
from email.mime.text import MIMEText
from email.header import Header

session = requests.session()
length = 0
data_dict = {

}
# -------------------------------------------------以下内容需要自己填写-------------------------------------------------
# 个人信息区
username = xxxxxx
password = xxxxx
# 邮件配置区
mail_user = xxxxxxxx  # 用户名
mail_pass = xxxxxxx  # 口令 在QQ邮箱设置里面
# -------------------------------------------------以上内容需要自己填写-------------------------------------------------
sender = mail_user  # 发送者
receiver = sender
times = 1


def data_get(us, pa):
    url = 'https://wfw.scu.edu.cn/a_scu/api/sso/check'
    url_for_id = 'https://wfw.scu.edu.cn/ncov/wap/default/index'
    data = {
        'username': us,
        'password': pa,
        'redirect': 'https://wfw.scu.edu.cn/ncov/wap/default/index'
    }
    header = {
        'Referer': 'https://wfw.scu.edu.cn/site/polymerization/polymerizationLogin?redirect=https%3A%2F%2Fwfw.scu.edu'
                   '.cn%2Fncov%2Fwap%2Fdefault%2Findex&from=wap',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
        'Host': 'wfw.scu.edu.cn',
        'Origin': 'https://wfw.scu.edu.cn',
    }
    r = session.post(url, data=data, headers=header).json()

    if r['m'] == '操作成功':
        data = {
            "xq": "2",
            "year": "2019-2020",
            "kcmc": ""
        }
        r = session.post("https://wfw.scu.edu.cn/score/wap/default/get-data", headers=header, data=data).json()['d'][
            'list']
        return r
    else:
        return False


# ------------------------------------------------邮件发送功能-------------------------------
def smtp(info, receivers) -> None:
    # 第三方 SMTP 服务
    mail_host = "smtp.qq.com"  # 设置服务器

    message = MIMEText(info, 'plain', 'utf-8')
    message['From'] = Header("Y4tacker's Assistant", 'utf-8')
    message['To'] = Header('你有新的成绩请查收', 'utf-8')

    subject = '期末成绩自动推送'
    message['Subject'] = Header(subject, 'utf-8')

    try:
        smtpObj = smtplib.SMTP_SSL()
        smtpObj.connect(mail_host, 465)  # 465 为 SMTP 端口号
        smtpObj.login(mail_user, mail_pass)
        smtpObj.sendmail(sender, receivers, message.as_string())
    except smtplib.SMTPException:
        pass


def main(info):
    global length, receiver, times
    us = info[0]
    pa = info[1]
    data = data_get(us, pa)
    if times == 1:
        result = "由于是第一次运行程序\n获取了所有的成绩信息\n接下来只会发送新增成绩\n感谢您的使用\n"
        for i in data:
            data_dict[i['kcmc']] = i['kccj']
            result += f"课程名称：{i['kcmc']}, 成绩：{i['kccj']}\n"
        smtp(result, receiver)
        length = len(data)
        times += 1
    else:
        if len(data) != length:
            for i in data:
                if i['kcmc'] not in data_dict:
                    data_dict[i['kcmc']] = i['kccj']
                    print(f"当前时间：{time.ctime()} 课程名称：{i['kcmc']}, 成绩：{i['kccj']}")
                    smtp(f"您有一门新成绩出来了快快查看吧\n课程名称：{i['kcmc']}, 成绩：{i['kccj']}", receiver)
            length = len(data)
        else:
            print(f"当前时间：{time.ctime()} 系统通知：暂无新的成绩")


if __name__ == '__main__':
    userInfo = [username, password]
    main(userInfo)
    schedule.every().hour.do(main, userInfo)
    while True:
        schedule.run_pending()
        time.sleep(1)
