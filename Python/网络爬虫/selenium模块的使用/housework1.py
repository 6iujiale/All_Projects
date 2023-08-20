#1.引入包 import
#2.打开网页 url
#3.用selenium在网页输入拉萨,点击“百度一下按钮”
#4.在结果页面，分析页面，找到前8张图片的img
#5.获取img的src属性
from selenium.webdriver.chrome.service import Service
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver import ActionChains
import time
import requests
import os
service=Service(executable_path="D:\tools\webdriver\bin")
dirver=webdriver.Chrome(service=service)#启动浏览器
dirver.get("https://image.baidu.com/")#打开百度网页
dirver.find_element(By.XPATH,'//*[@id="kw"]').send_keys("猫猫头像")#输入框
dirver.find_element(By.XPATH,'//*[@id="homeSearchForm"]/span[2]/input').click()#按钮
tx=dirver.find_elements(By.XPATH,'//*[@id="imgid"]/div/ul')[0]
# img=tx.find_elements(By.TAG_NAME,"img")
# print(img[0].get_attribute("src"))
img_url=[]
for i in tx.find_elements(By.TAG_NAME,"img"):
    img_info=i.get_attribute("data-imgurl")
    img_url.append(img_info)
print(img_url)
hd={"user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36"}
a=0
for img in img_url:
    folder="C:\\Users\\Lenovo\\Desktop\\ljl\\"
    try:
        r=requests.get(url=img,headers=hd).content
    except Exception:
        continue
    a+=1
    with open(folder+str(a)+".jpg","wb") as f2:
        f2.write(r)

