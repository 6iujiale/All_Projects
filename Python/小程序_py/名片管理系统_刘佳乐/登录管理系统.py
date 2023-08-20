#记录用户信息
dict_information={}
#错误提示_新建名片
wrong_prompt=['姓名必须是中文请重输','请输入正确的手机号码','请输入正确的QQ号','请输入正确的邮箱号','用户名重复']
#记录用户登录活动
user_activate={}

while all:
    dict_operation={1:'新建名片',
                    2:'显示全部',
                    3:"查询名片",
                    4:"退出系统"
                    }
    for key,value in dict_operation.items():
        print(key,value,end='\t')
    user=input('请您输入想操作的功能选项序号: ')
  
    #新建名片
    if user=='1':
        print('*'*18+'欢迎来到新建名片界面'+'*'*18)
        while 1:
            name=input('您的姓名:')
            qq=input('您的QQ号:')
            phone=input('您的手机号:')
            email=input('您的邮箱号:')
            #检查
            def check_name():
                if u'\u4e00' <= name <= u'\u9fa5':
                    dict_information[name]='姓名'
                else: 
                    print(wrong_prompt[0])
            check_name()
            if phone and qq and email >= u'0' and qq and email<=u'9':
                pass
                if len(phone)==11:
                     dict_information[phone]='电话'
                else:
                    print(wrong_prompt[1])
                if len(qq)==10:
                    dict_information[qq]='QQ'
                else:
                    print(wrong_prompt[2])
                if len(email)==17 and email.endswith('qq.com'):
                    dict_information[email]='邮箱'
                    user_judge=input('是否注册多用户,请输入yes or no: ')
                    if user_judge=='yes':
                        print('开始注册多用户')
                    else:
                        print('注册成功')
                        break
                else:
                    print(wrong_prompt[3])
                #检查
                # if len(phone)>2:
                #     dict_information[phone]='电话'
                # else:
                #     print(wrong_prompt[1])
                # if len(qq)>2:
                #     dict_information[qq]='qq'
                # else:
                #     print(wrong_prompt[2])
                # if len(email)>2:
                #     dict_information[email]='邮箱'
                #     user_judge=input('是否注册多用户,请输入yes or no: ')
                #     if user_judge=='yes':
                #         print('开始注册多用户')
                #     else:
                #         print('注册成功')
                #         break
                # else:
                #     print(wrong_prompt[3])
            else:
                print('手机号或QQ或邮箱必须为数字请检查')
    
    #显示全部
    if user=='2':
        print('*'*18+'欢迎来到显示名片界面'+'*'*18)
        for key,value in dict_information.items():
             print(value,key,'\t')
        
    
    #查询名片
    if user=='3':
        print('*'*18+'欢迎来到名片查询界面'+'*'*18)
        print('查询前请您先登录')
        #登录
        for i in range(3):
            username=input('请输入用户名：')
            useremail=input('请输入邮箱号：')
            if username and  useremail in dict_information.keys():
                user_activate['登录']='成功'
                print('登录成功')
                break
            else:
                print('登录失败请重试,您还有%d次机会'%(2-i))
                #手机证码登录
                if i==2:
                    print('机会已用完,是否选择验证码登录')
                    user_choice=input('请输入yes or no: ')
                    if user_choice=='yes':
                        username=input('请输入用户名：')
                        telephone_number=input('请输入手机号码：')
                        import random
                        verification=[random.randint(1000,9999) for i in range(1)]
                        print('请在1分钟之类完成验证','\n您的验证码为: %s'%verification)
                        yz=int((input('请输入您的验证码：')))
                        if yz not in verification:
                            print('验证码登录失败')
                            break
                        else:
                            user_activate['登录']='成功'
                            print('验证码登录成功')                             
                    else:
                        print('退出登录')
        #登录成功才可继续
        if '成功'  in user_activate.values():
            print('可查询or修改or删除名片\t1.修改名片,2.查询名片,3.删除名片,4.退出')
            user_choice=input('请您输入想操作的序号:')
            #2.查询
            if user_choice=='2':
                print('您的信息为%s'%dict_information)
            if user_choice=='1':
            #1.修改用户名或QQ号
                user_choice=input('可修改资料为姓名和QQ,请选择输入name or qq:')
                if user_choice=='name':
                    #用户名
                    name=input('请输入新的用户名：')
                    if name not in dict_information.keys() :
                        print('用户名修改成功')
                    else:
                        print('用户名修改失败可能是%s'%wrong_prompt[4])
                elif user_choice=='qq':
                    #qq号
                    qq=input('请输入新的qq号:')
                    if len(qq)==10:
                        print('qq修改成功')
                    else:
                        print('qq修改失败')
                else:
                    break
            #3删除名片
            if user_choice=='3':
                print('温馨提示:此次操作将会删除您的全部信息')
                user_choice=input('是否继续请输入:yes or no')
                if user_choice=='yes':
                    dict_information.clear()
                    print('信息删除完毕')
                else:
                    break
            #4退出
            if user_choice=='4':
                print('感谢您的查询祝您生活愉快')
                break                    
        else:
            print('不可查询登录失败请重试')
            
    #退出系统
    if user=='4':
        break

    if user not in  dict_operation:
        break