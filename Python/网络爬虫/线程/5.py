import requests
import json
keyword=input("输入搜索关键词：")
url="https://image.bing.com/search/acjson?"
header={"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36"}
params={
    "sp": "-1",
    "lq": "0",
    "pq": "%u732bzhoa%27p",
    "sc": "3-7",
    "cvid": "1B9FD0F8F61B4385BA4C282436637819",
    "ghsh": "0",
    "ghacc": "0",
    "q": keyword,
    "qft": "+filterui:photo-photo",
    "form": "IRFLTR",
    "first": "1",
    "cw": "135",
    "ch": "275"
}
def get_url():
    try:
        response=requests.get(url,headers=header,params=params)
    except  json.JSONDecodeError:
        json = response.json()
        print(json)
        

get_url()

""" headers = {
        "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36}"
    }
url = "https://image.baidu.com/search/acjson?"
gsm='1e'
pn=30
i=1
params = {
            "tn": "resultjson_com",
            "logid": "9722697711466477012",
            "ipn": "rj",
            "ct": "201326592",
            "is": "",
            "fp": "result",
            "fr": "",
            "word": keyword,
            "queryWord": keyword,
            "cl": "2",
            "lm": "-1",
            "ie": "utf-8",
            "oe": "utf-8",
            "adpicid": "",
            "st": "-1",
            "z": "",
            "ic": "0",
            "hd": "",
            "latest": "",
            "copyright": "",
            "s": "",
            "se": "",
            "tab": "",
            "width": "",
            "height": "",
            "face": "0",
            "istype": "2",
            "qc": "",
            "nc": "1",
            "expermode": "",
            "nojc": "",
            "isAsync": "",
            "pn": pn,
            "rn": "30",
            "gsm": gsm,
        }
def get_url():
    response=requests.get(url,headers=headers,params=params).json()
    print(response)

get_url() """
