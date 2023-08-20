import re
pat=r"www.\w*.com$"
str1="www.baidu.com"
res=re.match(pat,str1)
print(res)

pat=r"www(.\w*)+.com$"
str1="www.baidu.baike.com"
res=re.match(pat,str1)
print(res)

pat=r"www(.\w*)+(.com|.cn|.org)"
str1="www.baidu.baike.org"
res=re.match(pat,str1)
print(res)


pat=r"\w*@163.com"
str1="xxx@163.com"
res=re.match(pat,str1)
print(res)

pat=r"\w*@163(.vip)?.com"
str1="xxx@163.vip.com"
res=re.match(pat,str1)
print(res)

pat=r"\w*@163.com|[1-9]\d{9}@qq.com"
str1="2621286438@qq.com"
res=re.match(pat,str1)
print(res)
