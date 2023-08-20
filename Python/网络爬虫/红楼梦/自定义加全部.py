import requests
from bs4 import BeautifulSoup
import re
import pymysql
#获取网页信息
def getText(weburl):
    r=requests.get(weburl)
    return r.text
#获取各章节网址
def getUrlList(webText):
    sp=BeautifulSoup(webText,"html.parser")
    id_a=sp.select("#content>a")
    all_url=[]#存储各章节网址
    for item in id_a:
        url="http://10.1.88.252:7000"+item["href"]
        all_url.append(url)
    return all_url
#获取章节内容
def getStory(urllist):
    contentAll=[]#接收文章信息
    hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.26"}
    for i in urllist:
        chapter=requests.get(i,headers=hd)
        chapter.encoding="ut3f-8"
        sp=BeautifulSoup(chapter.text,"html.parser")
        #标题
        id_title=sp.select("#title>h1")[0]
        contentAll.append(id_title.string)
        #主体内容
        id_content=sp.select("#content>p")
        for item in id_content: 
            if item.string !="\n" and item.string.isspace()==False:
                contentAll.append(item.string)
        # p=re.compile(r"\S*")
        # for i in p.findall(str(contentAll)):
        #     a=str(contentAll).replace(i,str(contentAll))
        
    return contentAll
#获取用户指示
def getChoice(urllist):#全部yes  自定义:no
    user=input("获取全部或自定义,请输yes或no：")
    if user=="yes":#全部章节
        all=getStory(urllist)
        return True,all
    elif user=="no":#自定义
        diyCh_url=[]#接收自定义章节网址
        while True:
            singleCh=int(input("请输入想获取的章节："))#自定义章节
            diyCh_url.append(urllist[singleCh-1])
            print("开始爬取第%d回小说,"%singleCh,"网址为：%s"%urllist[singleCh-1])
            tf_user=input("是否还继续,终止获取输no：")
            if tf_user=="no":
                break
        all=getStory(diyCh_url)
        return True,all
    else:
        print("用户指示错误")
        return False,None
#保存文件
def saveText(all,fname):
    with open("C:\\Users\\Lenovo\\Desktop\\"+fname,"w",encoding="utf-8") as f2:#不被覆盖a+
        [f2.write(i) for i in all]
if __name__=="__main__":
    text=getText("http://10.1.88.252:7000/%E7%BA%A2%E6%A5%BC%E6%A2%A6")
    urllist=getUrlList(text)
    flag,all=getChoice(urllist)
    if flag==True:
        saveText(all,"红楼梦.txt")
        print("小说爬取成功")
    else:
        print("小说爬取失败")





