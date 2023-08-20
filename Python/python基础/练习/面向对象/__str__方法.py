#__str__方法
    #输出对象变量时,自定义内容。
    #必须返回一个字符串

class Cat:
    def __init__(self,name):
        self.name=name
        # self.name="tom"#初始值,默认名字为tom

    def eat(self):
        print(f"{self.name}爱吃鱼")

    def __str__(self):
        #必须返回一个字符串
        return "我是一个猫[%s]"%self.name
tom=Cat("Tom")
tom.eat()
#python输出对象变量,默认输入 变量的引用对象 由哪个类创建的对象 内存中的地址
print(tom)#我是一个猫[Tom]


""" lazyCat=Cat("LazyCat")
lazyCat.eat() """
