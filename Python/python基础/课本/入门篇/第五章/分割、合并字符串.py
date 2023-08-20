#分割字符串 
# listname=str.split(sep,maxsplit)
    # sep:空格、制表、换行
    # maxsplit:分割次数

#合并字符串
#strnew=string.join(iterable)
    # string:字符串类型，指定合并时的分隔符
    # iterable：可迭代对象



str1='@明日科技 @扎克伯格 @俞敏洪 @勤奋的天使'
list1=str1.split(' ')
print(list1)
list1=str1.split(' ',2)#分割前两个
print(list1)

#循环遍历每个元素前新增一个元素(刘佳乐)
#哈哈哈笑死我了自恋姐
list2=[]#接收新列表
# print(len(list1))
for i in list1:
    print(list1)
    list2.append('刘佳乐') 
    list2.append(i)
print(list2)


list_friend=['明日科技', '扎克伯格', '俞敏洪', '勤奋的天使']
str1="@".join(list_friend)
at="@"+str1
print('您要@的好友有:',at)#输出为：您要@的好友有:@明日科技@扎克伯格@俞敏洪@勤奋的天使
   
# list_friend=['明日科技', '扎克伯格', '俞敏洪', '勤奋的天使']
# list_friend2=[]
# for index,item in enumerate(list_friend):
#     list_friend2.append('ljl')
#     list_friend2.append(item)
# print(list_friend2)