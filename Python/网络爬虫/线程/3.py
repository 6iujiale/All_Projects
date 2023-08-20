""" 
事件循环 
    任务列表=[任务1,任务2,任务3....]

    while True:
        for 就绪任务 in 可执行的任务列表:
            执行已经就绪的任务
        for 已完成的任务 in 已完成的任务列表:
            在任务列表中移除 已经完成的任务
        如果 人物列表 中的任务都已经完成 则终止循环
    
    loop = asyncio.get_event_loop()#去生成获取一个事件的循环
    loop.run_until_complete(main())#将任务放到 任务列表
"""

async def func1():
    print("快乐秃头ing")
result=func1()
loop=asyncio.get_event_loop()
loop.run_util_complete(函数名())
