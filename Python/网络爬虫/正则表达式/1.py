''''
?	前一字符的0次或1次扩展
+	前一字符1次或无限次扩展
[]	匹配字符集，对单个字符
{m,n}	扩展前一字符m至n次（含n）	ab{1,2}c表示abc、abbc

'''

#一个最大长度为10的字符串，以HO开头，HO的后面的字符不能出现O或K；
import re
#1.写正则表达式
pat=r"(HO[^OK]{0,8})$"
#2.定要匹配的字符串
str1="HOppppp"
#3.进行检验
res=re.match(pat,str1) #match:匹配
#4、打印/处理检验结果
print(res)


#一个最大长度为10,最小长度是5的字符串，以HO开头，HO的后面的字符不能出现O或K；
import re
#1.写正则表达式
pat=r"(HO[^OK]{3,8})$"
#2.定要匹配的字符串
str1="HOppppp"
#3.进行检验
res=re.match(pat,str1) #match:匹配
#4、打印/处理检验结果
print(res)


#1、判断字符串是否以th开头 测：This is the bag   /this is a bag  
# pat=r"^(th)"
# str1="This is a bag  "
# res=re.match(pat,str1) 
# print(res)

#2、判断字符串是否有8位(包含字母、数字下划线或汉字)
pat=r"\w{8}"
str1="1234aaaa"
res=re.match(pat,str1) 
print(res)

#3、判断一个字符串，已he开头，中间l出现了0~5次,o结尾
pat=r"^hel{0,5}o$"
str1="helllo"
res=re.match(pat,str1) 
print(res)

#匹配字符串：x开头，a出现0或1次，b出现1次或多次，c出现0或多次，d出现3次，e至少出现2次，f出现0到3次。写5个不同的字符串检测成功和5个不同的字符串检测失败
pat=r"^xa?b+c*d{3}e{2}f{0,3}"
str1="xbdddee"
res=re.match(pat,str1) 
print(res)

#匹配0~99的字符串
pat=r"^[1-9]?\d"
str1="0"
res=re.match(pat,str1)
print(res)

