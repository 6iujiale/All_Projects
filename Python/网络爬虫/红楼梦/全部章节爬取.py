import requests
from bs4 import BeautifulSoup
import os
def getText(weburl):
    r=requests.get(weburl)
    return r.text
def getUrlList(webText):
    sp=BeautifulSoup(webText,"html.parser")
    id_a=sp.select("#content>a")
    all_url=[]
    for item in id_a:
        url="http://10.1.88.252:7000"+item["href"]
        all_url.append(url)
    return all_url
def getStory(url):
    contentAll=[]
    for i in url:
        hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.26"}
        chapter=requests.get(i,headers=hd)
        chapter.encoding="utf-8"
        sp=BeautifulSoup(chapter.text,"html.parser")
        #标题
        id_title=sp.select("#title>h1")[0]
        contentAll.append(id_title.string)
        #主体内容
        id_content=sp.select("#content>p")
        for item in id_content: 
            # if item.string !="\n" and item.string.isspace()==False:
                contentAll.append(item.string)
    return contentAll
if __name__=="__main__":
    a=getText("http://10.1.88.252:7000/%E7%BA%A2%E6%A5%BC%E6%A2%A6")
    b=getUrlList(a)
    c=getStory(b)
    fl_path="红楼梦.txt"
    with open("C:\\Users\\Lenovo\\Desktop\\"+fl_path,"w",encoding="utf-8") as f2:
        f2.write(str(('').join(str(x) for x in c)))
    if os.path.getsize("C:\\Users\\Lenovo\\Desktop\\"+fl_path)==0:
        print("小说爬取失败")
    else:
        print("小说爬取成功")