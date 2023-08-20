from selenium.webdriver.chrome.service import Service
from selenium import webdriver
from selenium.webdriver.common.by import By
import requests
import os
import re 
import pymysql
import openpyxl

#打开网页
def openPage(weburl,keyword):
    dirver.get(weburl)
    dirver.find_element(By.XPATH,'//*[@id="key_S"]').send_keys(keyword)#输入框,输入关键字("科幻小说")
    dirver.find_element(By.XPATH,'//*[@id="form_search_new"]/input[10]').click()#按钮,点击跳转

#获取信息
def getInfo(page):
    count=0#记录次数&book命名
    book_info={}#记录book信息(url+txt+price)
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
            book_info["book"+str(count)]=[url,txt,price]
        next=dirver.find_element(By.LINK_TEXT,"下一页").click()
    # print(book_info)
    print("一共%d本书"%(len(book_info)))
    return book_info

#保存书本信息到本地(book.xlsx)
def saveTolocal(book_info):
    try:
        os.mkdir(folder)
    except FileExistsError:
        print("文件夹已存在")
    finally:
        try: 
            for key,values in book_info.items():
                r1=requests.get(url=values[0]).content
                with open(folder+str(key)+".jpg","wb") as f2:#保存图片
                    f2.write(r1)
            xlsx_folder=folder+"book.xlsx"
            wb=openpyxl.Workbook(xlsx_folder)
            if os.path.exists(xlsx_folder)==False:
                wb.save(xlsx_folder)
            bk=openpyxl.load_workbook(xlsx_folder)
            title=["序号","图片地址","文本","价格"]
            sheet1=bk["Sheet"]
            sheet1.append(title)
            count=1
            for key,values in list(book_info.items()):
                txt=re.sub('([^\u4e00-\u9fa5\u0030-\u0039])', '', str(values[1]))
                count+=1
                sheet1['A'+str(count)]=key
                sheet1['B'+str(count)]=values[0]
                sheet1['C'+str(count)]=txt
                sheet1['D'+str(count)]=values[2]
            bk.save(xlsx_folder)
            bk.close()
        except Exception:#出现异常，跳过
            print('出现异常跳过保存')
        else:
            print("本地保存成功")

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
            sql1_create_databases="create database if not exists DangBooks default character set 'utf16';"#创建数据库DangBooks,保存书本信息
            sql2_use_database="use DangBooks;"#使用DangBooks数据库
            sql3_create_table="create table if not exists books(img_url varchar(50),txt varchar(200),price varchar(10));"#创建books表,创建字段
            cursor.execute(sql1_create_databases)
            cursor.execute(sql2_use_database)
            cursor.execute(sql3_create_table)
            for key,values in book_info.items():
                sql4_insert_info="insert into books(img_url,txt,price) values(%s,%s,%s);"#添加信息到books表
                args1=(values[0],values[1],values[2])
                cursor.execute(sql4_insert_info,args=args1)
                db.commit()
            print("数据已成功添加")
        except Exception as e:
            print("错误："+str(e))
            db.rollback()
        finally:
            cursor.close()
            db.close()

if __name__=="__main__":
    service=Service(executable_path="d:\工具包\Python\webdriver\bin")
    dirver=webdriver.Chrome()#启动浏览器
    dirver.implicitly_wait(3)
    openPage("http://www.dangdang.com/","科幻小说")
    book_info=getInfo(5)#翻5页
    folder="C:\\Users\\Lenovo\\Desktop\\当当网\\"
    saveTolocal(book_info)
    saveToMySql()