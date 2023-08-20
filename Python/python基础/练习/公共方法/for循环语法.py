# for 变量 in 集合:
#     循环体代码
# else:
#     没有通过break退出循环,循环结束后,会执行的代码。


# for num in [1,2,3]:
#     print(num)
#     if num==2:
#         break #break退出循环，else不会执行。 # 1 2 循环结束
# else:
#     print('会执行吗')
# print('循环结束')


students={
    'name1':'刘佳乐',
    'name2':'蓝精灵',
    'name3':'了解了',
    'name4':'刘嘉玲',
    'name5':'刘经理',
}
find_name='ljl' #指定搜索的名字
for key,value in students.items():
    # print(value)
    if find_name in students.values():
        print('找到了%s'%find_name)
        break
else:
    print('循环结束，抱歉没有找到%s'%find_name)

