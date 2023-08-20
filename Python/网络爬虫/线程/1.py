""" 
协程
    在一个线程中如果遇到IO等待时间,切换不同的函数,提高运行销量。
asyncio
    执行方式1,只执行一个
        loop=asyncio.get_event_loop()
        loop.run_util_complete(函数名())
    执行方式2,执行多个函数
        tasks=[
            asynico.ensure_future(函数1())
            asynico.ensure_future(函数2())
        ]
        loop=asyncio.get_event_loop()
        loop.run_util_complete(asynico.wait(takes)))

关键字
    async&await关键字
        @asyncio.coroutine装饰器=》async def func1()
        yield from asyncio.sleep(2) #遇到IO耗时操作的时候,直接切换到tasks下一个任务 =》await asyncio.sleep(2)
"""
import requests
#下载图片的函数
def download_images():
    count=0
    for i in urllist:
        response=requests.get(i)
        count+=1
        print(f"正在下载第{count}张")
        with open(f"./images/{count}.jpg",mode="wb") as fp:
            fp.write(response.content)
if __name__=="__main__":
    urllist=[
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.jY0ROa1oYGITshgR0TCCKgHaE7?w=279&h=186&c=7&r=0&o=5&dpr=1.9&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.eZx7LBSGAKNWKdf5ETdfWQHaLH?w=124&h=186&c=7&r=0&o=5&dpr=1.9&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.4HQh-5kwbYxn4Qmn2uct8wHaLH?w=124&h=186&c=7&r=0&o=5&dpr=1.9&pid=1.7",
    ]
    download_images()