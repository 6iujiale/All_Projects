import requests
url="https://cn.bing.com/search"
# keyword=input()
par={
    "q":"新冠疫苗"
    # "q":keyword
}
hd={
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36"
}
r=requests.get(url,params=par,headers=hd)
r.encoding=r.apparent_encoding
print(r.text)
# with open("./text/1.html","w",encoding="utf-8") as f2:
#     f2.write(r.text)

# print(url_end)
