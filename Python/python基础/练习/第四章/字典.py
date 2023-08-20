#列表和元组的区别
#   1.列表是可变序列，元素可以随时删除或修改。
#   2.元组是不可变序列，元素不可以随时删除或修改。除非整体替换、
#
#列表和字典的区别
#   1.列表是有序的对象集合
#   2.字典是无序的对象集合

#字典 {}大括号 存储一个物体的相关信息  



from textwrap import indent


xiaoming={"name":"小明",
            "age":18,
            "height":1.67,
            "qq":123456,
            "weixin_name":"hhaha"
            }
print(xiaoming)
xiaoming_dict={"name":"小明"}
#取值
print(xiaoming_dict["name"])
#增加/修改
#如果key不存在，就新增加键值对。
#如果key存在，就修改已经存在的键值对。
xiaoming_dict["test"]=18
xiaoming_dict["name"]="小小明"
print(xiaoming_dict)
#删除 del 或 pop 
# xiaoming_dict.pop('name')
# del xiaoming_dict["name"]
print(xiaoming_dict)



# xiaoming={"name":"小明",
#             "age":18,
#             "height":1.67,
#             "qq":123456,
#             "weixin_name":"hhaha"
#             }
# print(xiaoming)
# #统计
# print(len(xiaoming))
# #合并
# #注意：如果合并的字典包含已经存在的键值对，会覆盖原有的键值对
# temp_list={"xiaoxiaoming":"小小明",
#             "age":10,
#             "height":135,
#             }
# xiaoming.update(temp_list)   
# print(xiaoming)  
# #清空
# xiaoming.clear()
# print(xiaoming)


# # 第一个%s输出key的内容
# # 第一个%s输出value的内容
# # 变量i是每一次循环中，获取到的键值对的key

# #字典循环遍历
# xiaoming={"name":"小明",
#             "qq":"123456",
#             "weixin_name":"hhaha"
#             }

# for i in xiaoming:
#     print("%s 是 %s" %(i,xiaoming[i]))  


xiaoming={"name":"小明",
            "age":18,
            "height":1.67,
            "qq":123456,
            "weixin_name":"hhaha"
            }


for key,value in xiaoming.items():
    print(key,value)



