#案例一
#小明爱跑步,体重为75kg
#跑一次步 减肥0.5kg
#吃一次东西 体重增加1kg

class Person:
    def __init__(self,name):
        self.name=name
        self.widget=75
    
    def run(self):
        self.widget-=0.5
        # new_widget=self.widget-0.5
        # self.widget=new_widget
        print(f"{self.name}体重为{self.widget}")

    def eat(self):
        # new_widget2=self.widget+1
        # self.widget=new_widget2
        self.widget+=1
        print(f"{self.name}体重为{self.widget}")
    
    def __str__(self):
        return f"我的名字是{self.name},体重为{self.widget}公斤"


p1=Person("小明")
p1.run()#74.5
print(p1)
p1.run()#74.0
p1.eat()#75.0
print(p1)

print("")

p2=Person("小美")
p2.widget=45
print(p2)
p2.eat()
p2.eat()