from selenium.webdriver.chrome.service import *
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
import time

# service=Service(executable_path="E:\tools\webdriver\bin")
# dirver=webdriver.Chrome(service=service)
# dirver.get("https://www.baidu.com/")

# dirver.find_element(By.XPATH,'//*[@id="kw"]').send_keys("深圳技师学院",Keys.ENTER)#搜索框
# dirver.find_element(By.XPATH,'//*[@id="su"]').click()#搜索按钮

# # js="return action=document.body.scrollHeight"#获取页面初始高度
# # height = dirver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
# time.sleep(10)
# # js = "var q=document.body.scrollTop=10000"
# # dirver.execute_script(js)


driver = webdriver.Chrome()
driver.implicitly_wait(10)
# 设置窗口大小
# driver.set_window_size(800, 700)

driver.get('http://baidu.com')

# 百度输入框输入 selelnium python 回车
driver.find_element(By.XPATH,'//*[@id="kw"]').send_keys("深圳技师学院")
driver.find_element(By.XPATH,'//*[@id="su"]').click()


time.sleep(2)
# # 向下滚动200个像素
# driver.execute_script('window.scrollBy(0,200)')

target = driver.find_element(By.XPATH,'//*[@id="page"]/div')
driver.execute_script("arguments[0].scrollIntoView();", target) 

time.sleep(2)
time.sleep(2)

