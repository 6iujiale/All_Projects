from selenium.webdriver.chrome.service import Service
from selenium import webdriver
from selenium.webdriver.common.by import By
import requests
import os
import re 
import pymysql
import PyQt5
#打开网页
def openPage(weburl,keyword):
    dirver.get(weburl)
    dirver.find_element(By.XPATH,'//*[@id="key_S"]').send_keys(keyword)#输入框,输入关键字("科幻小说")
    dirver.find_element(By.XPATH,'//*[@id="form_search_new"]/input[10]').click()#按钮,点击跳转

#获取信息(url+txt+price)
def getInfo(page):
    count=0#记录次数&book命名
    book_info1={}#接收前150本
    book_info2={}#接收后150本
    for i in range(page):
        print("正在爬取第"+str(i+1)+"页"+"\n**********************")
        ul=dirver.find_element(By.XPATH,'//*[@id="component_59"]')
        for j in ul.find_elements(By.TAG_NAME,"li"):
            count+=1
            url="http:"+str(j.find_element(By.TAG_NAME,"img").get_attribute('data-original'))#图片url
            if url=="http:None":
                url=j.find_element(By.TAG_NAME,"img").get_attribute('src')
            txt=j.find_element(By.CSS_SELECTOR,"a").get_attribute("title")#标题
            price=j.find_element(By.CSS_SELECTOR,"span.search_now_price").text#价格
            if count<=150:
                book_info1["book"+str(count)]=[url,txt,price]#前150本
            else:
                book_info2["book"+str(count)]=[url,txt,price]#后150本
        next=dirver.find_element(By.LINK_TEXT,"下一页").click()
    # print(book_info1,book_info2)
    print("一共%d本书"%(len(book_info1)+len(book_info2)))
    return book_info1,book_info2

#保存书本信息到本地
def saveTolocal(book_info1,book_info2):
    try:
        os.mkdir(folder)
    except FileExistsError:
        print("文件夹已存在")
    finally:
        try:
            with open(folder+"当当.txt","w",encoding="utf-8") as f2:#保存title&price
                for key1,key2 in zip(book_info1.keys(),book_info2.keys()):
                    Info1=re.sub('([^\u4e00-\u9fa5\u0030-\u0039])', '', str(book_info1[key1][1]))#去除符号
                    Info2=re.sub('([^\u4e00-\u9fa5\u0030-\u0039])', '', str(book_info2[key2][1]))
                    f2.write(key1+"："+Info1+str(book_info1[key1][2])+"\n")#写入&格式化输出&换行
                    f2.write(key2+"："+Info2+str(book_info1[key1][2])+"\n")
            for key1,key2 in zip(book_info1.keys(),book_info2.keys()):    
                #获取url: print(key1,book_info1[key1][0])
                r1=requests.get(url=book_info1[key1][0]).content#获取图片(前150)
                r2=requests.get(url=book_info2[key2][0]).content#获取图片(后150)
                with open(folder+str(key1)+".jpg","wb") as f2:#保存图片(前150)
                    f2.write(r1)
                with open(folder+str(key2)+".jpg","wb") as f2:#保存图片(后150)
                    f2.write(r2)
        except Exception:#出现异常，跳过
            print('出现异常跳过保存')

#保存书本信息到数据库
def saveToMySql():
    try:
        db = pymysql.connect(
            host = "127.0.0.1",
            user = "root",
            password = "123456",
            database = "test",
            charset = "utf8",
            port = 3306
        )
    except Exception as e:
        print("数据库连接失败",e)
    else:
        print("数据库连接成功")
        try:
            cursor=db.cursor()
            sql0_drop_databases="drop database if exists dangbooks;"#防止报错,如果数据库存在则删除
            sql1_create_databases="create database if not exists DangBooks default character set 'utf16';"#创建数据库DangBooks,保存书本信息
            sql2_use_database="use DangBooks;"#使用DangBooks数据库
            sql3_create_table="create table books(img_url varchar(50),txt varchar(200),price varchar(10));"#创建books表,创建字段
            cursor.execute(sql0_drop_databases)
            cursor.execute(sql1_create_databases)
            cursor.execute(sql2_use_database)
            cursor.execute(sql3_create_table)
            for key1,key2 in zip(book_info1.keys(),book_info2.keys()):
                sql4_insert_info="insert into books(img_url,txt,price) values(%s,%s,%s);"#添加信息到books表
                args1=(book_info1[key1][0],book_info1[key1][1],book_info1[key1][2])
                args2=(book_info2[key2][0],book_info2[key2][1],book_info2[key2][2])
                cursor.execute(sql4_insert_info,args=args1)
                cursor.execute(sql4_insert_info,args=args2)
                db.commit()
            print("数据已成功添加")
        except Exception as e:
            print("错误："+str(e))
            db.rollback()
        finally:
            cursor.close()
            db.close()

if __name__=="__main__":
    service=Service(executable_path="D:\tools\webdriver\bin")
    dirver=webdriver.Chrome(service=service)#启动浏览器
    dirver.implicitly_wait(10)
    openPage("http://www.dangdang.com/","科幻小说")
    book_info1,book_info2=getInfo(5)#翻5页
    folder="C:\\Users\\Lenovo\\Desktop\\当当网_ljl\\"
    saveTolocal(book_info1,book_info2)
    saveToMySql()