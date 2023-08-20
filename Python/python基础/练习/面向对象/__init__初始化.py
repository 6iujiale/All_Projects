#初始化方法
    # 专门定义一个类,具体有哪些属性的方法
    # 格式 self.属性名=初始值


""" class Cat:
    def eat(self):
        #哪个对象调用的方法,self就是哪个对象的引用
        print("%s爱吃鱼"%self.name)

    def drink(self):
        print("%s要喝水"%self.name)

tom=Cat()
tom.name="tom"
tom.eat()
tom.drink()

 """

""" class Cat:
    #__init__方法是专门用来定义一个类 具体由哪些属性的方法
    def __init__(self):
        #初始化方法定义属性 self.属性名=初始值
        self.name="Tom"
        print("这是一个初始化方法")

    def eat(self):
        print("%s爱吃鱼"%self.name)

tom=Cat()
print(tom.name)#Tom
tom.eat()#Tom爱吃鱼 """

#改造初始化方法,初始化同时设置初始值
class Cat:
    def __init__(self,name):
        self.name=name
        # self.name="tom"#初始值,默认名字为tom

    def eat(self):
        print(f"{self.name}爱吃鱼")
tom=Cat("Tom")
tom.eat()

lazyCat=Cat("LazyCat")
lazyCat.eat()


