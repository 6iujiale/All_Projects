#注意
#必须保证带有默认值的缺省参数 在参数列表末尾
#调用带有多个缺省参数的函数
def print_info(name,gender=True):#指定缺省参数默认值 参数后使用赋值语句(变量=数值)  使用最常见的值
    # pass
    '''
    name:班上同学的姓名
    gender：True男生  False女生
    '''
    gender_text="男生"
    if not gender:#取反
        gender_text ="女生"
    print('%s是%s'%(name,gender_text))
#假设男生居多
#不设置传入形参也能判断性别
print_info('小乐')
print_info('老王')
print_info('小美',False)

