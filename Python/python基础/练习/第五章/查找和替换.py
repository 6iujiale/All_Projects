hello_str='hello world' 
# #1.判断是否以指定字符串开始
# print(hello_str.startswith('h'))
# print(hello_str.startswith('H'))#python区分大小写 
# #2.判断是否以指定字符串结束
# print(hello_str.endswith('d'))
#3.查找指定字符串
print(hello_str.find('ello'))
#对比
print(hello_str.find('abc'))
# print(hello_str.index('abc'))
print(hello_str.rindex('ld')) #rindex=right index 从右边开始查找
# print(hello_str.rindex('abc'))
# #4.替换字符串
print(hello_str.replace('hello','python'))