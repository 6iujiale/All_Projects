#一个函数 内部 调用自己
#进行参数判断 满足条件 程序不执行

# def sum_number(num):
#     print(num)
#     #递归的出口，当参数满足某个条件时，不再执行函数
#     if num==1:
#         print('---end----')
#         return 
#     #自己调用自己
#     sum_number(num-1)
# sum_number(3)

for a in range(1,10):
    for b in range(1,a+1):
        print('%d*%d=%d'%(b,a,a*b),end='\t')
    print('')
