# num=10
# def demo1():
#     print('demo1 ==> %d'%num)

# def demo2():
#     print('demo2 ==> %d'%num)
# demo1()
# demo2()


num=10#全局变量 定义在所有函数上方
def demo1():
    #希望修改全局变量的值
    #python中，不允许直接修改全局变量的值
    #如果使用赋值语句，在函数内部，定义一个局部变量
    # num=99 
    global num
    #global 关键字修改全局变量的值
    #生命
    num=99 #赋值
    print('demo1 ==> %d'%num)#99

def demo2():
    print('demo2 ==> %d'%num)#10
demo1()
demo2()


gl_name='liujiale'
gl_num=10
def demo():
    print('%d'%num)
    print('%s'%title)
    print('%s'%gl_name)
title='黑马程序员'
demo()
# name='liujiale'