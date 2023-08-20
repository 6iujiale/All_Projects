#索引
#切片
#序列相加
#乘法
#检查 value in sequence
#计算长度,最大值,最小值

'''
'''
#序列相加
test1=['1','2','3','4','5']
test2=['6','7','8','9','10']
print(test1+test2)


#乘法
test1=['1','2','3','4','5']
print(test1*3) 
#输出结果：['1', '2', '3', '4', '5', '1', '2', '3', '4', '5', '1', '2', '3', '4', '5']


#检查 value in sequence
test1=['1','2','3','4','5']
print('1' in test1)
print('6' in test1)
#输出结果：True
#输出结果：False


#计算长度,最大值,最小值
test1=['1','2','3','4','5']
print('序列test1的长度为:',len(test1))


test1=['1','2','3','4','5']
print('序列test最大值为:',max(test1))


test1=['1','2','3','4','5']
print('序列test最小值为:',min(test1))