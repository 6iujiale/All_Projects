'''
info_tuple=('zhangsan','zhangsan',18,1.75)
#1.取值和取索引
print(info_tuple[0])
print(info_tuple.index('zhangsan'))
#2.统计计数
print(info_tuple.count('zhangsan'))
print(len(info_tuple))
'''

#元组循环遍历
info_tuple=('zhangsan','zhangsan',18,1.75)
for item in info_tuple:
   print(item)


name_list=['张三','李四','王五','王小二']
for i in name_list:
    print('我是 %s' % i)

'''
info_tuple=('小明',20,1.75)
print('%s 年龄是 %d 身高是 %.2f ' % info_tuple)

#格式化字符串后面的()本质上就是元组
info_tuple=('小明',20,1.75)
info_tuple='%s 年龄是 %d 身高是 %.2f ' % info_tuple
print(info_tuple)
'''


