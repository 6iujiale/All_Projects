""" 案例四
    输入开始数字和结束数字，打印区间的素数 """

start_num=int(input("开始数字："))
end_num=int(input("结束数字："))

""" while start_num<end_num:
    # print(start_num)
    start_num+=1
    if start_num%2!=0:
        print(start_num)
 """
    

def print_primes(begin,end):
    while begin<end:
        begin+=1
        if begin%2!=0:
            print(begin)
print_primes(start_num,end_num)
