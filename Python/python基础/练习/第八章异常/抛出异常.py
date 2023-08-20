def input_password():
    pwd=input('请输入密码:')
    if len(pwd)>=8:
        return pwd
    #主动抛出异常
    print('主动抛出异常')#可使用错误信息字符串做参数
    # 1.创建 Exception 的对象
    ex=Exception('密码长度不够')
    # 2.使用 raise关键字 抛出 异常对象
    raise ex
try:
    print(input_password())
except Exception as result:
    print(result)
