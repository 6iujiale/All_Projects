# 案例一
# 1、键盘录入一个路径
# 2、统计该路径下的文件和文件夹,以及分别的数量
# 3、统计当前路径下包含文件名称中包含demo的文件数量,注意不区分大小写

import os
import re

path=input("请输入绝对路径")
os.chdir(path)

file_list=[]#文件
dir_list=[]#文件夹

for file in os.scandir():
    if file.is_dir():#是文件夹就添加到dir_list
        dir_list.append(file.name)
    else:
        file_list.append(file.name)
print("文件夹的个数是%d"%(len(dir_list)),",文件夹是%s"%dir_list)
print("文件的个数是%d"%(len(file_list)),",文件是%s"%file_list)

demo_list=[]
for i in file_list:
    result=re.search(r"dmeo",i,re.I)#DENO demo
    if bool(result) is True:
        demo_list.append(i)
print(f"dmeo文件数量是:{len(demo_list)},文件夹是：{demo_list}")

""" import re
 
content = "abcabcabC"
rex = re.search("c", content, re.I)
print(rex.group()) """