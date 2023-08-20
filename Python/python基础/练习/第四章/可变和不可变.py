'''
不可变类型
    数字类型    int,bool,float.complex,long(2,x)
    字符串      str
    元组        tuple

可变类型
    列表        list
    字典        dict

    可变类型的数据变化，是通过方法实现的
    如果给一个可变类型的变量，赋值了一个新的数据，引用会修改
'''

#hash内置函数(哈希)
    #接收一个 不可变类型 的数据作为参数
    #返回 结果是一个整数
    #相同的内容 得到 相同的结果
    #不同的内容 得到 不同的结果

#键值对的 key 必须是不可变类型数据
#键值对的 value 可以是任意类型的数据

a=hash(1)
b=hash('ljl')
c=hash('ljl1')
print(a)
print(b)
print(c)





