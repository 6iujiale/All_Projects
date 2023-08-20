#上下文管理器
#文件读出or写入
f_path="./text/file0.txt"#路径
with open(f_path,"r") as fp:
    s=fp.read()
    print(s)


# from urllib import request
import requests as rq
print(rq.__version__)#request版本

