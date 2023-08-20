
def measure():
    print('测量开始----')
    temp=39
    wetness=40
    print('测量结束----')
    #元组可包含多个数据 因此可使用元组让函数一次返回多个值
    return temp,wetness #返回的类型是 元组
result=measure()
print(result)
#分别拿出温度和湿度
gl_n1,gl_n2=measure()
print(gl_n1)
print(gl_n2)
