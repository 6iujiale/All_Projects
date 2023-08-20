import os
import re
path="backup/"
path3=path+"txt文本/"
if os.path.exists(path)==False:
    os.mkdir(path)
    os.mkdir(path3)
""" for i  in range(10):
    txt=path3+"dmeo{}.txt".format(i)
    file=open(txt,"w") """
dir_list=os.listdir(path)
print(dir_list)

file=0
dir=0
demo_dir=0
for i  in dir_list:
    path2=os.path.join(path,i)
    if os.path.isdir(path2) is True:
        dir+=1
        dir2_list=os.listdir(path2)
        print(dir2_list)
        for b in dir2_list:
            result=re.search(r"dmeo",b,re.I)#DENO demo
            if bool(result) is True:
                demo_dir+=1
                print(b+"文件包含demo(不区分大小写)")
    elif os.path.isfile(path2) is True:
        file+=1
    else:
        print("既不是文件又不是文件夹")
print("文件的个数是%d"%file)
print("文件夹的个数是%d"%dir)
print("文件夹包含demo字符的个数是%d"%demo_dir) 