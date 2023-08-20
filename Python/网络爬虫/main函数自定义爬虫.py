#1.向网页发送请求，获取网页文件
import requests
def getHtml(weburl,name):
    kv={
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.42"
    }
    res=requests.get(url=weburl,headers=kv)
    filepath="./第二学期/课堂/text/"+name+".html"
    with open(filepath,"w",encoding="utf-8") as fp:
        fp.write(res.text)
    print(filepath+"保存成功")

def main():
    url="http://www.kulemi.com/content/456391/"
    filename="三国"
    getHtml(url,filename)
main()