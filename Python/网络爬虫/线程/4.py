import requests
import asyncio
import aiohttp
import gc
import os

""" async def fetch(session,url):
    async with session.get(url,verify_ssl=False) as response:
        content=await response.content.read()
        for i in range()
loop = asyncio.get_event_loop()
loop.run_until_complete(result)
 """
"""  https://cn.bing.com/images/search?sp=-1&lq=0&pq=%u732bzhoa%27p&sc=3-7&cvid=1B9FD0F8F61B4385BA4C282436637819&ghsh=0&ghacc=0&q=狗
 &qft=+filterui:photo-photo&form=IRFLTR&first=1 """
"""  
sp: -1
lq: 0
pq: （无法解码值）
sc: 3-7
cvid: 1B9FD0F8F61B4385BA4C282436637819
ghsh: 0
ghacc: 0
q: 狗
qft:  filterui:photo-photo
form: IRFLTR
first: 1
cw: 1353
ch: 275 """

keyword=input("输入搜索关键词：")
url="https://cn.bing.com/images/search?sp=-1&lq=0&pq=%u732bzhoa%27p&sc=3-7&cvid=1B9FD0F8F61B4385BA4C282436637819&ghsh=0&ghacc=0&q="+keyword+"&qft=+filterui:photo-photo&form=IRFLTR&first=1"
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

async def fetch(url,num):
    print("正在发送请求：")
    async with aiohttp.ClientSession() as session:
        async with session.get(url,headers=header) as response:
            content=await response.content.read()
            """ for i in range(3):
                with open(f"./images2/{i}.jpg",mode="wb") as fp:
                    fp.write(content)
                    gc.collect() """
            try:
                os.mkdir("./Image-{keyword}/")
            except FileExistsError:
                # print("文件夹已经存在")
                pass
            finally:
                for i in range(num):
                    with open(f"./Image-{keyword}/{num+1}.jpg",mode="wb") as fp:
                        fp.write(content)
                    print(f"{num}保存成功")

async def main():
    result=requests.get(url,params=params,headers=header)
    data = result.text
    print(data)
    # for imageURL in data:
    #     imageURL = imageURL['thumbURL']
    #     # print(imageURL)
    #     fetch(imageURL, 10)

if __name__=="__main__":
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())

