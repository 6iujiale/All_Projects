import cards_tools

#无限循环
while True:  
    # TODO 显示功能菜单
    cards_tools.show_menu()
    action_str=input('请输入您希望执行的操作：')
    print('您选择的操作是【%s】'%action_str)
    #成员运算符判断比
    if action_str in ['1','2','3']:
        #pass关键字 占位符 保证程序正常运行
        # pass
        if action_str=='1':
            #新增名片
            cards_tools.new_card()
            #显示名片
        elif action_str=='2':
            cards_tools.show_all()
        elif action_str=='3':
            cards_tools.serch_card()
        else:
            pass
    elif action_str=='0':
        print('欢迎再次使用【名片管理系统】')
        break
    else:
        print('您输入的不正确,请重新选择')