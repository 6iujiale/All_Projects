import re
content="001 张三，002 王五，003 老刘"
p=re.compile(r"^\d+",re.M)
for i in p.findall(content):
    print(i)

