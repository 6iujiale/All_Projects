 #士兵
class Soldier:
    def __init__(self,name):
        self.name=name
        #新兵没有抢
        self.gun=None

    def fire(self):
        if self.gun is None:
            print(f"{self.name}还没有枪")
            return
        print("有枪,士兵可以开火")
        #枪添加子弹
        self.gun.add_bullet(10)

    def __str__(self):
        return f"士兵{self.name},有一把{g1.model}"

#枪
class Gun:
    def __init__(self,model):
        #qiang
        self.model=model
        #子弹的数量
        self.bullet_count=0
    
    def add_bullet(self,count):
        self.bullet_count+=count
        print(f"抢可以装子弹,子弹有{count}发")
    
    def shoot(self):
        #判断子弹数量
        if self.bullet_count==0:
            print(f"{self.model}没有子弹了")
            return
        #发射子弹 -1
        self.bullet_count-=1
        #提示发射信息
        print(f"枪可以发射子弹,还有{self.bullet_count}发")


g1=Gun("AK48")
""" g1.add_bullet(1)
g1.shoot()
g1.shoot() """

p1=Soldier("许三多")
p1.gun=g1
p1.fire()
# print(p1)

