""" 案例五
    输入一个数字N
    计算：1的二次方一直到加到N的二次方 """
num=int(input("输入一个数字,求前n个平方和: "))
s_sum=0
for i in range(1,num+1):
    s_sum+=i**2
print(s_sum)