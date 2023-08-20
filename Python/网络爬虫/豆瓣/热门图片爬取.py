import requests
from bs4 import BeautifulSoup
import os
def getText(weburl):
    hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"}
    r=requests.get(url=weburl,headers=hd)
    return r.text
def getImgUrl(webtext):
    # img_url=re.findall(r"https://?img.*(jpg|png)",webtext)
    sp=BeautifulSoup(webtext,"html.parser")
    img=sp.select("img")
    url=[]
    for i in img:
        url.append(i["src"])
    return url
def getImg(url):
    hd={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"}
    for i in url:
        img=requests.get(i,headers=hd)
        # if os.path.exists("C:\\Users\\Lenovo\\Desktop\\照片"):
        #    print("文件夹存在")
        #    break
        # else:
        #     os.makedirs("C:\\Users\\Lenovo\\Desktop\\照片")
        #返回path最后的文件名
        with open("C:\\Users\\Lenovo\\Desktop\\照片\\"+os.path.basename(i),"wb") as f2:
            f2.write(img.content)
if __name__=="__main__":
    webtext=getText("https://movie.douban.com/")
    url=getImgUrl(webtext)
    getImg(url)