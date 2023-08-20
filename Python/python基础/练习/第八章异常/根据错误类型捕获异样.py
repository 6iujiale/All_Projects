# try:
#     #尝试执行的代码
#     pass
# except 错误类型一:
#     #针对错误类型1，对应的代码处理
#     pass
# except 错误类型二:
#     #针对错误类型2，对应的代码处理
#     pass
# except Exception as result:
#     print('未知错误%s'%result)


try:
    num=int(input('输入一个整数：'))
    result=8/num
    print(result)
except ZeroDivisionError:
    print('除0错误')
except ValueError:
    print('输入非法字符错误')