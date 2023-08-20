import requests
import os
import re
import shutil
#获取网页信息
def getHTML(weburl):
    hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"}
    r=requests.get(url=weburl,headers=hd)
    return r.text
#获取img信息
def getSrc(html):
    ex1='<div class="movie-list list">(.*?)</div>\s</div>'
    div=re.findall(ex1,html,re.S)
    ex2='<img src.*?" data-origin="(.*?)" alt="(.*?)" />'
    img_info=re.findall(ex2,div[0],re.S)#img_info包含网址与替代文本
    print(img_info)
    return img_info
#保存图片
def saveImg(srcs,foldername):
    hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.56"}
    folder="C:\\Users\\DELL\\Desktop\\"+foldername+"\\"
    if os.path.exists(folder):
        shutil.rmtree(folder)#true删除
    os.mkdir(folder)#false创建
    for img in srcs:
        r=requests.get(url=img[0],headers=hd).content
        (file,ext)=os.path.splitext(img[0])#获取图片扩展名.jpg
        # r=requests.get(url=file,headers=hd).content
        img_all=img[1]+ext#图片名.扩展名
        # print(file)
        # print(img[0])
        with open(folder+img_all,"wb") as f2:
            print("爬取成功----%s"%img_all)
            f2.write(r)
if __name__=="__main__":
    html=getHTML("https://www.douban.com/")
    srcs=getSrc(html)
    saveImg(srcs,"ljl豆瓣照片")


