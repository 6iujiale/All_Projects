# def demo1(num):
#     print('在demo1函数内部的变量是%d'%num)
#     return num
# n1=demo1(10)
# print(n1)


def demo1():
    num=1
    print('在demo1函数内部的变量是%d'%num)
# print(num) 报错 找不到num变量
'''
局部变量 实在 函数内部 定义的变量，只能在函数内部使用
函数执行结束后，函数内部的局部变量，会被系统回收
不同的函数，可以定义相同的名字的局部变量，到那时各用各个的不会产生影响

'''
demo1()

#不同的函数，可以定义相同的名字的局部变量，到那时各用各个的不会产生影响
def demo2():
    num=1
    print('在demo1函数内部的变量是%d'%num)
demo2()