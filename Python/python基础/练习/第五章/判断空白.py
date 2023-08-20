#判断空白字符
#.isspace
space_str="\n\t\r"
print(space_str.isspace())
if space_str.isspace()==False:
    print('成功')


# 判断字符串中是否只包含数字(如果string包含数字则返回True)
# 共同点：不能判断小数
#.isdecimal 全角数字
#.isdigit   全角数字、(1)、\u00b2
#.isnumeric 全角数字、汉字数字
num_str='1'
print(num_str)
print(num_str.isdecimal())
print(num_str.isdigit())
print(num_str.isnumeric())

num_str='2\u00b2'
print(num_str)
print(num_str.isdecimal()) #False
print(num_str.isdigit())   #True 
print(num_str.isnumeric()) #True

num_str='一千零一' #纯中文数字
print(num_str)
print(num_str.isdecimal()) #False
print(num_str.isdigit())   #False
print(num_str.isnumeric()) #True



