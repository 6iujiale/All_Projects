"""
#比较数字大小，
a=18
b=20
if a<b:
    print("对哦")
if a>b:
    print("错的")
"""



#else语法
"""
a=10
b=20
if a>b:
    a=30
    print(a)
else:
    print(1111)
"""




#判断年龄
"""
age=int(input())
if age>=18:
    agw=str(age)
    print("您已经成年，欢迎来到酒吧")
else:
    print("未成年人禁止进入")
"""




#判断成绩是否优秀
"""
yuwen=int(input())
if yuwen<=60:
    print("差")
elif 60<yuwen<=75:
    print("良")
elif 75<yuwen<=85:
    print("优秀")
else:
    print("非常优秀")
shuxue=int(input())
if shuxue<=60:
    print("差")
elif 60<shuxue<=75:
    print("良")
elif 75<shuxue<=85:
    print("优秀")
else:
    print("非常优秀")
yingyu=int(input())
if yingyu<=60:
    print("差")
elif 60<yingyu<=75:
    print("良")
elif 75<yingyu<=85:
    print("优秀")
else:
    print("非常优秀")
sum=yuwen+shuxue+yingyu 
print(sum)
"""



#比大小
"""
number1=int(input())
number2=int(input())
if number1>number2:
    print(number1)
else:
    print(number2)
"""



#1.if语句  可以只有if  但不能只有else
#if语句可以包含多条语句
#1.检测程序 运行成功   功能正确  测试     
""""
yw:0 flase="false"

yw=int(input())
py=int(input())
if  yw>=90 and py>=90:
    print("优秀")
else:
    print("合格")
"""



number=int(input())
if number%3==2 and number%5==3 and number%7==2:
    print("正确")
else:
    print("错误")







"""
print("机会之一")
print("机会之二")
print("机会之三")
print("机会之四")
"""

"""
score_yw=int(input())
if score_yw>=90:
  print("优秀")
else:+
    print("合格")
print("你的语文成绩:",score_yw)
"""

score_yw = int(input())
grade = ""
if score_yw >= 90:
    grade = "优秀"
else:
    grade = "合格"
print("你的语文成绩:", grade)

"""
s = int(input("请输入分数:"))

if 80 >= s >= 60:
    print("及格")
elif 80 < s <= 90:
    print("优秀")

elif 90 < s <= 100:
    print("非常优秀")

else:
    print("不及格")
"""



