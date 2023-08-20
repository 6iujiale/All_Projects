"""
压缩包操作
    模块：os+zipfile
    创建压缩包
        z=zipfile.ZipFile(path,"w") #path:压缩包地址/压缩包名.zip
    添加文件到压缩包
        z.write()
    读取压缩包
        z.namelist()
    压缩包解压    
        z.extractall(path)
"""
import zipfile
import os

#1、backup目录新建压缩包素材文件夹
path="backup/压缩包素材/"
if os.path.exists(path) is False:
    os.mkdir(path)

#2、创建文本压缩包
zipName=path+"文本.zip"#压缩包地址
z=zipfile.ZipFile(zipName,"w")

path2="backup/文本/"
dir_list=os.listdir(path2)
print(dir_list)

with zipfile.ZipFile(zipName,"w") as z:
    os.chdir("backup/文本/")
    for i in dir_list:
        #添加文本到压缩包
        z.write(i)
        #读取压缩包
        files=z.namelist()
        print(files)
        #解压缩包
        z.extractall(path)

