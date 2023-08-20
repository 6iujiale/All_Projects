from selenium.webdriver.chrome.service import Service
from selenium import webdriver

from selenium.webdriver.common.by import By

import os
import shutil
import time

service=Service(executable_path="D:\tools\webdriver\bin")
dirver=webdriver.Chrome(service=service)

dirver.get("https://www.gushiwen.cn/")
time.sleep(3)
# tx=dirver.find_elements(By.XPATH,'/html/body/div[1]/div/div[2]/div[1]')
# print(tx[0].text)

#调用find_elements方法，查询网页元素，一个classname是son1的div的标签
tx=dirver.find_elements(By.CLASS_NAME,'son1')[0]
# print(tx.text)
#在div.son这个标签里提取a
ass=tx.find_elements(By.TAG_NAME,"a")
for i in ass:
    print(i.text)



