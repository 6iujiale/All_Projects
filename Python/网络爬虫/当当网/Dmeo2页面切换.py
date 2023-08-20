from selenium.webdriver.chrome.service import *
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import requests
import os
import time 

"""
dirver.back()#页面回退
dirver.forward()#页面前进
dirver.switch_to.window(dirver.window_handles[1])#切换到第二个打开的窗口
dirver.switch_to.window(dirver.window_handles[0])#切换初始窗口

"""
service=Service(executable_path="E:\tools\webdriver\bin")
dirver=webdriver.Chrome(service=service)
dirver.get("https://www.baidu.com/")

dirver.find_element(By.XPATH,'//*[@id="kw"]').send_keys("深圳技师学院")#搜索框
dirver.find_element(By.XPATH,'//*[@id="su"]').click()#搜索按钮
# dirver.back()#页面回退
# time.sleep(10)
# dirver.forward()#页面前进
time.sleep(5)
dirver.find_element(By.XPATH,'//*[@id="2"]/div/div/h3/a').click()
time.sleep(20) 
dirver.switch_to.window(dirver.window_handles[1])
txt=dirver.find_element(By.XPATH,"/html/body/div[3]/div[2]/div/div[1]/div[4]/div").text
print("百度文本是："+txt)
time.sleep(10) 
dirver.switch_to.window(dirver.window_handles[0])
txt2=dirver.find_element(By.XPATH,'//*[@id="1"]/div/div[1]/div[2]/div/span').text
print("\n"+txt2)
dirver.close()
# dirver.back()#页面回退
# time.sleep(10)
