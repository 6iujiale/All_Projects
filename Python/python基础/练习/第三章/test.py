'''
result=0
for i in range(100):
    if i%2==0:
       # print(i)
        result+=i
print('共有%d种组合' %result)



result=0
for i in range(100):
    if i%2==1:
       # print(i)
        result+=i
print('共有%d种组合' %result)



result=0
for a in(1,2,3,4):
    for b in(1,2,3,4):
        for c in(1,2,3,4):
            if a!=b and b!=c and c!=a:
                result+=1
print('共有%d种组合' %result)            



for a in range(1,5):
    for b in range(1,5):
        for c in range(1,5):
            if (a!=b) and (b!=c) and (c!=a):
                print('%d%d%d '%(a,b,c))


for i in range(3):
    user=input('请输入用户名：')
    passwd=input('请输入密码：')
    if user=='ljl' and passwd=='123456':
        print('登录成功')
        break
    else:
        print('登录失败，请重新再试,您还有%d次机会'%(2-i))
else:
    print('超过三次，登录失败')            
'''

# i=0
# while i<3:
#     user=input('请输入用户名：')
#     passwd=input('请输入密码：')
#     if user=='ljl' and passwd=='123456':
#         print('登录成功')
#         break
#     else:
#         print('登录失败，请重新再试,您还有%d次机会'%(2-i))
#         i+=1
# else:
#     print('超过三次，登录失败')   



# i=0
# a=0
# while i<10:
#     if i%2==0:
#         a+=i
#     i+=1
# print(a)
       
    







