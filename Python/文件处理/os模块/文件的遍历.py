'''
os.getcwd           获取当前路径
os.chdir            切换当前工作路径
os.mkdir            创建单个目录
os.makedirs         创建多层目录

os.path.isdir       是否为文件夹
os.path.isfile      是否为文件

os.path.getsize     获取文件大小

os.rmdir            删除单级目录
os.remove           删除一个文件
os.removedirs       递归删除空目录
'''
import os

# print(os.getcwd())#获取当前路径

#1、新建backup路径0,把当前1.py放到backup路径
""" os.mkdir('os模块/backup/')#新建目录
os.chdir('backup/')#切换当前工作路径
print(os.getcwd())
 """

# print(os.listdir("__pycache__/"))#查看文件夹文件,值为空默认为当前路径。

#2、 遍历__pycache__文件夹,找出文件夹和文件
dir_list=os.listdir("__pycache__/")
print(dir_list)
for i in dir_list:
    i = os.path.join("__pycache__/", i)#拼接目录
    if os.path.isdir(i)==True:
        print("%s是文件夹"%i)
    else:
        print("%s是文件"%i)







