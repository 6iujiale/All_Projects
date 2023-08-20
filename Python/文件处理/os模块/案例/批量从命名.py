# 案例三
# 1．键盘输入一个路径
# 2．获取里面所有的mp4文件
# 3．重命名mp4文件在每个文件前面添加前缀，前缀就是文件最后修改的年月日（如：2021—08—18＿西游记01．mp4）
# 4．新建文件夹：最新视频
# 5．将重命名的视频批量移动到最新视频文件夹

import os
import time 

""" path=input("请输入路径：")
if os.path.exists(path) is True:
    dir_list=os.listdir(path)
    for file in dir_list:
        if file.endswith(".mp4"):
            new_dir=path+"最新视频/"
            if os.path.exists(new_dir) is False:
                os.mkdir(new_dir)
            path2=os.path.join(path,file)
            t=os.path.getmtime(path2)
            # timeStruce=time.localtime(t)
            times=time.strftime("%Y-%m-%d",time.localtime(t))
            # os.rename(src,dst) 要修改的目录名称 dst修改后的目录名称
            new_file_name=times+"_素材.mp4"
            new_file=os.rename(path2,new_dir+new_file_name)
else:
    print("路径不存在请重试") """

# 案例二
#backup路径小的demo文本进行重命名-》文本1.txt  文本2.txt
path="backup/"
os.chdir(path)#切换当前工作路径
dir_list=os.listdir()
i=1
for file in dir_list:
    i+=1
    os.rename(file,f"文本{i}.txt")



