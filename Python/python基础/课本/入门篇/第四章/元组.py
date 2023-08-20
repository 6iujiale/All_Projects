#创建和删除
#访问
#修改
#推导式



#创建和删除
'''
versel=('少壮不努力,老大徒伤悲')
print(type(versel)) #type函数测试变量的类型  #输出为:<class 'str'>

versel=('少壮不努力,老大徒伤悲',)
print(type(versel)) #type函数测试变量的类型  #输出为:<class 'tuple'>
'''

#tuple()函数可直接将range()函数循环出来
print(tuple(range(2,21,2)))

#删除元组
number=(1,2,3,4,5,6)
print(type(number))
del number 


#访问元组元素
tuple=('python',28,'linux','java',15,'0707')
print(tuple[:5]) 

coffe=('蓝山','卡布奇诺','摩卡','哥伦比亚','拿铁','美式','拿铁','星冰乐')
print('你好欢迎光临！\n我们店里有:')
for index,item in enumerate(coffe):
    if index%2==0:
        print(item+'\t\t',end='')
    else:
        print(item+'\n')



#修改元组元素
#import 
#1.元组连接时，内容必须都是元组
#2.连接元组just only 1个元素时，don’t忘记后面的,号

coffe1=('蓝山','卡布奇诺','摩卡','哥伦比亚')
print('原元组',coffe1)
coffe2=('拿铁','美式','拿铁','星冰乐')
print('新元组',coffe2+coffe1)



#元组推导式
#导入随机模块，用元组随机输出1~100的数字
import random
number=(random.randint(1,100) for i in range(10))
number1=list(number)
print(number1)



#元组和列表的区别
#1.列表可变 元组不可变
#元组没有 append() extend() insert() remove()和pop()方法
#元组比列表访问和处理速度快。
