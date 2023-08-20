from bs4 import BeautifulSoup
soup=BeautifulSoup(open("数据采集与处理/课堂/text/10.html",encoding="utf-8"),"html.parser")
print(soup.prettify())

#a、获取网页的标题
print(soup.title)#<title>List</title>
print(soup.title.parent.name)#title
print(soup.title.parent.parent.name)#head

# #b、获取html页中的a标签；取得a标签的名字、获取a的父标签的名字、a的父亲的父亲的名字
print(soup.a.name)#a
print(soup.a.parent.name)#body
print(soup.a.parent.parent.name)#html

#c、获取a标签的所有属性；只获取href属性；只获取class属性
tag_a=soup.a
attrs=tag_a.attrs
print(attrs)
print(attrs["href"])#http://www.ssti.net.cn
print(attrs["class"][0])#py1

#d、获取a标签属性的类型；获取a标签的类型
print(type(tag_a.attrs))

#e、获取p标签的string，并查看他的属性是否为 bs4.element.NavigableString 
tag_p=soup.p
print(tag_p.string)#别忘了买这些东西哦

#f、获取h1标签的String属性，并查看他的属性是否为bs4.element.Comment 
tag_h1=soup.h1
print(tag_h1.string)#买什么

#获取ul标签内容
uls=soup.ul
# print(uls)

for i in uls.children:
    print(i)
# lis=soup.li
# print(lis.select)

