#函数参数
    # 可以在调用函数时，使用参数向函数体内传递数据
    # 可以定义多个参数，中间用逗号隔开
    # def line(a,b)#形参:
    #     print(a*b)
    # line('-'*10)#实参
    # line('*'*10)#实参


#打印分割线
def line(number):
    print('------------'*number)
line(5)

def line(number,char):
    print(char*number)
line(5,"@")


#数字计算
# def sum_2_num():
#     n1=10
#     n2=23
#     result=n1+n2
#     print('%d + %d =%d' %(n1,n2,result))
# sum_2_num()    


# def  sum_2_num(n1,n2): #形参
#     result=n1+n2
#     print('%d + %d =%d' %(n1,n2,result))
# sum_2_num(1,2) #实参


def generate_random(n,m):
    import random
    for i in range(n):
        ran=random.randint(1,10)
        if ran==m:
            print('我被中断了')
            break
        print(ran)
generate_random(8,5)





