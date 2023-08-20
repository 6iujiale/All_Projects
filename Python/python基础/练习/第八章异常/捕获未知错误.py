#提前没有预判到的 所有错误类型
# except Exception as result:
#     print('未知错误%s'%result)

try:
    num=int(input('输入一个整数：'))
    result=8/num
    print(result)
except ValueError:
    print('请输入正确的整数')
#输出异常对象
except Exception as result:
    print('未知错误%s'%result)

#输入 0
#输出 未知错误division by zero