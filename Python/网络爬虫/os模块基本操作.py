#文件的基本操作
import os
#getcwd    获取当前位置
print(os.getcwd())

#listdir   列举某个路径里的内容
print(os.listdir(os.getcwd()))

#chdir      更改路径，更换目录
newdir='E://java'
print(os.chdir(newdir))
print(os.getcwd())
print(os.listdir(os.getcwd()))
