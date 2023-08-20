import re
content="<html><head><title></title></head></html>"
p=re.compile(r"<.*?>")#非贪婪模式?（匹配0个或一个字符）
for i in p.findall(content):
    print(i)


