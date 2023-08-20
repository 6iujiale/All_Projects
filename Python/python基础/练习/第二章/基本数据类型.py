py=95
eng=92
mat=90
fen_cha=py-eng
print(fen_cha)


#不会自动取整，//取前面的整数
py=100
eng=92
mat=90
sum=py+eng+mat
avg_fen=(sum//3)
print(sum)
print(avg_fen)


#脑筋急转弯
n=98765
nol=n//10000
no5=n%10
cha=nol-no5
print(cha)


n=123
x=n//100%10
print(x)



#输入一个三位数，计算各位上的数字之和 接受来自用户的输入
n=input()
no1=int(n)//100
no2=int(n)//10%10
no3=int(n)//1%10
sum=no1+no2+no3
print(sum)


f="203.4"
ff=float(f)
print(ff)


f=30
ff=float(f)
print(ff)


f=30.5
ff=str(f)
print(ff)


#type(对象)
f=30**2
print(f)


# f=30.5
# n=isinstance(f,float)
# print(n)


# f=30.5
# n=isinstance(f,str)
# print(n)


# a=30
# a+=10
# print(a)

# n=input()
# no2=int(n)//100
# no3=int(n)//10%10
# no4=int(n)//1%10
# sum=no2+no3+no4
# print(sum)



# #字符类型的数据 str
# #str+str不报错，是把两个str连接在一起
# x=input()
# z=type(x)
# print(z)


# x=int(input())
# bai=x//100
# shi=x//10%10
# ge=x%10
# zonghe=bai+shi+ge
# print(zonghe)



# print("hahh""876.3")

# x=float(input())
# y=int(x)
# print(y)

# x=float('9.0')#字符串转为浮点型
# y=int(x)
# print(y)




# x=5
# x=x+2
# x=x+2
# print(x)


# x=5
# x=x+2
# x=x+2
# z=x!=5 #z=false
# print(z)


# x=5
# x=x+2
# x=x+2
# z=x!=5 #z=false
# print(z)
