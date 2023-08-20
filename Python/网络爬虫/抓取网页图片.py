#1.导入request库
import requests
#2.指定网页url并使用request服务器发送请求，并返回结果
# x=requests.get("http://www.news.cn/photo/2022-09/23/1129027053_16639257066751n.jpg")
# x=requests.get("https://ts1.cn.mm.bing.net/th/id/R-C.78fc100b0dfda99998d633633b54fff3?rik=5g4sc7a0roFN6w&riu=http%3a%2f%2fimage.hnol.net%2fc%2f2017-07%2f19%2f19%2f201707191925169681-2240800.jpg")
# # print(x.text)
# fp_path="C:\U\sers\\Lenovo\\Desktop\ljl\\1.jpg"
# with open(fp_path,"wb") as f2:
#     f2.write(x.content)
# f2.close()
import os
def getImage(p_url,fp_path):
    x=requests.get(p_url)
    with open(fp_path,"wb") as f2:
        f2.write(x.content)
    os.getcwd()
    return os.getcwd()
a=getImage("http://www.news.cn/photo/2022-09/23/1129027053_16639257066751n.jpg","C:\\Users\\Lenovo\\Desktop\\测试.jpg")