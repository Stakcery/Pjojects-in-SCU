from selenium import webdriver
import time


class Cookie:
    def __init__(self, username, password):
        self.url_skip = 'http://ids.scu.edu.cn/amserver/UI/Login?goto=http://zhjw.scu.edu.cn/login/'
        self.username = username
        self.password = password

    def csh_wuchuangkou(self):
        options = webdriver.ChromeOptions()
        options.add_argument('--headless')  # 无窗口化启动
        options.add_argument('--blink-settings=imagesEnabled=false')
        driver = webdriver.Chrome(options=options)
        return driver

    def csh_youchuangkou(self):
        options = webdriver.ChromeOptions()
        options.add_argument('--start-maximized')  # 最大窗口化启动
        options.add_argument('--blink-settings=imagesEnabled=false')
        driver = webdriver.Chrome(options=options)
        return driver

    def cookie_get(self):
        print('\033[1;30;41m温馨提示：由于学校网站响应速度比较玄学，获取COOKIE过程中请耐心等待。。。\033[0m')
        driver = self.csh_wuchuangkou()
        driver.get(self.url_skip)
        user_name = self.username
        user_password = self.password
        driver.find_element_by_xpath('//*[@id="username"]').send_keys(user_name)
        driver.find_element_by_xpath('//*[@id="password"]').send_keys(user_password)
        driver.find_element_by_xpath('//*[@id="loginBtn123"]').click()
        time.sleep(1)
        driver.implicitly_wait(8)
        cook = driver.get_cookies()
        count = 0
        print('为保障Cookie质量，请稍等', end='')  # end=参数默认里面为\n
        while count <= 1:
            print(f'{1 - count}s', end='')
            time.sleep(1)
            if count != 1:
                print('\b\b', end='')
            count += 1
        print('\n', end='')
        driver.close()
        cookies = ''
        length = len(cook)
        print(cook)
        print(length)
        for i in range(length):
            name = cook[i]['name']
            value = cook[i]['value']
            cookie = f'{name}={value}'
            cookies += cookie
            cookies += ';'
        print('获取成功，Cookie值为：', cookies)
        return cookies


if __name__ == '__main__':
    x = Cookie('你的学号', '你的密码')
    x.cookie_get()
