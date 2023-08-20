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
#     打印错误信息
#     print('未知错误%s'%result)
# else:
#     没有异常才会执行的代码
# finally:
#     #无论是否有异常，都会执行的代码
#     print('无论是否有异常，都会执行的代码')

try:
    num=int(input('输入一个整数：'))
    result=8/num
    print(result)
except ValueError:
    print('请输入正确的整数')
#输出异常对象
except Exception as result:
    print('未知错误%s'%result)
else:
    print('尝试成功')
finally:
    print('无论是否有异常，都会被执行')