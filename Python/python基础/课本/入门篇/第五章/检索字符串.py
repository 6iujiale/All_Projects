#指定字符串出现的次数
    #str.count

#查找指定字符串
    #str.find
        #存在：返回索引值
        #不存在：返回-1
    #str.index
        #存在：返回索引值
        #不存在：抛出异常(报错)
#判断是否以指定字符串开始
    #str.startswith()
#判断是否以指定字符串结束
    #str.endswith()

str1='@明日科技 @扎克伯格 @俞敏洪 @勤奋的天使'
print(str1.count('@'))
print(str1.index('@'))
print(str1.startswith('@'))
print(str1.endswith('大天使'))