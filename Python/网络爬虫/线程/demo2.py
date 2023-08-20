import aiofiles
import requests
import time
import asyncio
import aiohttp
import os

keyword=input("请输入关键词：")
headers = {
            "User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36}"
        }
url = "https://image.baidu.com/search/acjson?"
path=f"./Images{keyword}/"

async def getContent(src,i):
    async with aiohttp.ClientSession() as session:
        async with await session.get(url=src,headers=headers) as response:
            try:
                os.mkdir(path)
            except FileExistsError:
                # print("文件夹已经存在")
                pass
            finally:
                async with aiofiles.open(f"{path}{i}.jpg", mode='wb') as fp:
                    await fp.write(await response.content.read())
                print(f"{i}保存成功")
async def main():
    gsm='1e'
    pn=30
    i=1
    while True:
        param = {
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
            time.time(): ""
        }
        response = requests.get(url=url, headers=headers, params=param)
        json = response.json()
        print(param["gsm"])
        print(param["pn"])

        data = json["data"]
        for j in range(30):
            if i==600:
                break
            await getContent(data[j]["thumbURL"],i)
            i+=1
        pn+=30
        gsm = json["gsm"]
        if i==600:
            break

if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())

