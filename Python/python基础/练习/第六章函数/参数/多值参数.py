#*  接收元组
#** 接收字典
def demo(num,*nums,**person):
    print(num)
    print(nums)
    print(person)
 # demo(1)
demo(1,2,3,4,name="小明")


# Python当如输入的参数个数不能确定时，会定义不定形参的函数，即多变形参函数。
def sum_numbers(*args):
    print(args)
    num=sum(args)
    return num
result=sum_numbers(1,2,3,4,5)
print(result)