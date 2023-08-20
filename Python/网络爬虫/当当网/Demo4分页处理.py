from selenium.webdriver.chrome.service import Service
from selenium import webdriver
from selenium.webdriver.common.by import By
import requests
import os
import time

service=Service(executable_path="E:\tools\webdriver\bin")
dirver=webdriver.Chrome(service=service)
dirver.implicitly_wait(30)
dirver.get("http://search.dangdang.com/?key=%BF%C6%BB%C3%D0%A1%CB%B5&act=input")

# dirver.find_element(By.XPATH,'//*[@id="kw"]').send_keys("深圳技师学院")#搜索框
# dirver.find_element(By.XPATH,'//*[@id="su"]').click()#搜索按钮
#//*[@id="12810"]/div[5]
time.sleep(4)
#将滚动条移动到页面的指定位置(nav 导航)
target = dirver.find_element(By.XPATH,'//*[@id="12810"]/div[5]')
dirver.execute_script("arguments[0].scrollIntoView();", target) 
time.sleep(5)
# //*[@id="12810"]/div[5]/div[2]/div/ul/li[2]/a
for i in range(5):
    print(i)
    next_page=dirver.find_element(By.LINK_TEXT,'下一页')
    next_page.click()
    time.sleep(5)
