def sum_numbers(num):
    print(num)
    if num==1:
        return 1
    temp=sum_numbers(num-1)
    return num+temp
result=sum_numbers(1)
print(result)

