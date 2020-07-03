import hashlib
from captchaRecognize import captchaRecognize
import requests

session = requests.session()
cook = ''


def is_login(text):
    if 'URP' in text:
        return True
    else:
        return False


def login(j_username, j_password):
    """
    这里登录教务处网站，使之前得到的session和自己的身份联系在一起，从而使得session的身份验证生效
    """
    global cook
    headers = {
        'Host': '202.115.47.141',
        'Connection': 'keep-alive',
        'Cache-Control': 'max-age=0',
        'Upgrade-Insecure-Requests': '1',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
        'Referer': 'http://202.115.47.141/login?errorCode=badCaptcha',
        'Accept-Encoding': 'gzip, deflate',
        'Accept-Language': 'zh-CN,zh;q=0.9',
    }
    md5 = hashlib.md5()
    md5.update(j_password.encode('utf-8'))
    j_password = md5.hexdigest()
    captcha_class = captchaRecognize(session, 1)
    j_captcha = captcha_class()
    for i in range(3):
        print('欢迎使用四川大学本科教务抢课系统  By：Stackery')
        Url = 'http://202.115.47.141/j_spring_security_check'
        Data = {
            "j_username": j_username,
            "j_password": j_password,
            "j_captcha": j_captcha,
            '_spring_security_remember_me': 'on'
        }
        print(f'学 号：{j_username}')
        print(f'密 码：{j_password}')
        print(f'破解验证码成功: {j_captcha}')
        try:
            r = session.post(Url, data=Data, headers=headers)
            if is_login(r.text):
                print("Login Successfully!")
                cookies_dict = session.cookies.get_dict()
                cokkies_str = ''
                for i, j in enumerate(cookies_dict):
                    temp = j + '=' + cookies_dict[j] + ';'
                    cokkies_str += temp
                cook = cokkies_str
                return True
            else:
                print("Login Falsely!")
                print("Verification Code Or Password Error")
            return is_login(r.text)
        except:
            return False


def getCookie():
    return cook
