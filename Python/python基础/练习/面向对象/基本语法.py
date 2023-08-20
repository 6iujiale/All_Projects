""" 
class 类名
    def 方法1(self,参数列表):
        pass
    
    def 方法2(self,参数列表):
        pass

创建对象
    对象变量=类名()
 """
#需求：定义一个猫类
#吃鱼、喝水

class Cat:
    def eat(self,eat):
        self.eat=eat
        print("小猫爱吃"+eat)
    def drink(self):
        print("小猫爱喝水")
#创建猫对象
tom=Cat()
tom.eat("小鱼")
tom.drink()
tom.name="汤姆"





