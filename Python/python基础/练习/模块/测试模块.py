#变量
from ast import main


name='ljl'
age=17

#函数
def detect(case=None):
    if case:
        print('这个案件真相只有一个！！！')
    else:
        print('没有这个案件世界太平')

#类
class Course:
    def __init__(self,name,c_list):
        self.name=name
        self.c_list=age
        def add_course(self,c_name):
            if c_name:
                self.c_list.append(c_name)
            else:
                print('选修课不能为空')
# print(name)
# detect()