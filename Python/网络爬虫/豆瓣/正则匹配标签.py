import re
fp=open("数据采集与处理\课堂\豆瓣\movie.html","r",encoding="utf-8")
ht=fp.read()
# (.*?)可以重复的任意多的内容,匹配<div class="global-nav-items">开始,</div>结束,任意多的内容
# .*?匹配任意多个任意字符
#  ()匹配想要的内容
pt1=r'<div class="global-nav-items">(.*?)</div>' 
div=re.findall(pt1,ht,re.S)
# print(div)
pt2=r'href="(.*?)"'
pt3=r'<a href.*?">(.*?)</a>'
urls=re.findall(pt2,div[0],re.S)
# print(urls)
texts=re.findall(pt3,div[0],re.S)
print(texts) #找到a标签的文本内容
info=[]
for i in range(len(urls)):
    info.append([texts[i],urls[i]])
print(info)

#二维列表的网址
#使用正则表达式匹配div下的a标签取出href属性,定义一个info列表，通过循环来获取文本和网址。
#list,html导航栏的文本和网址

#贪婪模式 .*  最小匹配.*?
