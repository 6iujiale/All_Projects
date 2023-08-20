def test(num):
    result='hello'
    print('函数要返回的内存地址是%d'%(id(result)))
    return result
a=10
print('a变量保存数据的内存地址是%d'%id(a))
#定义变量接受返回值
r=test(a)
print('%s的地址是%d'%(r,id(r)))

