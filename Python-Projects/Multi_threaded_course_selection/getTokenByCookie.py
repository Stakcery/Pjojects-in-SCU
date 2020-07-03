from bs4 import BeautifulSoup


def getToken(session, cookie):
    """
    获得tokenvalue
    """
    token_headers = {
        'Referer': 'http://202.115.47.141/login',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) '
                      'Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400',
        'Host': '202.115.47.141',
        'Cookie': cookie
    }
    url_token = 'http://202.115.47.141/student/courseSelect/courseSelect/index'  # 拿token的地方
    r = session.get(url_token, headers=token_headers)
    soup = BeautifulSoup(r.text, "html.parser")
    tokenValue = soup.find('input', attrs={"id": "tokenValue"})['value']  # 抓取tokenvalue

    return tokenValue
