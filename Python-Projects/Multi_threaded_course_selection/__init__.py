import time
from getTokenByCookie import getToken
from login import *
from selectCourse import select, get_token_fajhh
from csvReader import *
from basicInfo import userInfo
import threading

session = requests.session()
username = ""
password = ""
email = ""
cookie = ""
token = ""
fajhh = ""
status = False


def getUserInfo():
    global username, password, email, cookie, fajhh
    newToken = get_token_fajhh()
    return newToken


def isLogin():
    global username, password, email, cookie
    username = userInfo.get("username")
    password = userInfo.get("password")
    email = userInfo.get("email")
    if login(username, password):
        cookie = getCookie()
        return True
    else:
        return False


class myTread(threading.Thread):
    def __init__(self, myToken, item):
        threading.Thread.__init__(self)
        self.token = myToken
        self.number = item

    def run(self) -> None:
        main(self.token, self.number)


def main(token, item):
    global status
    myDict = readInfo()
    temp = myDict[item].strip("\n").split("@")
    courseName = temp[0]
    courseNumber = temp[1]
    courseId = temp[2]
    selectCourse = select(username, email, session, cookie, token[1], courseId,
                          courseName, courseNumber)
    selectCourse.get_course_info()
    while True:
        if status:
            token = getToken(session, cookie)
            status = selectCourse.course_select(token)
            break
        else:
            status = selectCourse.course_select(token[0])
            break
    print(f'{courseName}选课成功，欢迎下次使用')

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
            return True
        else:
            return False


if __name__ == '__main__':
    myCondition = True
    if isLogin():
        while True:
            try:
                token = getUserInfo()
                break
            except:
                print("教务处选课接口暂未开放")
                pass
    if token:
        threadList = []
        x = readInfo()
        number = 0
        for i in x:
            if len(i) > 0:
                threadList.append(myTread(token, number))
                number += 1
        for i in threadList:
            i.start()
    else:
        print("用户故障，请检查用户名或密码，如果无误则可能为网络故障")
