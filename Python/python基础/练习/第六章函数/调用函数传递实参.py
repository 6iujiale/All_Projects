#python中，函数的实参/返回值/都是靠 引用 来传递的

def test(num):
    print('在函数内部%d对应的内存地址是%d'%(num,id(num)))
a=10
#数据的地址本质就是一个数字
print('a变量保存数据的内存地址是%d'%id(a))
b=30
print('b变量保存数据的内存地址是%d'%id(b))
#调用test函数
#本质上传递的是实参保存数据的引用，而不是实参保存的数据！！！
test(a)
test(b)


