#定义用户类
class user():

    def __init__(self,id,name,pw):
        self.id = id #用户编号
        self.name=name #用户名字
        self.pw=pw #用户密码

    def showinfo(self):
        """
        显示个人信息
        :return:
        """
        print("编号：",self.id)
        print("名字：",self.name)
        print("密码：",self.pw)

# 定义用户管理者，负责对用户实现CRUD操作
class usermanger():

    def __init__(self):
        #初始化3个用户
        user1= user(1,"jack","123")
        user2 = user(2, "tom", "123")
        user3 = user(3, "admain", "123")
        self.users = [user3,user2,user1]

    def add_user(self,user):
        """
        添加用户
        :return:
        """
        self.users.append(user)
        return True

    def get_index_by_id(self,id):
        """
        根据id来获取用户的索引
        :param id:
        :return:
        """
        for u in self.users:
            if u.id==id:
                return self.users.index(u)
        return -1

    def delete_user_by_id(self,id):
        """
        删除用户
        :param id:
        :return:
        """
        # 根据id来查找用户，存在就删除，不存在则返回信息
        index = self.get_index_by_id(id)
        if index>=0:
            self.users.pop(index)
            return True
        return False

    def update_user_by_id(self,user):
        """
        根据id来修改
        :param user:
        :return:
        """
        index = self.get_index_by_id(user.id)
        if index>=0:
            self.users[index]=user
            return True
        return False

    def query_all(self):
        """
        查询全部
        :return:
        """
        return self.users

    def query_users_by_name(self,name):
        """
        根据名字来查询
        :param name:
        :return:
        """
        # us = [] # 保存满足条件的用户
        # for u in self.users:
        #     if u.name.find(name)>=0:#满足条件
        #         us.append(u)
        # return  us
        return [u for u in self.users if u.name.find(name)>=0]

    def query_user_by_id(self,id):
        # 根据id来查找用户
        index = self.get_index_by_id(id)
        if index >= 0:
            return self.users[index]
        return None

# 定义菜单类,负责输入和输出
class menu():

    def __init__(self):
        self.usermanger = usermanger() # 创建用户管理对象

    def start_system(self):
        """
        显示菜单
        :return:
        """
        print("""
           欢迎来到用户管理系统
    	1 添加用户
    	2 删除用户
    	3 修改用户
    	4 查询用户(id)
      	5 查询全部
        6 根据用户的名字来查询（模糊）
    	7 退出
        """)
        type = int(input("请选择："))
        while type >= 1 and type <= 7:
            if type == 1:#添加用户
                self.addusermenu()
            elif type == 2:
                self.deleteusermenu()
            elif type == 3:
                self.updateusermenu()
            elif type == 4:
                self.queryuseridmenu()
            elif type == 5:
                self.queryallmenu()
            elif type == 6:
                self.queryusernamemenu()
            elif type == 7:
                break
            type = int(input("请选择："))

    def addusermenu(self):
        """
        添加用户的菜单
        :return:
        """
        print("-------添加用户--------")
        id = int(input("请输入用户的id:"))
        name = input("请输入用户的名字：")
        pw = input("请输入密码：")
        if self.usermanger.add_user(user(id,name,pw)):
            print("添加成功")
        else:
            print("添加失败")

    def  queryuseridmenu(self):
        """
        查询用户（id）的菜单
        :return:
        """
        print("------查询用户(id)------")
        id = int(input("请输入用户的id:"))
        u = self.usermanger.query_user_by_id(id)
        if u is not None:
            u.showinfo()
        else:
            print("用户不存在！！")

    def deleteusermenu(self):
        """
        删除用户的菜单
        :return:
        """
        print("------删除用户------")
        id = int(input("请输入用户的id:"))
        if self.usermanger.delete_user_by_id(id):
            print("删除成功")
        else:
            print("删除失败")

    def updateusermenu(self):
        """
        修改用户的菜单
        :return:
        """
        print("------修改用户------")
        id = int(input("请输入用户的id:"))
        if self.usermanger.get_index_by_id(id)>=0:
            name = input("请输入用户的名字：")
            pw = input("请输入密码：")
            if self.usermanger.update_user_by_id(user(id,name,pw)):
                print("修改成功")
            else:
                print("修改失败")
        else:
            print("没有修改的用户！")


    def  queryallmenu(self):
        """
        查询全部菜单
        :return:
        """
        print("-------查询全部------")
        us = self.usermanger.query_all()
        if len(us)>0:
            for u in us:
                u.showinfo()
                print("-"*12)
        else:
            print("没有用户！")

    def queryusernamemenu(self):
        """
        根据名字来查询用户
        :return:
        """
        print("-------根据用户的名字来查询（模糊）------")
        name = input("请输入用户名：")
        us = self.usermanger.query_users_by_name(name)
        if len(us) > 0:
            for u in us:
                u.showinfo()
                print("-" * 12)
        else:
            print("没有用户！")


m = menu()
m.start_system()
