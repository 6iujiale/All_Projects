import re
content="张三，13569452368。李四，14589632056。刘佳乐，13569874562，小乐，110118"
p=re.compile(r"1[35]\d{9}")
n=re.compile(r"[^0-9]+")
name=[]
phone=[]
for i in p.findall(content):
    phone.append(i)

for i in n.findall(content):
    name.append(i)
print(name,phone)