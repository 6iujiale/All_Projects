# 案例2
# 1．键盘输入一个路径
# 2．搜索该路径下文件大小超过50M的zip文件
# 3．搜索该路径下最后修改日期在30天前的文件
# 4．打印显示2，3的文件

import os
import datetime

path=input("请输入路径：")
#切换当前工作路径
os.chdir(path)
#以列表的形式展开路径下的文件
dir_list=os.listdir()
print(dir_list)
 
for i in dir_list:
    i=os.path.join(path,i)
    # print(i)
    file_size=os.path.getsize(i)
    # #获取当前日期
    # today=datetime.datetime.now()
    # #获取文件最近的修改日期 
    # file_day=datetime.datetime.fromtimestamp(os.stat(i).st_mtime)
    if(file_size>52428800):
        print(f"{i}文件大小超过了50MB") 
    # E:\\ljl_project\\

""" file_size=os.path.getsize("__pycache__/测试文本.txt")
print(file_size) """

""" import datetime
#获取当前日期
today=datetime.datetime.now()
print(today)

import os 
#获取文件的时间属性
day=os.stat("__pycache__/测试文本.txt").st_mtime#文件的最新修改时间
t=datetime.datetime.fromtimestamp(day)
print(t)#0:29:15.998930 """

