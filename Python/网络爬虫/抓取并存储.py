""""
r.status
r.content

"""


#1.导入request库
import requests
#2.指定网页url并使用request服务器发送请求，并返回结果
x=requests.get("https://wwww.baidu.com/")
# print(x.text)
fp_path="d:\index0.txt"#路径保存到d盘
#3.保存数据文件
with open(fp_path,"w",encoding="utf-8") as f2:
    f2.write(x.text)
# fp=open(fp_path,"r",encoding="utf-8")
# a=fp.read()
# print(a)
f2.close()
