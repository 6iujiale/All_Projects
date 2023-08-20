#函数的返回值
    #返回值是函数完成运行后，最后给调用者的一个结果(return)
    #结束函数 

# def get_max(a,b):
#     v=a
#     if b>a:
#        v=b
#     return v
#     print('---emd---')
# i=get_max(5,8)
# print(i)

# def set_age(age):
#     if type(age)!=int or age<0:
#         print('年龄输入错误')
#         return age
# end=set_age(-1)
# print(end)

def func(a,b):
    return a+b,a-b
result,cha=func(1,2)
print('result是%s'%result,'\tcha是%s'%cha)

