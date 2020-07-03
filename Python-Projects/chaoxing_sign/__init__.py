import json
import time
import requests
import smtplib
from email.mime.text import MIMEText
from email.header import Header

# ------------------------------------------------全局变量区域----------------------------------------------------------
uid = 0
cookie = 0
course_data = []
index = 0
activities = []
picture_name = ''  # 自己填写
# 经纬度自己去查
address = ''  # 地址
latitude = ''  # 纬度
longitude = ''  # 经度
name = ''  # 可以填写，不填写就是默认参数,感觉无所谓可以不要这个参数？
# ---邮件配置
mail_user = "你的QQ"  # 用户名
mail_pass = "邮箱的口令"  # 口令
sender = '发送者你自己QQ邮箱'
receivers = ['接收者']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱


class Operate:
    def __init__(self, username, password):
        self.username = username
        self.password = password
        self.session = requests.session()
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/74.0.3729.108 Safari/537.36'}

    def smtp(self,info):
        # 第三方 SMTP 服务
        mail_host = "smtp.qq.com"  # 设置服务器

        message = MIMEText(info, 'plain', 'utf-8')
        message['From'] = Header("守护最好的豪哥哥", 'utf-8')
        message['To'] = Header('成功了哟', 'utf-8')

        subject = 'Python自动发信'
        message['Subject'] = Header(subject, 'utf-8')

        try:
            smtpObj = smtplib.SMTP()
            smtpObj.connect(mail_host, 25)  # 25 为 SMTP 端口号
            smtpObj.login(mail_user, mail_pass)
            smtpObj.sendmail(sender, receivers, message.as_string())
            print("邮件发送成功")
        except smtplib.SMTPException:
            print("Error: 无法发送邮件")

    def Cookie_get(self):  # 获取cookie
        url = 'https://passport2-api.chaoxing.com/v11/loginregister'

        data = {
            'uname': self.username,
            'code': self.password
        }

        cookie_jar = self.session.post(url=url, data=data, headers=self.headers).cookies
        cookie_t = requests.utils.dict_from_cookiejar(cookie_jar)
        return cookie_t

    def Course_get(self):
        global course_data, index
        url = "http://mooc1-api.chaoxing.com/mycourse/backclazzdata?view=json&rss=1"
        res = self.session.get(url, headers=self.headers, cookies=cookie)
        cdata = json.loads(res.text)
        if cdata['result'] != 1:
            print("课程列表获取失败")
        for item in cdata['channelList']:
            if "course" not in item['content']:
                continue
            data = {
                'courseid': item['content']['course']['data'][0]['id'],
                'name': item['content']['course']['data'][0]['name'],
                'classid': item['content']['id']
            }
            course_data.append(data)
        print("课程信息获取成功:")

        for item in course_data:  # 打印课程
            print(str(index) + ".课程名称:" + item['name'])
            index += 1

    # 查找activePrimaryId
    def aid_get(self, url):
        var1 = url.split("&")
        for var in var1:
            var2 = var.split("=")
            if var2[0] == "activePrimaryId":
                return var2[1]
        return "error"

    def Sign(self, courseId, classId):
        global activities, course_data
        url = "https://mobilelearn.chaoxing.com/ppt/activeAPI/taskactivelist?courseId=" + str(
            courseId) + "&classId=" + str(classId) + "&uid=" + uid
        response = requests.get(url, headers=self.headers, cookies=cookie)
        if response.status_code == 200:
            data = json.loads(response.text)
            activeList = data['activeList']
            for i in activeList:
                if "nameTwo" not in i:
                    continue
                if i['activeType'] == 2 and i['status'] == 1:
                    sign_url = i['url']  # 提取activePrimaryId
                    aid = self.aid_get(sign_url)
                    if aid not in activities:
                        print(course_data[i]['name'], '查询到可签到活动 活动名称:%s 活动状态:%s 活动时间:%s aid:%s'
                              % (i['nameOne'], i['nameTwo'], i['nameFour'], aid))
                    self.sign_task(aid, uid)
                    return True
        else:
            return False

    def sign_task(self, aid, uid):
        global activities
        url = "https://mobilelearn.chaoxing.com/pptSign/stuSignajax"
        objectId = self.uploadPicture()
        res = requests.post(url,
                            data={"name": name, "address": address, 'activeId': aid, 'uid': uid, 'longitude': longitude,
                                  'latitude': latitude, 'objectId': objectId}, headers=self.headers, cookies=cookie)
        if res.text == "success":
            print(" 签到成功！")
            self.smtp("签到成功")
            activities.append(aid)
        else:
            print(res.text, '签到失败')
            self.smtp("签到失败")
            activities.append(aid)

    # 上传图片需要token
    def getTokenValue(self):
        url = 'https://pan-yz.chaoxing.com/api/token/uservalid'
        res = requests.get(url, headers=self.headers, cookies=cookie)
        t_dict = json.loads(res.text)
        return t_dict['_token']

    def uploadPicture(self):  #
        global picture_name, uid
        if picture_name.isspace() or len(picture_name) == 0:
            print("图片参数错误，请检查")
            return
        else:
            url = 'https://pan-yz.chaoxing.com/upload'
            files = {'file': (picture_name, open(picture_name, 'rb'), 'image/webp,image/*',), }
            data = {
                'puid': uid,
                '_token': self.getTokenValue()
            }
            response = requests.post(url, data=data, files=files, headers=self.headers,
                                     cookies=cookie)
            t_dict = json.loads(response.text)
            return t_dict['objectId']

    def main(self):
        global cookie, uid
        cookie = self.Cookie_get()
        uid = cookie['UID']
        self.Course_get()
        for i in range(index):
            time.sleep(15)
            condition = self.Sign(course_data[i]['courseid'], course_data[i]['classid'])
            if condition:
                pass
            else:
                print('课程:', course_data[i]['name'], '未查询到签到活动')

    def __call__(self, *args, **kwargs):
        self.main()


if __name__ == '__main__':
    username = input("请输入账号：\n>")
    password = input("请输入密码：\n>")
    Generator = Operate(username, password)
    Generator()
