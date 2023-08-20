'''
#列表 修改 增加 删除
name_list=['张三','李四','王五','老刘']
name_list[3]='老六' #修改 
print(name_list[3])
print(name_list.index('老六')) #知道数据内容，想确定数据在列表中的位置。
#增加三种方法
# append 向列表的末尾追加数据 
# insert 在列表的指定索引位置插入数据
# extend 把其他列表中的完整内容，追加到当前列表的末尾
name_list.append('王小二')
name_list.insert(1,'小美眉')
temp_list=['孙悟空','猪八戒','沙师弟']
name_list.extend(temp_list)
print(name_list)
#删除四种方法
# renmove  向列表中删除指定的数据
# pop 默认删除列表最后一个元素，可以指定要删除元素的索引 pop(number)
# clear 清空列表
name_list.remove('王五')
name_list.pop()
name_list.pop(3)
name_list.clear()
print(name_list)
'''


'''
#列表统计
# len() 列出长度
# count()  统计指定数据在列表出现的次数

name_list=['张三','李四','王五','老刘','张三']
list_len=len(name_list)
print('列表中包含%d个元素' % list_len)
count=name_list.count('张三')
print('张三出现了%s次' % count)
'''



#列表排序
name_list=['zhangsan','lisi','wangwu','wangxiaoming']
num_list=[6,8,4,1,10]
#升序
name_list.sort()
num_list.sort()

print(num_list)
# 降序
name_list.sort(reverse=True)
num_list.sort(reverse=True)

print(num_list)
#逆序
name_list.reverse()
num_list.reverse()

print(num_list)
# print(name_list)



#循环遍历 从头到尾依次从列表中获取数据
# menu=['东坡肉','北京烤鸭','北京烤鸭','热干面','麻婆豆腐','北京烤鸭','白斩鸡']
# menu1=[]
# for item in menu:
#     if item =='北京烤鸭':
#         menu1.append('京酱肉丝')
#     menu1.append(item)
# print(menu1)


# menu=['东坡肉','东坡肉','北京烤鸭','北京烤鸭','热干面','麻婆豆腐','北京烤鸭','白斩鸡']
# menu1=[]
# for item in menu:
#     if menu.count(item)>1:
#         menu1.append('京酱肉丝')
#     menu1.append(item)
# print(menu1)





