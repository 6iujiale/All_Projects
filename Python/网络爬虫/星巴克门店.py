import requests
def diZhi():
    f_url="https://www.starbucks.com.cn/api/stores/nearby"
    hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36 Edg/92.0.902.67"}
    par={
        'lat':'22.542037',
        'lon':'114.093805',
        'limit':'80',
        'locale':'ZH',
        'features': '',
        'radius':'1616'
    }
    r=requests.get(url=f_url,params=par,headers=hd)
    return r.json()
a=diZhi()
print(a)
with open("./ljl.json","w",encoding="utf-8") as f2:
    f2.write(str(a))
