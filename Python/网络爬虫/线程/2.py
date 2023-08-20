import aiohttp
import asyncio
import gc

async def fetch(session,url):
    print("正在发送请求：")
    async with session.get(url,verify_ssl=False) as response:
        content=await response.content.read()
        for i in range(3):
            with open(f"./images2/{i}.jpg",mode="wb") as fp:
                fp.write(content)
                gc.collect()

async def main():
    async with aiohttp.ClientSession() as session:
        urllist=[
            "https://tse2-mm.cn.bing.net/th/id/OIP-C.jY0ROa1oYGITshgR0TCCKgHaE7?w=279&h=186&c=7&r=0&o=5&dpr=1.9&pid=1.7",
            "https://tse4-mm.cn.bing.net/th/id/OIP-C.eZx7LBSGAKNWKdf5ETdfWQHaLH?w=124&h=186&c=7&r=0&o=5&dpr=1.9&pid=1.7",
            "https://tse3-mm.cn.bing.net/th/id/OIP-C.4HQh-5kwbYxn4Qmn2uct8wHaLH?w=124&h=186&c=7&r=0&o=5&dpr=1.9&pid=1.7",
        ]
        tasks=[asyncio.create_task(fetch(session,i))for i in urllist]
        print("hah ")
        await asyncio.wait(tasks)
if __name__=="__main__":
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())
    
    
#     # await main()
# asyncio.run(main())
            