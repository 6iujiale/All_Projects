#记录所有名字的字典
card_list=[]

def show_menu():
    print('*'*50)
    print('欢迎使用【名片管理系统】v 1.0')
    print('1.新增名片')
    print('2.显示名片')
    print('3.查询名片')
    print('')
    print('0.退出系统')
    print('*'*50)

def new_card():
    #新增名片
    print('*'*50)
    print('新增名片')
    #1.提示用户输入名片的详细信息
    name_str=input('请输入姓名：')
    phone_str=input('请输入电话：')
    qq_str=input('请输入qq：')
    email_str=input('请输入邮箱：')
    #2.使用用户输入的信息建立一个名片字典
    card_dict={
        'name':name_str,
        'phone':phone_str,
        'qq':qq_str,
        'email':email_str
    }
    #追加名片字典添加到列表中
    card_list.append(card_dict)
    #提示用户添加成功
    print('添加%s的名片成功'%name_str)
    print(card_list)
    # print(type(card_list))

def show_all():
    #显示名片
    print('*'*50)
    print('显示所有名片')
    #TODO 判断是否存在名片记录，如果没有，请使用新增名片功能
    if len(card_list)==0:
        print('当前没有任何的名片记录，请使用新增名片功能添加名片！！！')
        # return 返回函数执行结果
        # return 下方的代码不会执行
        # 如果 return 后面没任何的内容，表示会返回到调用函数的位置
        # 并且不返回任何的结果
        return 
    #打印表头
    for name in ["姓名","电话","QQ","邮箱"]:
        print(name,end='\t\t')
    #打印分割线
    # print('='*50)
    #字典的输出
    for card_dict in card_list:
        print('\n%s\t\t%s\t\t%s\t\t%s\t\t'%(card_dict['name'],
                                      card_dict['phone'],
                                      card_dict['qq'],
                                      card_dict['email'],
                                    ))

def serch_card():
    #查询名片
    print('*'*50)
    print('搜素名片')
    find_name=input('请输入要搜索的姓名：')
    for card_dict in card_list:
        if card_dict['name']==find_name:
            print('姓名\t\t电话\t\tQQ\t\t邮箱')
            print('\n%s\t\t%s\t\t%s\t\t%s\t\t'%(card_dict['name'],
                                      card_dict['phone'],
                                      card_dict['qq'],
                                      card_dict['email'],
                                    ))
            #TODO 针对找到的名片记录修改or删除操作
            deal_card(card_dict)#调用处理名片函数 传参
            break
        else:
            print('抱歉，没有找到%s'%find_name)
    
def deal_card(find_dict):#形参 传入参数
    print(find_dict)
    action_str=input("请选择要执行的操作 [1]修改 [2]删除 [0]返回上级目录")
    if action_str=='1':
        print(find_dict)
        find_dict['name']=input_card_info(find_dict['name'],('姓名[回车不修改]：'))
        find_dict['phone']=input_card_info(find_dict['phone'],input('电话[回车不修改]：'))
        find_dict['qq']=input_card_info(find_dict['qq'],input('QQ[回车不修改]：'))
        find_dict['email']=input_card_info(find_dict['email'],input('邮箱[回车不修改]：'))
        # print(find_dict)
        print('修改名片成功')
        pass
    elif action_str=='2':
        card_list.remove(find_dict)
        print('名片删除成功')
        pass
    else:
        pass

def input_card_info(dict_value,tip_message):
    #1.提示用户输入内容
    result_str=input(tip_message)
    #2.针对用户的输入进行判断，如果用户输入了内容，之间返回结果
    if len(result_str)>0:
        return result_str
    #3.如果用户没有输入内容，则返回"字典中原有的值"
    else:
        return  dict_value
        