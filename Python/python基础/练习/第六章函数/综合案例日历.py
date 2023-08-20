def get_week_with_date(y,m,d):
    y=y-1 if m==1 or m==2 else y
    m=13 if m==1 else(14 if m==2 else m)
    w=int((d+2*m+3*(m+1)//5+y+y//4-y//100+y//4000)%7+1)
    return w 
def is_leap_year(y):#闰年或平年
    if year  %400 ==0 or (year%4==0 and year%100!=0):
        return True
    return False
def get_days_in_month(y,m):
    if m in [1,3,5,7,8,10,12]:
       return 31
    elif m in[4,6,9,11]:
       return 30
    else:
        return 29 if is_leap_year(y) else 28
'''
1.提示用户输入年月日
'''
year=int(input('请输入年份：'))
month=int(input('请输入月份：'))
'''
2.计算这个月有多少天
'''
days=get_days_in_month(year,month)
'''
3.按照指定格式显示日期
'''
print('一 二 三 四 五 六 日')
print('*'*20)
for i in range(1,days+1):
    w= get_week_with_date(year,month,i)
    if i==1:
        print(f"{''*(w-1)*3}",end='')
    if w ==1:
        print('')
    print(f"{i:2d}",end=' ')
print('')