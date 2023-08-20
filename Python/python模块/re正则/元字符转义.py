import re 
content="苹果.是红色 香蕉.是黄色 西瓜.是绿色 葡萄.是紫色"
p=re.compile(r".{2}\.")
for i in p.findall(content):
    print(i)