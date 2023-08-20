""" 案例二
    求数字阶乘，3的阶乘:3*2*1 """
num=int(input("输入一个数字,计算阶乘："))
mci=1
for i in range(1,num+1):
    mci*=i
print(f"数字{num}阶乘是{mci}")
