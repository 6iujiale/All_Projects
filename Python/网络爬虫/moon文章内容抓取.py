#moon
from bs4 import BeautifulSoup
import os
#getText:爬取一个页面内容，将内容放到htext 里返回出来
def getText(url):
    sp=BeautifulSoup(open(url,encoding='UTF-8'),"html.parser")
    id_a=sp.find_all(id='divContent')[0]
    id_b=id_a.contents
    htext=''
    for item in id_b:
        if item.string !=None:
#             print(item.string)
            htext=htext+'\n'+item.string
    return htext
# saveText:将文本str存到txt文件中
def saveText(str):
    f_path="moon.txt"
    with open(f_path,"w") as f2:
        f2.write(str)
        
def main():
    url='第二学期/课堂/text/moon.html'
    str=getText(url)
    print(str)
    saveText(str)
main()