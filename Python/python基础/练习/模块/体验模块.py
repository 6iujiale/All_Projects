def  sum_2_num(n1,n2): #形参
    result=n1+n2
    # return result
    print('%d + %d =%d' %(n1,n2,result))
sum_2_num(1,2) #实参


def print_line(char,n):
    print(char*n)

def print_lines(char,n):
    i=0
    while i<5:
        i+=1
        print_line(char,n)
print_lines('***',20)