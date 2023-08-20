'''
price=8.5
weight=7.5
money=str(price*weight-5)
print('总金额为：'+money+'元')

price=8.5
weight=7.5
money=price*weight
print('苹果单价%.2f元一斤，购买了%.2f斤，需要%.2f元。' % (price,weight,money))
'''



'''格式化字符串
name="刘佳乐"
print('我的名字叫%s,请多多关照！' % name)

student_no=1
print('我的学号是%d'%student_no)

scale=0.25
print('数据比例是%.2f%%' %(scale*100))
'''



'''
age=int(input('请输入年龄：'))
print('可以进酒吧嗨皮') if age>=18 else print('回家写作业')
print('运行结束，用户年龄是%d岁' % age)


age=int(input())
print('年龄正确') if age>=0 and age<=12 else print('年龄错误')


python_score=int(input())
c_score=int(input())
print('合格') if python_score>=60 or c_score>=60 else print('不及格')


is_employee=False
if not is_employee:
    print('非本公司人员，请勿入内')


holiday_name='情人节'
if holiday_name=="情人节":
    print('买玫瑰')
    print('看电影')
elif holiday_name=='平安夜':
    print('买苹果')    
    print('吃大餐')
elif holiday_name=='生日':
    print('买蛋糕')
else:
    print('其他的日子每天都是节日啊......')
print('早日分手吧！！！太可恶了欺负单身狗')    


has_ticket=True
if has_ticket:
    print('车票检查通过，开始安检')
    knife_length=int(input())
    print('不允许上车') if knife_length>=20 else print('允许上车') 
else:
    print('请先买票')
'''


'''
#import导入工具包，randon(随机数字)
import random
player=int(input('请输入您要出的拳 石头(1) /剪刀(2) /布(3) :'))
computer= random.randint(1,4)
print('玩家选择的拳头是 %d , 电脑出的拳是%d' % (player,computer))
if ((player ==1 and computer ==2 )
        or (player==2 and computer==3)
        or (player==3 and computer==1)):
    print('玩家胜利')
elif ((player ==1 and computer ==1 ) 
        or (player==2 and computer==2) 
        or (player==3 and computer==3)):
    print('平局')
else:
    print('电脑胜利')
'''



'''
#0~100相加
sum=0
i=0
while i<=100:
    print(i)
    sum+=i
    i+=1
print(sum)
print('end') 

#0~100 偶数相加
sum=0
i=0
while i<=100:
    if i%2==0:
        print(i)
        sum+=i
    i+=1
print(sum)


i=0
while i<10:
    if i==3:
       # break(打破) / continue(跳过) 
    print(i)
    i+=1
print('结束')  

#for循环实现
for hang in range(1,10):
    for lie in range(1,hang+1):
        print('@',end="")
    print('')#目的：一行星星输出完成后，添加换行！

#while循环实现
row=1
while row <=5:
    print('@'*row)
    row+=1
'''

#九九乘法表
for i in range(1,10):
    for j in range(1,i+1):
        print(str(i)+"x"+ str(j)+ "=" +str(i *j) +"\t",end="")
    print('')   


    
from operator import contains


for  i in range(100):
    print('现在走到了',i)
    if i%3==2 and i%5==2 and i%7==3:
       print('是满足条件的数字：',i)
       break



for  i in range(100):
     if i%3!=2:
        continue
     elif i%5!=2:
        continue
     elif i%7!=3:
         continue
     else:
         print(i)    




for i in range(3):
     number=input('请输入数字:')
     if number=='89':
         print('成功猜对')
         break
     else:
         print('重新输入，您还有%d次机会'%(2-i))   
else:
    print('超过三次，游戏失败')   







