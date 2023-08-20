#创建空字典
dict_user={} #接收用户(姓名and密码)
dict_telephone={} #接收用户(姓名and手机号)
list_activity=[] #记录用户操作


#功能
while True:
    dict_operation={1:'注册',
                    2:'登录',
                    3:'游戏', #登录过才可游戏
                    4:'退出'
                    }
    for key,value in dict_operation.items():
        print(key,value,end='\t')
    user=input('请输入想操作的序号：')
    list_activity.append(user)
    
#注册
    if user=='1':
        print('*'*18+'欢迎来到注册界面'+'*'*18)
        while True:
            username=input('请输入用户名：')
            #用户名重复
            if username in dict_user:
                print('请重新输入用户名')
            else:
                password=input('请输入密码：')
                dict_user[username]=password
                telephone_number=input('请输入手机号码：')
                dict_telephone[username]=telephone_number
                user_judge=input('是否注册多用户,请输入yes or no: ')
                if user_judge=='yes':
                    print('开始注册多用户')
                else:
                    print('注册成功')
                    break
                print(dict_user)
                print(dict_telephone)

#登陆
    #密码登录
    if user=='2':
        print('*'*18+'欢迎来到登录界面'+'*'*18)
        for i in range(3):
            username=input('请输入用户名：')
            password=input('请输入密码：')
            if dict_user.get((username))==password:
                print('登录成功')
                # list_activity.append('登录成功')
                break
            else:
                print('登录失败')
                print('您还有%d次登录机会'%(2-i)) 
                #验证码登录。
                if i==2:
                    print('机会已用完,是否选择验证码登录')
                    user_choice=input('请输入yes or no: ')
                    if user_choice=='yes':
                        username=input('请输入用户名：')
                        telephone_number=input('请输入手机号码：')
                        if dict_telephone.get((username))==telephone_number:
                            import random
                            verification=[random.randint(1000,9999) for i in range(1)]
                            print(verification)
                            yz=int((input('请输入您的验证码：')))
                            if yz in verification:
                                print('验证码登录成功')
                                # list_activity.append('验证码登录成功')
                                print(list_activity)
                            else:
                                print('验证码登录失败')
                                break
                    else:
                        print('退出登录')

    #判断是否有游戏资格
    if '1' and '2' in list_activity:
    # if '登录成功' or '验证码登录成功' in list_activity:
        print('恭喜您获得游戏资格')
        if user=='3':
            print('*'*18+'欢迎来到游戏界面'+'*'*18)
            #游戏
            import games
            games.finger_guessing
    else:
        print('您未有游戏资格,请您进行登录')
    
    #退出系统   
    if user=='4':
        print('感谢您的使用')
        break

    #
    if user not in dict_operation:
        break
