#创建和删除
#访问
#遍历
#添加、修改和删除列表元素
#统计和计算
#排序
#推导式
#二维列表

'''
#创建和删除
test1=[]
print(test1)


#删除格式：del 列表名 [索引值]
test1=['1','2','3','4','5']
del test1 [0:2]
print(test1)
#输出结果：['3', '4', '5']
'''

#遍历
    #格式1 
    # for循环  
        # for item in listname:
                #输出item
     #格式2
    # for循环和enumerate函数()  
        #index：保留元素的索引
        #item：保存获取到的元素值
        #listname：列表名称
        # for index,item in enumerate(listname):
                #输出index和item
        
'''
  
print('2017~2018赛季NBA西部联盟前八名')
team=['休斯顿火箭',
'金州勇士',
'波特兰开拓者',
'俄克拉荷马雷霆',
'犹他爵士',
'新奥尔良鹈鹕',
'圣安东尼奥马刺',
'明尼苏达森林狼',]
for index,item in enumerate(team):
    print(index+1,item)



print('判断索引值是否为偶数,偶数值换行,奇数值不换行')
team=['休斯顿火箭',
'金州勇士',
'波特兰开拓者',
'俄克拉荷马雷霆',
'犹他爵士',
'新奥尔良鹈鹕',
'圣安东尼奥马刺',
'明尼苏达森林狼',]
for index,item in enumerate(team):
    if index%2==0:
        print(item+'\n')
    else:
        print(item)
'''  




#添加、修改和删除列表元素
    #添加
        #listname.append(obj)
        #listname.insert(索引值,'元素名')
        #listname.extend(seq)

    #修改
        #元素名[索引值]

    #删除
        #del     格式：del listname [索引值]
        #remove  格式： listname.remove('元素')   
        #pop     格式： listname.pop()  |   listname.pop(索引值，'元素名')  pop默认删除最后一个元素  
        #clear   格式： listname.clear() 
        

#统计和计算
    #统计
        #获取元素出现的次数
            #listname.count(obj)
    
        #获取元素首次出现的下标(索引值)
            #listname.index(obj)

    #计算
        #sum(iterable[,start])

print('中考成绩计算')
grade=[99,67,71,95,92,47]
total=sum(grade)
print('您的中考成绩为：',total)


#排序
    #升序 listname.sort()
    #降序 listname.sort(reverse=True)

'''
grade=[99,67,71,95,92,47]
grade.sort()
print(grade)
grade.sort(reverse=True)
print(grade)
'''

#推导式
    #生成指定范围的数值列表  list=[Expression for var in range ]
    #生成指定需求的列表      newlist=[Expression for var in list ]


'''
import random
randomnumber=[random.randint(10,100) for i in range(10)]
print('随机生成数为：',randomnumber)

price=[1200,5330,456,9876]
sale=[int(x*0.5) for x in price]
print('打五折的价格为：',sale)

'''

#二维列表
arr=[]
for i in range(4): #0~4且不包括4
    arr.append([])
    for j in range(5):
        arr[i].append(j)
print(arr)

arr=[[j for j in range(5)]for i in range(4)]
print(arr)


        
