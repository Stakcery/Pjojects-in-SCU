import os
import re
import time
from login import *
import requests
from bs4 import BeautifulSoup
from AAA.smtp import smtp
from captchaRecognize import captchaRecognize
from getTokenByCookie import getToken


def get_token_fajhh():
    """
    获得tokenvalue
    """
    token_headers = {
        'Referer': 'http://202.115.47.141/login',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
        'Host': '202.115.47.141',
        'Cookie': getCookie()
    }
    url_token = 'http://202.115.47.141/student/courseSelect/courseSelect/index'  # 拿token的地方
    r = session.get(url_token, headers=token_headers)
    soup = BeautifulSoup(r.text, "html.parser")
    tokenValue = soup.find('input', attrs={"id": "tokenValue"})['value']  # 抓取tokenvalue
    fajhh = re.findall(r"/intentCourse/index\?fajhh=(\d+)'", r.text)[0]

    print(f'Tokenvalue:{tokenValue}')
    print(f'您的专业号:{fajhh}')
    return tokenValue, fajhh


class select:
    def __init__(self, user, receivers, session, cookie, fajhh, kclbdm, target_class, kch):
        self.receivers = receivers
        self.user = user
        self.session = session
        self.cookie = cookie
        self.fajhh = fajhh
        self.kclbdm = kclbdm
        self.kch = kch
        self.target_class = target_class
        self.target_kch = ""
        self.target_kxh = ""
        self.param_Select = {}

    def get_course_info(self) -> None:
        infoDict = {}
        # print("请输入目标课程完整名称：")
        target_class = self.target_class
        # print("请输入目标课程完整课程号+课序号（用_连接）：")
        target_kechenghao_kexuhao = self.kch
        temp = re.split(r'_', target_kechenghao_kexuhao)
        self.target_kch = temp[0]
        self.target_kxh = temp[1]
        kcIds = self.target_kch + "@" + self.target_kxh + "@" + "2020-2021-1-1"
        kcms_ = target_class + "(" + self.target_kch + "@" + self.target_kxh + ")"
        kcms = ''
        for chr in kcms_:
            kcms += (str(ord(chr)) + ',')
        infoDict['dealType'] = "5"
        infoDict['kcIds'] = kcIds
        infoDict['kcms'] = kcms
        infoDict['fajhh'] = self.fajhh
        infoDict['sj'] = "0_0"
        infoDict['searchtj'] = ""
        if self.kclbdm == "ywh":
            infoDict['kclbdm'] = ""
        else:
            infoDict['kclbdm'] = self.kclbdm
        self.param_Select = infoDict
        print(f'课程信息拼接完成:')

    def result_search(self):
        url_query = 'http://202.115.47.141/student/courseSelect/selectResult/query'
        headers_QueryCourser = {
            'Host': '202.115.47.141',
            'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:66.0) Gecko/20100101 Firefox/66.0',
            'Accept': 'application/json, text/javascript, */*; q=0.01',
            'Accept-Language': 'zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2',
            'Referer': 'http://202.115.47.141/student/courseSelect/selectCourses/waitingfor',
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            'X-Requested-With': 'XMLHttpRequest',
            'Content-Length': '31',
            'Connection': 'close',
            'Cookie': self.cookie
        }
        param_query = {
            'kcNum': '1',
            'redisKey': self.user + '5'
        }
        print("正在查询选课结果...")
        queryCourse = requests.post(url=url_query, headers=headers_QueryCourser,
                                    data=param_query)  # 查询是否选课成功
        result = queryCourse.text
        print(result)
        if '选课成功' in result:
            print("选课成功")
            smtp(result, self.receivers)
            return True
        else:
            return False

    def course_select(self, tokenValue):
        """
        获取想要查询的课程信息
        """
        url_SelectCourse = 'http://202.115.47.141/student/courseSelect/freeCourse/courseList'  # 自由选课界面
        url_select = 'http://202.115.47.141/student/courseSelect/selectCourse/checkInputCodeAndSubmit'  # 向这个页面post，即选课

        headers_CourseSelect = {
            'Host': '202.115.47.141',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.36 Safari/537.36',
            'Accept': '*/*',
            'Accept-Language': 'zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2',
            'Accept-Encoding': 'gzip, deflate',
            'Referer': 'http://202.115.47.141/student/courseSelect/freeCourse/index?fajhh=' + self.fajhh,
            # ?fajhh=5519',
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            'X-Requested-With': 'XMLHttpRequest',
            'Content-Length': '31',
            'Connection': 'keep-alive',
            'Cookie': self.cookie,
            'Origin': 'http://202.115.47.141'
        }

        param_SelectCourse = {
            'searchtj': self.target_class,
            'xq': '0',
            'jc': '0',
            'kclbdm': ''
        }

        token_headers = {
            'Referer': 'http://202.115.47.141/login',
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                          'Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
            'Host': '202.115.47.141',
            'Cookie': self.cookie
        }
        status = True
        self.param_Select['tokenValue'] = tokenValue
        while status:
            try:
                r = self.session.post(url_SelectCourse, headers=headers_CourseSelect, data=param_SelectCourse,
                                      timeout=2)
                r = r.json()
                print(r)
                r_json = r.get("rwRxkZlList")
                r_json = eval(r_json)
                length = len(r_json)
                for i in range(length):
                    keyuliang = r_json[i]['bkskyl']
                    kechenghao = r_json[i]['kch']
                    kexuhao = r_json[i]['kxh']
                    if keyuliang > 0 and kexuhao == self.target_kxh and kechenghao == self.target_kch:
                        captcha = captchaRecognize(session, 2)
                        j_captcha = captcha()
                        print(j_captcha)
                        self.param_Select['inputCode'] = j_captcha
                        self.param_Select['tokenValue'] = getToken(self.session, self.cookie)
                        for item in range(4):
                            r_final = self.session.post(url_select, headers=token_headers, data=self.param_Select,
                                                        timeout=2)
                        print("选课成功,等待脚本运行完毕请登录检查")
                        time.sleep(0.5)
                        resultStatus = self.result_search()
                        print(resultStatus)
                        if resultStatus:
                            return True
                        else:
                            status = True
                            self.param_Select['tokenValue'] = getToken(self.session, self.cookie)
                    elif keyuliang <= 0 and kexuhao == self.target_kxh and kechenghao == self.target_kch:
                        print(f"{self.target_class}没有课余量")
                        time.sleep(0.2)
            except requests.exceptions.RequestException:
                print('错误')
                # 查询是否选课成功
