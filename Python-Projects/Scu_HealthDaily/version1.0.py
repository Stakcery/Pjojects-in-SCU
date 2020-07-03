import smtplib

import requests
from fake_useragent import UserAgent
import re
import json
from email.mime.text import MIMEText
from email.header import Header

# -------------------------------------全局变量--------------------------------------------------------------------------
# 会话保持
session = requests.Session()
# 登录信息
username = '学号'
password = '身份证后6'
# 邮件配置区
mail_user = "自己改@qq.com"  # 用户名
mail_pass = "自己改"  # 口令 在QQ邮箱设置里面
sender = '自己改@qq.com'  # 发送者
receivers = ['自己改@qq.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱
# 信息区
geo_api_info = 0  # GPS定位信息
province = 0
city = 0
area = 0
formattedAddress = 0  # province + city + area
uid = 0
date = 0  # 昨日日期
created = 0
# 表单内容
tw = 1  # 体温范围
sfcxtz = 0  # 是否出现发热、乏力、干咳、呼吸困难等症状？
sfyyjc = 0
id = 0
sfjcbh = 0  # 是否接触是否接触疑似/确诊人群
sfcxzysx = 0  # 是否有任何与疫情相关的， 值得注意的情况？
sfzx = 0  # 是否在校
sfjcwhry = 0  # 是否接触武汉人员
sfcyglq = 0  # 是否处于观察期
sftjwh = 0  # 今日是否到过或者经停武汉？
sftjhb = 0  # 今日是否到过或者经停湖北其他地区（除武汉）？
sfjchbry = 0  # 今日是否与湖北其他地区（除武汉外）的人员有过较为密集的接触？
ismoved = '0'  # 看是否移动了 这里开学后注意一下啊
remark = '一切很正常'
# 以下未知含义，闭着眼睛都知道以后要填写的内容
jcjgqr = 0
jcjg = ''
qksm = ''
gllx = ''
glksrq = ''
jcbhlx = ''
jcbhrq = ''
fxyy = ''
bztcyy = ''
fjsj = 0
sfjcqz = ''
jcqzrq = ''
jcwhryfs = ''
jchbryfs = ''
xjzd = ''
szgj = ''
sfsfbh = ''
szsqsfybl = '0'
sfsqhzjkk = ''
sqhzjkkys = ''
sfygtjzzfj = 0
gtjzzfjsj = ''
gwszdd = ''
sfyqjzgc = ''
jrsfqzys = ''
jrsfqzfy = ''


# ------------------------------------------------我想骚一点不好意思---------------------------------------------------
class colors:
    PINK = '\033[95m'
    BLUE = '\033[94m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    END = '\033[0m'


# -----------------------------------登录界面获取cookie------------------------------------------
def login() -> bool:
    # 全局变量声明
    global province, city, geo_api_info, formattedAddress, area, uid, date, tw, sfcxtz, sfyyjc, id, sfjcbh, jcjgqr, jcjg, sfcxzysx, qksm, sfzx
    global sfjcwhry, sfcyglq, gllx, glksrq, jcbhlx, jcbhrq, sftjwh, sftjhb, fxyy, bztcyy, fjsj, sfjchbry, sfjcqz, jcqzrq, jcwhryfs, jchbryfs
    global xjzd, szgj, sfsfbh, szsqsfybl, sfsqhzjkk, sqhzjkkys, sfygtjzzfj, gtjzzfjsj, gwszdd, sfyqjzgc, jrsfqzys, jrsfqzfy, ismoved, created
    url = 'https://wfw.scu.edu.cn/a_scu/api/sso/check'
    url_for_id = 'https://wfw.scu.edu.cn/ncov/wap/default/index'
    data = {
        'username': username,
        'password': password,
        'redirect': 'https://wfw.scu.edu.cn/ncov/wap/default/index'
    }
    header = {
        'Referer': 'https://wfw.scu.edu.cn/site/polymerization/polymerizationLogin?redirect=https%3A%2F%2Fwfw.scu.edu'
                   '.cn%2Fncov%2Fwap%2Fdefault%2Findex&from=wap',
        'User-Agent': UserAgent().chrome,
        'Host': 'wfw.scu.edu.cn',
        'Origin': 'https://wfw.scu.edu.cn',
    }
    r = session.post(url, data=data, headers=header, timeout=3).json()

    if r['m'] == '操作成功':
        r2 = session.get(url_for_id, headers=header).text
        uid = re.findall(r'"uid":"([\d]+)"', r2)[0]
        date = re.findall(r'"date":"([\d]+)"', r2)[1]
        created = re.findall(r'"created":([\d]+)', r2)[1]
        geo_api_info = re.findall(r'".*?,"geo_api_info":"(.*)","created".*?', r2)[1].encode('utf-8').decode(
            'unicode_escape')
        id = re.findall(r'"id":([\d]+)', r2)[1]
        # 表单内容
        tw = re.findall(r'"tw":"([\d]+)"', r2)[0]
        sfcxtz = re.findall(r'"sfcxtz":"([\d]+)"', r2)[1]
        sfjcbh = re.findall(r'"sfjcbh":"([\d]+)"', r2)[1]
        sfcxzysx = re.findall(r'"sfcxzysx":"([\d]+)"', r2)[1]
        sfzx = re.findall(r'"sfzx":"([\d]+)"', r2)[1]
        sfjcwhry = re.findall(r'"sfjcwhry":"([\d]+)"', r2)[1]
        sfcyglq = re.findall(r'"sfcyglq":"([\d]+)"', r2)[1]
        sftjwh = re.findall(r'"sftjwh":"([\d]+)"', r2)[1]
        sftjhb = re.findall(r'"sftjhb":"([\d]+)"', r2)[1]
        sfjchbry = re.findall(r'"sfjchbry":"([\d]+)"', r2)[1]
        #  未知信息含义
        sfyyjc = re.findall(r'"sfyyjc":"([\d]+)"', r2)[1]
        jcjgqr = re.findall(r'"jcjgqr":"([\d]+)"', r2)[1]
        jcjg = re.findall(r'"jcjg":"(.*?)"', r2)[1]
        qksm = re.findall(r'"qksm":"(.*?)"', r2)[1]
        gllx = re.findall(r'"gllx":"(.*?)"', r2)[1]
        glksrq = re.findall(r'"glksrq":"(.*?)"', r2)[1]
        jcbhlx = re.findall(r'"jcbhlx":"(.*?)"', r2)[1]
        jcbhrq = re.findall(r'"jcbhrq":"(.*?)"', r2)[1]
        fxyy = re.findall(r'"fxyy":"(.*?)"', r2)[1]
        bztcyy = re.findall(r'"bztcyy":"(.*?)"', r2)[1]
        fjsj = re.findall(r'"fjsj":"(.*?)"', r2)[1]
        sfjcqz = re.findall(r'"sfjcqz":"(.*?)"', r2)[1]
        jcqzrq = re.findall(r'"jcqzrq":"(.*?)"', r2)[1]
        jcwhryfs = re.findall(r'"jcwhryfs":"(.*?)"', r2)[1]
        jchbryfs = re.findall(r'"jchbryfs":"(.*?)"', r2)[1]
        xjzd = re.findall(r'"xjzd":"(.*?)"', r2)[1]
        szgj = re.findall(r'"szgj":"(.*?)"', r2)[1]
        sfsfbh = re.findall(r'"sfsfbh":"(.*?)"', r2)[1]
        szsqsfybl = re.findall(r'"szsqsfybl":"(.*?)"', r2)[0]  # ???
        sqhzjkkys = re.findall(r'"sqhzjkkys":"(.*?)"', r2)[1]
        # sfsqhzjkk = re.findall(r'"sfsqhzjkk":([\d])', r2)[1]
        sfygtjzzfj = re.findall(r'"sfygtjzzfj":([\d])', r2)[1]
        gtjzzfjsj = re.findall(r'"gtjzzfjsj":"(.*?)"', r2)[1]
        gwszdd = re.findall(r'"gwszdd":"(.*?)"', r2)[0]
        sfyqjzgc = re.findall(r'"sfyqjzgc":"(.*?)"', r2)[0]
        jrsfqzys = re.findall(r'"jrsfqzys":"(.*?)"', r2)[0]
        jrsfqzfy = re.findall(r'"jrsfqzfy":"(.*?)"', r2)[0]
        # 获取address等信息
        ismoved = re.findall(r'"ismoved":"(.*?)"', r2)[0]
        x = json.loads(geo_api_info)
        province = x['addressComponent']['province']
        city = x['addressComponent']['city']
        district = x['addressComponent']['district']
        area = f'{province}+{city}+{district}'
        formattedAddress = x['formattedAddress']
        return True
    else:
        return False
        # print(uid, date, sfcxtz, sfyyjc, jcjgqr, jcjg, sfcxzysx, qksm, remark, formattedAddress, area, province, city)
        # print(geo_api_info)
        # print(created, sfzx, sfjcwhry, sfcyglq, gllx, glksrq, jcbhlx, jcbhrq, sftjwh, sftjhb, fxyy, bztcyy, fjsj,
        #       sfjchbry, sfjcqz, jcqzrq)
        # print(jcwhryfs, jchbryfs, xjzd, szgj, sfsfbh, szsqsfybl, sfsqhzjkk, sqhzjkkys, sfygtjzzfj, gtjzzfjsj, id)
        # print(gwszdd, sfyqjzgc, jrsfqzys, '1', jrsfqzfy, '12', ismoved)


# ---------------------------------------填报界面------------------------------------------
def post() -> json:
    headers = {
        'Host': 'wfw.scu.edu.cn',
        'User-Agent': UserAgent().chrome,
        'Accept': 'application/json,text/javascript,*/*;q=0.01',
        'Accept-Language': 'zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2',
        'Accept-Encoding': 'gzip,deflate,br',
        'Content-Type': 'application/x-www-form-urlencoded;',
        'X-Requested-With': 'XMLHttpRequest',
        'Content-Length': '2082',
        'Origin': 'https://wfw.scu.edu.cn',
        'Connection': 'keep-alive',
        'Referer': 'https://wfw.scu.edu.cn/ncov/wap/default/index',
    }
    data = {
        "uid": uid, "date": date, "tw": tw, "sfcxtz": sfcxtz, "sfyyjc": sfyyjc, "jcjgqr": jcjgqr,
        "jcjg": jcjg, "sfjcbh": sfjcbh, "sfcxzysx": sfcxzysx, "qksm": qksm, "remark": remark,
        "address": formattedAddress, "area": area, "province": province, "city": city,
        "geo_api_info": geo_api_info,
        "created": created, "sfzx": sfzx, "sfjcwhry": sfjcwhry, "sfcyglq": sfcyglq, "gllx": gllx, "glksrq": glksrq,
        "jcbhlx": jcbhlx, "jcbhrq": jcbhrq, "sftjwh": sftjwh, "sftjhb": sftjhb, "fxyy": fxyy, "bztcyy": bztcyy,
        "fjsj": fjsj, "sfjchbry": sfjchbry, "sfjcqz": sfjcqz, "jcqzrq": jcqzrq, "jcwhryfs": jcwhryfs,
        "jchbryfs": jchbryfs, "xjzd": xjzd, "szgj": szgj,
        "sfsfbh": sfsfbh, "szsqsfybl": szsqsfybl, "sfsqhzjkk": sfsqhzjkk, "sqhzjkkys": sqhzjkkys,
        "sfygtjzzfj": sfygtjzzfj, "gtjzzfjsj": gtjzzfjsj,
        "id": id, "gwszdd": gwszdd, "sfyqjzgc": sfyqjzgc, "jrsfqzys": jrsfqzys, "jrsfqzfy": jrsfqzfy,
        "ismoved": ismoved,

    }
    r1 = session.post('https://wfw.scu.edu.cn/ncov/wap/default/save', headers=headers, data=data)
    return r1.json()


# ------------------------------------------------邮件发送功能-------------------------------
def smtp(info) -> None:
    # 第三方 SMTP 服务
    mail_host = "smtp.qq.com"  # 设置服务器

    message = MIMEText(info, 'plain', 'utf-8')
    message['From'] = Header("豪哥哥", 'utf-8')
    message['To'] = Header('成功了噻', 'utf-8')

    subject = '每日健康报'
    message['Subject'] = Header(subject, 'utf-8')

    try:
        smtpObj = smtplib.SMTP()
        smtpObj.connect(mail_host, 25)  # 25 为 SMTP 端口号
        smtpObj.login(mail_user, mail_pass)
        smtpObj.sendmail(sender, receivers, message.as_string())
        print(colors.RED+"邮件发送成功"+colors.END)
    except smtplib.SMTPException:
        print(colors.RED+"Error: 无法发送邮件"+colors.END)


# ---------------------------------------主函数------------------------------------------
def main():
    condition = login()
    if condition:
        print(colors.PINK+'Login successfully'+colors.END)
        json_data = post()
        if '今天已经填报了' in json_data['m']:
            print(colors.BLUE+'填过了哦'+colors.END)
            smtp(json_data['m'])
        elif '操作成功' in json_data['m']:
            print(colors.PINK+'填报成功'+colors.END)
            smtp(json_data['m'])


if __name__ == '__main__':
    main()
