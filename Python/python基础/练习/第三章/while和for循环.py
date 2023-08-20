'''
n=50
x=20
while n>=x:
    if n%2: #
        print(n)
    n-=1
print('end') 
'''

#知道嘛知道并接受自己平庸是一种能力。
#flag用False来约束开关 。
flag=True
# n=0
# while flag:
#     print('~@_@~')
#     n=n+1
#     if n==5:
#         flag=False


# n=0
# while flag:
#     print('~@_@~')
#     n=n+1
#     if n==5:
#         break

# n=0
# while n<5:
#     print('~@_@~')
#     n+=1
#     if n==5:
#         break



'''
chengji=int(input())
while 0<=chengji<=100:
    chengji=int(input())
    print('开始计算')    
    if chengji>=90:
        print('A')
    elif 60<=chengji<=80:
        print('B')
    else:
        print('C')
    chengji+=1         
print('结束')
'''


'''
i=0
chengji=int(input())
while chengji:
    if chengji<0:
        print('重新输入')
        i+=1
    else:
        print('开始判断')          
        i+=1
        if chengji>=90:
            print("A")
        elif 60<=chengji<=80:    
            print('B')
        else:    
            print('C')
    print('结束判断')
'''


'''
i=0
score=int(input())
while  score:
    if 0<score<101:
        print('重新输入')
'''


'''
i=0
score=int(input('请输入你的成绩：'))
while score<3:
    if 0<score or score>100:
        print('重新输入')
        i+=1
    else:
        print('开始判断')
       
        if score>=90:         
            print("A")
        elif 60<=score<=90:  
            print("B")  
        else:    
            print('C')
    i+=1
print('结束判断')
'''



'''
score=int(input('请输入你的成绩：'))
while score<0 or score>100:
    score=int(input('请重新输入：'))     
if score>=90:         
    print("A")               
elif 60<=score<90:  
    print("B")
else:    
    print('C')       
print('结束判断')        
'''
'''
i=100
while i<=1000:
    if i%3==2 and i%5==3 and i%7==2:
       print(i)
    i+=1
print('循环结束后，i=%d'%i) 
'''

'''

for i in range(5): #range (5) 0 1 2 3 4  ,0~5范围的数字不包括5
    print(i)

for i  in range(1,11):
    print(i)

for i  in range(10):
    print(i+1)

result=0
for i  in range(1,11):
    print(i)
    result+=i
print(result)   
'''

# for i in range(20): #不包含20  0~20
#     print(i)

'''
for i  in range(2,10): #包含2不包含10
    print(i)

for i  in range(2,100,3): #包含2不包含100 步长为3
    print(i)

for i  in range(1,11,2): 
    print(i+1)




result=0
for i  in range(1,11,2):
    print(i)
    result+=i
print(result) 
'''
# result=0
# n1=int(input())
# n2=int(input())
# for i in range(n1,n2+1):
#     print(i)
#     result+=i
# print(result)

'''
n1=0
for i in range(100,1001):
    if i%3==2 and i%5==3 and i%7==2:
        print(i)
        n1+=1
print(n1)


n1=0
for i in range(10,101):
    if i%3==0 and i%5==0:
        print(i)
        n1+=1
print(n1)


string ='我是中国人'
for i in string:
    if i=="中":
        print(i)



for row in range(1,8):
    print('当前所在排',row)
    if 5==row:
        print('第二排使我们座位所在的拍数！')
        for col in range(1,15):
            print("当前所在列",col)
            if 10==col:
             print('第十列是我所在的座位')
print('结束')        



for row in range(1,8):
    if 5==row:
        print('第五排使我们座位所在的拍数！')
        for col in range(1,15):
            if 10==col:
             print('第十列是我所在的座位')
print('结束')        


for row in range(1,8):
    for col in range(1,15):
         if 5==row and 10==col:
            print('成功找到座位') 


for hang in range(1,4):
    for lie in range(1,6):
        print('@~@',end='\t')  #/t=tab键
    print('')

'''    

 

# for hang in range(1,6):
#     for lie in range(1,hang+1):
#         print('@',end="\t")
#     print('') #目的：添加换行！

# for hang in range(1,6):
#     for lie in range(6-hang):
#         print('@',end="\t")
#     print('')


# for hang in range(1,6):
#     for lie in range(1,hang+1):
#         print(str(lie),end="\t")
#     print('')

# n1=0
# for i in range(100,1001):
#     if i%3==2 and i%5==3 and i%7==2:
#         print(i)
#         n1+=1
# print(n1)

print("hhah")

a=0
for i in range(100):
    if i%2==0:
        a+=i
print(a)