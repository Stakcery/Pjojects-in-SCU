import requests
import json
import re
import threading

header = {
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
    'Accept-Encoding': 'gzip, deflate, br',
    'Accept-Language': 'zh-CN,zh;q=0.9',
    'Cache-Control': 'max-age=0',
    'Connection': 'keep-alive',
    'Cookie': 'IJSESSIONID=19hnpak4eig9xgxjnjex78q6o; '
              'iuuid=9591C07CFBC1D3BE9BACA758767EA08FBDC4094D320F0DFCEC803C268FA2AFB3; '
              'latlng=30.671931%2C103.660052%2C1584589356946; ci=59; cityname=%E6%88%90%E9%83%BD; '
              '_lxsdk_cuid=170f0e2c548c8-0691372f106c22-335e4e7e-144000-170f0e2c548c8; '
              '_lxsdk=9591C07CFBC1D3BE9BACA758767EA08FBDC4094D320F0DFCEC803C268FA2AFB3; '
              'i_extend=E066949552764205396913576493272373911905_c1_e6711092781123648724_anull_o1_dhotelpoitagb_k1002Gempty__xhotelhomepage__yselect__zday; _lxsdk_s=170f1002074-88a-456-4c5%7C%7C16',
    'Host': 'ihotel.meituan.com',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3754.400 QQBrowser/10.5.4034.400'
}


def Hotel_info(searchplace):  # 获得searchplace附近的酒店信息
    url = 'https://ihotel.meituan.com/hbsearch/HotelSearch'
    num = 0
    n = 1
    tyname_list = []
    tyid_list = []
    final_list = []
    for i in range(5):
        data = {
            'utm_medium': 'touch',
            'version_name': '999.9',
            'platformid': '1',
            'cateId': '20',
            'newcate': '1',
            'limit': '50',
            'offset': num,
            'cityId': '59',
            'ci': '59',
            'startendday': '20200319~20200319',  # 用于查起始日期与结束日期
            'startDay': '20200319',
            'endDay': '20200319',
            'mypos': '30.671931%2C103.660052',
            'attr_28': '129',
            'sort': 'distance',
            'distance': '3000',
            'uuid': '9591C07CFBC1D3BE9BACA758767EA08FBDC4094D320F0DFCEC803C268FA2AFB3',
            'accommodationType': '1',
            'lat': '30.671931',
            'lng': '103.660052',
            'q': searchplace,  # q用来查询地点
            'keyword': searchplace,
            'ste': '_b400203',
            'hotelType': '20244',  # 20244代表民宿
            'poi_attr_20022': '20244'
        }
        response = requests.get(url, headers=header, params=data)
        json_data = json.loads(response.text)
        final_data = json_data['data']['searchresult']
        num += 50
        for i in final_data:
            hotel_name = i['name']
            hotel_addr = i['addr']
            hotel_score = i['scoreIntro']
            hotel_price = i['originalPrice']
            hotel_distance = i['posdescr']
            hotel_id = i['realPoiId']
            ty = i['poiAttrTagList']
            n += 1
            if '投影设备' in ty:
                info = f'民宿名：{hotel_name},地址：{hotel_addr},评分：{hotel_score},当前价格：{hotel_price},{hotel_distance}'
                final_list.append(info)
                tyname_list.append(hotel_name)
                tyid_list.append(hotel_id)
    length = len(final_list)
    print(f'共爬取了{n}家民宿的信息，其中符合条件(含有投影设备)的有{length}个，以下是其基本信息')
    for i in range(length):
        print(final_list[i])
    return tyname_list, tyid_list


def get_comment(realPoId, address_search):
    url = 'https://ihotel.meituan.com/api/v2/comments/biz/reviewCount?poiId={}'.format(realPoId)
    response = requests.get(url, headers=header)
    Review_maxNum = json.loads(response.text)['Data']['Total']  # 得到酒店最大评论数
    # print(ReviewNum,address_search)
    url = 'https://ihotel.meituan.com/api/v2/comments/biz/reviewList'
    data = {
        'referid': '{0}'.format(format(realPoId)),
        'limit': Review_maxNum,  # 一次爬完所有评论
        'start': 0,  # 本次爬的起始值
        'filterid': 800,
        'querytype': 1,
        'utm_medium': 'touch',
        'version_name': 999.9,
    }
    response = requests.get(url, headers=header, params=data)
    # UnicodeEncodeError: 'gbk' codec can't encode character '\U0001f64f' in position 62: illegal multibyte sequence
    # 用errors规避错误
    jsons = json.loads(response.text)
    CommentCounts = jsons['Data']['List']
    with open(f'{address_search}.txt', 'a', errors='ignore')as f:
        for i in CommentCounts:
            text = str(i['Content']).replace(' ', '').replace('\r', '')  # 去掉表情
            text = re.sub(':\S+?:', '', text)
            text = text.replace('#', '')
            f.write(text)


def main():
    tyname_list, tyid_list = Hotel_info('北京华联')
    length = len(tyid_list)
    print('下面将爬取各个民宿评论，并将其存在根目录，请自行查看')
    for i in range(length):
        t = threading.Thread(target=get_comment, args=(tyid_list[i], tyname_list[i]))
        t.start()


if __name__ == '__main__':
    main()
