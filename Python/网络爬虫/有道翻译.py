# import requests
# def fanYi():
#     f_url="https://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule"
#     hd={"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36 Edg/92.0.902.67"}
#     par={
#         'i':'猫',
#         'from':'AUTO',
#         'to':'AUTO',
#         'smartresult':'dict',
#         'client':'fanyideskweb',
#         'doctype':'json',
#         'version':'2.1',
#         'keyfrom':'fanyi.web',
#         'action':'FY_BY_REALTlME'
#     }
#     r=requests.post(url=f_url,params=par,headers=hd)
#     return r.json()
# a=fanYi()
# print(a)

import requests
def fanYi(keyword):
    f_url="https://fanyi.youdao.com/translate?smartresult=dict&smartresult=rule"
    hd={"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36 Edg/92.0.902.67"}
    par={
        'i':keyword,
        'from':'AUTO',
        'to':'AUTO',
        'smartresult':'dict',
        'client':'fanyideskweb',
        'doctype':'json',
        'version':'2.1',
        'keyfrom':'fanyi.web',
        'action':'FY_BY_REALTlME'
    }
    r=requests.post(url=f_url,params=par,headers=hd)
    return r.json()
a=fanYi("继续")
print(a)