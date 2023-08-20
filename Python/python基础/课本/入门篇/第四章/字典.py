#创建和删除
#键值对访问字典
#遍历
#添加、修改、删除元素
#字典推导式


#创建和删除
dictionary=dict()
dictionary={}
# print(dictionary)

name={'张三','李四','王五','王小二'}
sign={'金牛座','射手座','巨蟹座','双子座'}
dictionary=zip(name,sign)
print(type(dictionary))
# print(list(dictionary))
# # print(dictionary)
# print('张三的星座是：',dictionary['张三'] if '张三' in dictionary else '我的字典里面没有这个人')



#键值对访问字典
#dictionary['']
#dictionary.get('')
dictionary={'李四': '巨蟹座', '张三': '射手座', '王小二': '金牛座', '王五': '双子座'}  
print(dictionary['李四'])
print(dictionary.get('李四'))
print('张三的星座是：',dictionary.get('张三'))


#遍历列表
dictionary={'qq:123','明日科技:456','刘佳乐:0707',}
for item in dictionary:
    print(item)


tinydict = {'Google': 'www.google.com', 'Runoob': 'www.runoob.com', 'taobao': 'www.taobao.com'}
 # 遍历字典列表
#\ for key,values in  tinydict.items():
#     print(key,values)


tinydict={'qq':'123','明日科技':'456','刘佳乐':'0707'}
print(tinydict)
# for key,values in tinydict.items():
#     print(key,values)









