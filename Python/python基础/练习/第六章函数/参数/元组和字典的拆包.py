#拆包
#元组变量前，增加一个*
#集合变量前，增加两个*
def demo(*args,**kwargs):
    print(args)
    print(kwargs)
# demo(1,2,3,name='小明')
gl_nums=1,2,3
gl_dict={'name':'小明','age':'18'}
demo(*gl_nums,**gl_dict)
