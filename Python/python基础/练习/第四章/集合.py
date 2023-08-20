#有四个数字：1、2、3、4，能组成多少个互不相同且无重复数字的三位数？各是多少？
for a in range(1,5):
    for b in range(1,5):
        for c in range(1,5):
            if a!=b and b!=c and c!=a:
                print("%d%d%d" %(a,b,c))



#输入某年某月某日，判断这一天是这一年的第几天？
year=int(input('请输入年份：'))
month=int(input('请输入月份：'))
day=int(input('请输入某一个月的天数：'))

months = [0,31,59,90,120,151,181,212,243,273,304,334]
if 0 < month <= 12:
    sum = months[month-1]
print('这一天是这一年的%d日'%sum)



