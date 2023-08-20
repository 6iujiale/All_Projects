import requests
from bs4 import BeautifulSoup
import os
main_url="https://book.zongheng.com/showchapter/1244224.html"
hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.35"}
book_url=[]
contentAll=[]
def getText():
    r=requests.get(main_url,hd)
    return r.text
def getUrlList(a):
    sp=BeautifulSoup(a,"html.parser")
    title_a=sp.select(".col-4>a")
    for item in title_a:
        book_url.append(item["href"])

def getStory():
    user=int(input("请输入你想要的章节"))
    url=book_url[user]
    chapter=requests.get(url,headers=hd)
    chapter.encoding="utf-8"
    sp=BeautifulSoup(chapter.text,"html.parser")
    #标题
    title=sp.select(".title_txtbox")[0]
    contentAll.append(title.string)
    #主体内容
    id_content=sp.select(".content>p")
    for item in id_content:
        if item.string!="\n" and item.string.isspace()==False:
                contentAll.append(item.string.strip())

def  saveText():
    fl_path="绝世神医.txt"
    with open("C:\\Users\\Lenovo\\\Desktop\\"+fl_path,"w",encoding="utf-8") as f2:
        f2.write(str(('').join(str(x) for x in contentAll)))
    if os.path.getsize("C:\\Users\\Lenovo\\Desktop\\"+fl_path)==0:
        print("小说爬取失败")
    else:
        print("小说爬取成功")
def main():
    a=getText()
    getUrlList(a)
    getStory()
    saveText()
main()
# https://book.zongheng.com/chapter/1244224/70179664.html

# https://book.zongheng.comhttps://book.zongheng.com/chapter/1244224/70372918.html
