import requests
import os
def getHtml(p_url,fp_path):
    x=requests.get(p_url)
    with open(fp_path,"w",encoding="utf-8") as f2:
        f2.write(x.text)
    os.getcwd()
    return os.getcwd()
a=getHtml("https://wap.peopleapp.com/article/6874524/6738997","./text/1.txt")#d:\pic.jpg
print(a)

# def getSum(a,b):
#     result=a+b
#     return result
# c=getSum(1,2)
# print(c)

