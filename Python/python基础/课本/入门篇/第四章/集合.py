# 集合的作用：
#     创建字典
#     添加元素(add(),update())


# 创建
# 添加、删除
# 交集、并集、差集运算



# 创建
#     {}
#     set()
# test=[1,2,3,4,5,6]
# test1=tuple(test)  #list tuple dict set
# print(test1)
# print(type(test1))  


# 添加、删除
    # setname.add()   
    # setname.update()
    # setname.remove() 
    # setname.pop()
    # setname.clear()
test=set([1,2,3,4,5,6])
test.add(7)
test.remove(7)
test.clear()
print(test)


# 交集、并集、差集运算
# & 既....又... 
# | 全都
# - 只
sl={1,2,3,4}
s2={1,3,5,6}
print('交集',sl&s2)
print('并集',sl|s2)
print('差集',sl-s2)






