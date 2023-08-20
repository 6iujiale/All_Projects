from selenium.webdriver.chrome.service import Service
from selenium import webdriver

from selenium.webdriver.common.by import By

from selenium.webdriver import ActionChains


import os
import shutil
import time

service=Service(executable_path="E:\tools\webdriver\bin")
dirver=webdriver.Chrome(service=service)

dirver.get("https://www.baidu.com/")


#找input
# inp=dirver.find_element(By.XPATH,'//*[@id="kw"]')
# inp.send_keys("abc").perform()

dirver.find_element(By.XPATH,'//*[@id="kw"]').send_keys("刘佳乐")#输入框
dirver.find_element(By.XPATH,'//*[@id="su"]').click()#按钮
time.sleep(5)

# #更多
# tx1=dirver.find_element(By.XPATH,'//*[@id="s-top-left"]/div/a')
# tx2=dirver.find_element(By.XPATH,'//*[@id="s-top-more"]/div[2]/a[1]')

# ActionChains(dirver).move_to_element(tx1).click(tx2).perform()

