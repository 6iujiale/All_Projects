#if语句嵌套
'''
chepiao=1
if chepiao==1:
    print('有车票，可以上火车')
    print('终于可以见到Ta了，美滋滋....')
else:
    print('没有车票，不能上课')
    print('亲爱的，那就下一次见了，一票难求~~~~')
'''



'''
chepiao = 1     # 用1代表有车票，0代表没有车票
daolenght=int(input())  # 刀子的长度，单位为cm
if chepiao==1:
    print('有车票，可以进站')
    if daolenght < 10:
        print('通过安检')
        print('终于可以见到Ta了,美滋滋...')
    else:
        print('没有通过安检')  
        print('刀子的长度超过规定，等待警察处理...')  
else:
    print('没有车票，不能上课')
    print('亲爱的，那就下一次见了，一票难求~~~~')   
'''



'''
#语句表达式
n=int(input())
no1=int(n)//100
no2=int(n)//10%10
no3=int(n)//1%10
ceshi=no1*no1*no1+no2*no2*no2+no3*no3*no3
if ceshi==n:
   print('这是水仙花树')
else:
    print('这不是水仙花树')   
'''
'''
n=int(input())
no1=int(n)//100
no2=int(n)//10%10
no3=int(n)//1%10
ceshi=no1**3+no2**3+no3**3  
if ceshi==n:    **3三次方
   print('这是水仙花树')
else:
    print('这不是水仙花树')   
'''

'''
n=int(input())
if n<0:
    n=-n
    print(n)
elif n==0:
    print(0)
else :
    print(n)       

''' 

n=int(input())
if n<0:
    n=-n
print(n)#救命！！！这么聪明，good.

''' 
n=int(input())
n1=n if n>0 else -n
print(n1)
''' 

''' 
n=int(input())
no1=int(n)//100
no2=int(n)//10%10
no3=int(n)//1%10
ceshi=no1**3+no2**3+no3**3  
print('是水仙花') if ceshi==n else print('这不是水仙花树')
''' 

       


''' 
#打印五遍hello pyrhon
#1.定义一个整数变量，记录循环次数。
#2.开始循环
i=1
while i <=50:
    #希望在循环执行的代码
    print('Hello Python')
    #处理计数器
    i=i+1
print('循环结束后，i=%d'%i)
''' 