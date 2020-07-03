import requests
from bs4 import BeautifulSoup


def get_movies(page_num):
    headers = {
        'Host': 'movie.douban.com',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0',
    }
    movies_list = []
    pf_list = []
    for i in range(page_num):
        x = i * 25
        link = f'https://movie.douban.com/top250?start={x}'
        r = requests.get(link, headers=headers)
        soup = BeautifulSoup(r.text, 'html.parser')
        div = soup.find_all('div', class_='hd')
        movies_list += div
        div2 = soup.find_all('p', class_='')
        for each in div2:
            pf_list.append(each.text.split('\n')[2].split('/')[2].replace('剧情', '').strip())
    return movies_list, pf_list


# 获取中英文名
def get_fname(list):
    fname_list = []
    name_list = []
    num = 0
    for each in list:
        m = each.a.find_all('span', class_='title')
        name_list.append(m[0].string)
        if 2 == len(m):
            fname_list.append(m[1].string.strip(r' / '))
        else:
            fname_list.append('暂无')
    # for n in range(len(name_list)):
    #     print(f'TOP{num + 1}\t中文名：{name_list[n]}\t外国名：{fname_list[n]}')
    #     num += 1
    return name_list, fname_list


# 获取其他名字（构思 港或台）
def get_oname(list):
    oname_list = []
    for each in list:
        m = each.a.find_all('span', class_='other')
        oname_list.append(m[0].string.strip('/ '))
    return oname_list


def get_url(list):
    url_list = []
    for each in list:
        url_list.append(each.a['href'])
    return url_list


def main():
    list, pf_list = get_movies(10)
    name_list, fname_list = get_fname(list)
    oname_list = get_oname(list)
    url_list = get_url(list)
    for i in range(len(oname_list)):
        print(
            f'TOP{i + 1}\t 中文名：{name_list[i]}\t 英文名：{fname_list[i]}\t 其他别名：{oname_list[i]}\t 类型：{pf_list[i]}\t 豆瓣链接：{url_list[i]}')


if __name__ == '__main__':
    main()
