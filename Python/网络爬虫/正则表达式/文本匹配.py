# with open("book.txt","r",encoding='utf-8') as f2:
#     content=f2.read()
#     print(content)

import os
import re 

fp=open("book.txt","r",encoding="utf-8")
str1=fp.read()
print(str1)
pat=r"《\w*》"
res=re.findall(pat,str1)
print(res)
for i in res:
    print(i)