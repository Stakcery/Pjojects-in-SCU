import random
import base64
import requests

url_for_key = "http://www.damagou.top/apiv1/login.html?username=用户名&password=密码"


class captchaRecognize:
    def __init__(self, s, condition):
        if s is None:
            self.s = requests.session()
        else:
            self.s = s
        self.headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
        }
        self.condition = condition

    def get_captcha_pic_login(self):

        get_url = "http://202.115.47.141/img/captcha.jpg" + '?' + str(random.randint(1, 100))
        headers_for_captcha = {
            'Host': '202.115.47.141',
            'Connection': 'keep-alive',
            'Cache-Control': 'max-age=0',
            'Upgrade-Insecure-Requests': '1',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
            'Referer': 'http://202.115.47.141/login?errorCode=badCaptcha',
            'Accept-Encoding': 'gzip, deflate',
            'Accept-Language': 'zh-CN,zh;q=0.9',

        }
        try:
            r = self.s.get(get_url, headers=headers_for_captcha)
            return r.content
        except:
            pass

    def get_captcha_pic_selectCourse(self):

        get_url = "http://202.115.47.141/student/courseSelect/selectCourse/getYzmPic?time=250?time=526?time=28?time=334"
        headers_for_captcha = {
            'Host': '202.115.47.141',
            'Connection': 'keep-alive',
            'Cache-Control': 'max-age=0',
            'Upgrade-Insecure-Requests': '1',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
            'Referer': 'http://202.115.47.141/login?errorCode=badCaptcha',
            'Accept-Encoding': 'gzip, deflate',
            'Accept-Language': 'zh-CN,zh;q=0.9',

        }
        try:
            r = self.s.get(get_url, headers=headers_for_captcha)
            return r.content
        except:
            pass

    # 用于获取打码平台UserKey
    def get_userKey(self, ):

        get_url = url_for_key
        try:
            r = requests.get(get_url, headers=self.headers)
            r.raise_for_status()
            r.encoding = r.apparent_encoding
            print("Dmagou Being Processing")
            return r.text
        except:
            print("Can't Get Userkey ", r.status_code)

    def get_english_captcha(self, captcha, userkey):
        base64_data = base64.b64encode(captcha)

        postUrl = 'http://www.damagou.top/apiv1/recognize.html'
        postData = {
            "image": base64_data,
            "userkey": userkey,
            "type": "1001",
        }
        try:
            r = requests.post(postUrl, data=postData, headers=self.headers)
            r.raise_for_status()
            r.encoding = r.apparent_encoding
            # print("破解验证码成功")
            return r.text
        except:
            pass
            # print("破解验证码失败")

    def __call__(self):
        if self.condition == 1:
            captcha = self.get_captcha_pic_login()
        else:
            captcha = self.get_captcha_pic_selectCourse()
        userKey = self.get_userKey()
        return self.get_english_captcha(captcha, userKey)
