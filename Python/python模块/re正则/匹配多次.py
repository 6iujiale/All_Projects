import re 
content="苹果是红色，香蕉是黄色，西瓜是绿色，葡萄是紫色,绿色色色"
#re.compile()是用来优化正则的，它将正则表达式转化为对象
p=re.compile(r"绿色+")#匹配绿色、绿色色色
for i in p.findall(content):
    print(i)