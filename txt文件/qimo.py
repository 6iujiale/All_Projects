import threading
import pymysql
from tkinter import *
from bs4 import BeautifulSoup
import requests
import re
def rankingDisplay(bangdanRank):#获取榜单信息
    req = requests.get(url = target,verify=False)#使用requests访问网站
    bf = BeautifulSoup(req.text)#使用BeautifulSoup来匹配数据
    texts = bf.find_all('div', class_ = 'block bd')[bangdanRank]#查询符合条件的内容
    bf2 =BeautifulSoup(str(texts))#二次查询 查询所有榜单标题
    titles=bf2.find_all('a')
    rankName=bf2.find_all('h2')[0].text
    types=bf2.find_all('span',class_='rate')
    windowDisplay(rankName,titles,types)
def windowDisplay(rankName,titles,types):#将信息窗口化显示
    top=Tk()#创建窗体并设置大小
    top.geometry("1200x600")
    #command=lambda:rankingDisplay(0)这样的写法是使command指向了一个匿名函数
    #这个匿名函数调用了我们要使用的函数并传递所需的参数，直接给command绑定
    #函数会在代码载入时就被调用而且无法传参
    Button(top,width=5,text='总榜',command=lambda:openNewWindow(0)).grid(row=0,column=0)
    Button(top,width=5,text='玄幻榜',command=lambda:openNewWindow(1)).grid(row=0,column=1)
    Button(top,width=5,text='修真榜',command=lambda:openNewWindow(2)).grid(row=0,column=2)
    Button(top,width=5,text='都市榜',command=lambda:openNewWindow(3)).grid(row=0,column=3)
    Button(top,width=5,text='穿越榜',command=lambda:openNewWindow(4)).grid(row=0,column=4)
    Button(top,width=5,text='网游榜',command=lambda:openNewWindow(5)).grid(row=0,column=5)
    Button(top,width=5,text='科幻榜',command=lambda:openNewWindow(6)).grid(row=0,column=6)
    Button(top,width=5,text='其他榜',command=lambda:openNewWindow(7)).grid(row=0,column=7)
    Button(top,width=5,text='悬疑榜',command=lambda:openNewWindow(8)).grid(row=0,column=8)
    Button(top,width=5,text='女生榜',command=lambda:openNewWindow(9)).grid(row=0,column=9)
    Label(top,text=rankName+' 类型统计：').grid(row=1,column=0)
    c=0#设置列
    r=2#设置行
    number=0#判断是否切换行的标记
    paiming=1#排名
    for title in titles:
        rowsUsed=0#记录已经使用了多少行
        if number%5==0:#换行
            c=0
            r+=8
        Label(top,text=str(paiming)+"."+title.text).grid(row=r,column=c)
        paiming+=1
        r+=1
        rowsUsed-=1
        if re.search(r'"(.+)".*?',str(title))!=None:#使用正则表达式，如果正则匹配有效，则使用数据
            detailedUrl=re.search(r'"(.+)".*?',str(title)).group().strip('"')
            if (detailedUrl.startswith("/")):#判断获取的连接是否符合访问规范
                detailedUrl='https://www.bqkan8.com'+detailedUrl#补全连接
                addButton(detailedUrl,c,r,top,title.text)
                detailedReq= requests.get(url = detailedUrl)#设置编码格式 防止中文乱码
                detailedReq.encoding='gbk'
                detailedBf=BeautifulSoup(detailedReq.text)
                detailedTexts=detailedBf.find_all('div',class_="small")
                detailedBf2=BeautifulSoup(str(detailedTexts))
                detailedTexts2=detailedBf.find_all('span')[1:6]
                for text in detailedTexts2:
                    Label(top,text=text.text).grid(row=r,column=c)
                    r+=1
                    rowsUsed-=1
        r+=rowsUsed#将行数恢复初始状态，开始显示下一本书
        c+=1
        number+=1
    leixings={}#记录类型用的字典
    for type in types:#开始统计类型
        if (type.text in leixings):
            leixings[type.text]+=1
        else:
            leixings[type.text]=1
    sum=1
    for key in leixings:
        Label(top,text=key+':'+str(leixings[key])).grid(row=1,column=sum)
        sum+=1
    top.mainloop()
