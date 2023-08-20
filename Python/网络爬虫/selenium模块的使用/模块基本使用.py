# import selenium
# print(selenium.__version__)#版本号测试

""""
selenium
    find_element()   定位元素的参数
        dirver.find_element(By.XPATH,'//*[@id="touchnav-wrapper"]/header/div/div[3]/p').text

    find_elements() 

"""

#配置浏览器驱动
from selenium.webdriver.chrome.service import Service
from selenium import webdriver

#导入By类，用于查询网页元素
from selenium.webdriver.common.by import By

service=Service(executable_path="E:\tools\webdriver\bin")
dirver=webdriver.Chrome(service=service)

#打开网页get
dirver.get("https://www.python.org/")

#调用find_element()定位元素参数
tx=dirver.find_element(By.XPATH,'//*[@id="touchnav-wrapper"]/header/div/div[3]/p').text
print(tx)
tx=dirver.find_elements(By.XPATH,'//*[@id="touchnav-wrapper"]/header/div/div[3]/p')
print(tx[0].text)
#Xpath Copy Xpath
# //*[@id="touchnav-wrapper"]/header/div/div[3]