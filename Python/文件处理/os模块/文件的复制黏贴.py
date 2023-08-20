#创建
    # os.mkdir
    # os.makedirs
#复制
    # shutil.copy
    # shutil.copytree
#移动
    # shutil.move
#删除
    # os.rmdir      删除单级目录(必须为空)
    # os.removedirs 递归删除空目录
    # os.remove     删除空文件
    # shutil.rmtree 递归删除目录(可不为空)

import os

#1、创建
# if os.path.exists("backup") is False:
#     #makedirs 创建多层文件夹
#     os.makedirs("backup/package01")#mkdir 创建单个文件夹  makedirs 创建多层文件夹

#2、删除
#删除backup文件夹下的package01
import os
#removedirs递归删除空目录
# os.removedirs("backup/children/child/")#报错 不是空目录无法删除

#rmdir删除单级目录(目录为空)
# os.rmdir("backup/txt文本")

#remove删除一个文件,不能是文件夹
# os.remove("backup/dmeo0.txt")

#os.remove删除非空文件时会出现无法访问的问题
#可以使用shutil库的rmtree进行操作

# 删除backup/下的所有文本文件和目录
# import shutil 
# shutil.rmtree("backup/children/")

""" path="backup/children/child"
if os.path.exists(path) is False:
    os.makedirs(path)
for i in range(10):
    txt="demo{}.txt".format(i)
    file=open(path+txt,"w")
    file.write("hello world")
print(os.listdir(path)) """

""" #复制
#拷贝backup/demo01.txt到backup/children/
import shutil
# shutil.copy("backup/demo0.txt","backup/children/")#拷贝文件
#拷贝__pycache__文件夹到.history文件夹
shutil.copytree("__pycache__",".history/我是复制的文件夹")#拷贝文件夹 copytree要加文件命名
# shutil.rmtree(".history") """

#移动
import shutil
#将backup/children/demo0.txt移动到backup/
shutil.move("backup/children/",".history/")