def addButton(detailedUrl,c,r,top,bookName):#添加下载按钮
    Button(top,width=15,text='下载前十章试读',command=lambda:startDownLoad(detailedUrl,bookName)).grid(row=r+6,column=c)
def downloadTop10(detailedUrl,bookName):#下载前十章
    databaseWrite(bookName)#数据库写入
    req = requests.get(url = detailedUrl)
    req.encoding='gbk'
    html = req.text
    div_bf = BeautifulSoup(html)
    div = div_bf.find_all('div', class_ = 'listmain')[0]
    a_bf = BeautifulSoup(str(div))
    a = a_bf.find_all('a')[12:22]#获取前十章的连接
    aurlList=[]#存储链接的列表
    anameList=[]#储存章节名的列表
    threads=[]#储存线程的列表
    for each in a:
        aUrl=re.search(r'"(.+)".*?',str(each)).group().strip('"')#使用正则获取a标签链接
        if (aUrl.startswith("/")):#判断获取的连接是否符合访问规范
            aUrl='http://book.zongheng.com/rank.html'+aUrl#补全连接
        aurlList.append(aUrl)
        anameList.append(each.string)
    for i in range(len(aurlList)):
        thread=MyThread(aurlList[i],anameList[i],bookName)#创建线程开始下载
        thread.start()
        threads.append(thread)
    for t in threads:
        t.join()
    print('下载完毕')
def openNewWindow(bangdanRank):
    t=openNewWindowThread(bangdanRank)
    t.start()
    t.join
def startDownLoad(detailedUrl,bookName):
    t=startDownLoadThread(detailedUrl,bookName)
    t.start()
    t.join
class MyThread(threading.Thread):#定义下载线程
    def __init__(self,aUrl,aName,bookName):
        threading.Thread.__init__(self)
        self.aUrl=aUrl
        self.aName=aName
        self.bookName=bookName
    def run(self):
        fileWrite(self.aUrl,self.aName,self.bookName)
class openNewWindowThread(threading.Thread):#定义打开新窗口的线程
    def __init__(self,bangdanRank):
        threading.Thread.__init__(self)
        self.bangdanRank=bangdanRank
    def run(self):
        rankingDisplay(self.bangdanRank)
class startDownLoadThread(threading.Thread):#定义下载按钮的线程
    def __init__(self,detailedUrl,bookName):
        threading.Thread.__init__(self)
        self.detailedUrl=detailedUrl
        self.bookName=bookName
    def run(self):
        downloadTop10(self.detailedUrl,self.bookName)
def fileWrite(aUrl,aName,bookName):#文件写入方法
    try:
        req = requests.get(url = aUrl) 
        req.encoding='gbk'
        html = req.text
        bf = BeautifulSoup(html)
        texts = bf.find_all('div', class_ = 'showtxt')
        #replace(‘\xa0’*8,’\n\n’)就是去掉八个空格符号，并用回车代替
        text=texts[0].text.replace('\xa0'*8,'\n\n')
        path="D:\\book\\"+str(bookName)+' '+str(aName)+'.txt'
        with open(path,'w') as file:
            file.write(text)
            file.close()
    except Exception:
        print("文件下载出现异常")
def databaseWrite(bookName):#数据库写入方法
# 连接数据库
    try:
        connection = pymysql.connect(host='localhost',
                                    port=3306,
                                    user='root',
                                    password='123456',
                                    database='python',
                                    cursorclass=pymysql.cursors.DictCursor)
        with connection:
            with connection.cursor() as cursor:
                sql = "INSERT INTO `book` (`bookName`) VALUES (%s)"
                cursor.execute(sql, (bookName))
            connection.commit()
    except:
        print("数据库操作出现异常")

if __name__ == "__main__":
    target = 'https://www.qidian.com/'#目标网站
    #设置第几个榜单
    bangdanRank=6
    rankingDisplay(bangdanRank)