import requests
import re
import json

# -------------------------------------全局变量--------------------------------------------------------------------------
# 会话保持
session = requests.Session()
# 登录信息
username = '自己改'
password = '自己改'


# ------------------------------------------------我想骚一点不好意思---------------------------------------------------
class colors:
    PINK = '\033[95m'
    BLUE = '\033[94m'
    GREEN = '\033[92m'
    YELLOW = '\033[93m'
    RED = '\033[91m'
    END = '\033[0m'


# -----------------------------------登录界面获取cookie------------------------------------------
def data_get() -> dict:
    # 全局变量声明
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
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
        'Host': 'wfw.scu.edu.cn',
        'Origin': 'https://wfw.scu.edu.cn',
    }
    r = session.post(url, data=data, headers=header, timeout=3).json()

    if r['m'] == '操作成功':
        r2 = session.get(url_for_id, headers=header).text
        x = re.findall(r'.*?oldInfo: (.*),.*?', r2)
        data = eval(x[0])
        return data


def post() -> json:
    headers = {
        'Host': 'wfw.scu.edu.cn',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
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
    data = data_get()
    r1 = session.post('https://wfw.scu.edu.cn/ncov/wap/default/save', headers=headers, data=data)
    return r1.json()


def main():
    json_data = post()
    if '今天已经填报了' in json_data['m']:
        print(colors.BLUE + '已经填过了哦' + colors.END)

    elif '操作成功' in json_data['m']:
        print(colors.PINK + '今日填报成功' + colors.END)


if __name__ == '__main__':
    main()
