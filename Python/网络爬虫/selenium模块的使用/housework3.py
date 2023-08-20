from selenium.webdriver.chrome.service import Service
from selenium import webdriver
from selenium.webdriver.common.by import By
import requests
import os
import time

#获取图片信息
def getImgInfo(keyword):
    dirver.find_element(By.XPATH,'//*[@id="kw"]').send_keys(keyword)#输入框
    dirver.find_element(By.XPATH,'//*[@id="homeSearchForm"]/span[2]/input').click()#按钮
    #selenium调用js控制
    # js="window.scrollTo(0,1000)"
    """ js="var q=document.documentElement.scrollTop=100000"
    dirver.execute_script(js) """
    time.sleep(10)
    dirver.execute_script("window.scrollTo(0, document.body.scrollHeight)")	
    time.sleep(10)
    img_url=[]#接收img的url
    tx=dirver.find_elements(By.XPATH,'//*[@id="imgid"]/div/ul')[0]
    for i in tx.find_elements(By.TAG_NAME,"img"):#获取img标签
        if i.get_attribute("data-imgurl")!=None:
            img_url.append(i.get_attribute("data-imgurl"))#通过img标签,获取data-imgurl属性=图片url
    return img_url

#保存图片
def saveImg(folder,url):
    count=0#记录次数&图片命名
    try:
        os.makedirs(folder)
    except FileExistsError:
        print("文件夹已存在")
    finally:
        try:
            for i in url:
                r=requests.get(url=i).content
                count+=1
                # if count>8:#只爬八张图,第九张终止获取&保存
                #     break
                with open(folder+"甄嬛传头像"+str(count)+".jpg","wb") as f2:
                    f2.write(r)
                    print("成功爬取------第%d张图片"%count)
        except Exception:#出现异常,就跳过。
            print('出现异常直接跳过图片')
    # 捕获异常 os模块判断ta是否存在,true删除,false新建

if __name__=="__main__":
    service=Service(executable_path="D:\tools\webdriver\bin")
    dirver=webdriver.Chrome(service=service)#启动浏览器
    dirver.get("https://image.baidu.com/")#打开百度网页
    url=getImgInfo("甄嬛传")#图片搜索关键词
    print(url)
    saveImg("C:\\Users\\Lenovo\\Desktop\\ljl\\",url)#保存地址&img列表    