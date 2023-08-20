from selenium.webdriver.chrome.service import Service
from selenium import webdriver

from selenium.webdriver.common.by import By

import os
import shutil

service=Service(executable_path="E:\tools\webdriver\bin")
dirver=webdriver.Chrome(service=service)

dirver.get("https://www.douban.com/")
tx=dirver.find_elements(By.XPATH,'//*[@id="anony-movie"]/div[1]/div[3]/div')[0]
img=tx.find_elements(By.TAG_NAME,"img")
for i in img:
    print(i.text)
