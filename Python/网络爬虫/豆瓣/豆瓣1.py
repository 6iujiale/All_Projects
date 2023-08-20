import requests
import re
import os
if __name__=="__main__":
#建立存放图片的文件夹
    if not os.path.exists('./filmspic'):
        os.mkdir('./filmspic')
#设定request的请求头和url 
    url='https://www.douban.com/'
    request_headers = {
  "User-Agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36"
}
#请求豆瓣首页
    page_html=requests.get(url=url,headers=request_headers).text
    ex='<div class="movie-list list">.*?</div>\s</div>'
    x=re.findall(ex,page_html,re.S)
    ex2='<div class="pic">.*? data-origin="(.*?)" alt.*?</div>'
    img_srcs=re.findall(ex2,x[0],re.S)
    print(img_srcs)
    for src in img_srcs:
        url_src=src
        pic=requests.get(url=src,headers=request_headers).content
        #生成图片名称
        pic_name=src.split('/')[-1]
        imgPath='./filmspic/'+pic_name
        with open(imgPath,'wb') as fp:
            fp.write(pic)
    print('ok')