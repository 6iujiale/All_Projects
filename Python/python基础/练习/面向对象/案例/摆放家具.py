#案例二
#摆放家具

#家具类
class HouseItem:
    def __init__(self,name,area):
        self.name=name
        self.area=area
    
    def __str__(self):
        #返回家具的描述
        return f"家具:{self.name},面积为{self.area}"

#添加家具
bed=HouseItem("床",4)
chest=HouseItem("衣柜",2)
table=HouseItem("餐桌",1.5)
print(bed)
print(chest)
print(table)

print("")


#房子类
class House:
    def __init__(self,house_type,area):
        self.house_type=house_type
        self.area=area
        #家具名称列表
        self.item_list=[]
        #剩余面积
        self.free_area=area

    def __str__(self):
        return f"{self.house_type},面积为{self.area}"

    def add_item(self,item):
        self.item=item
        self.free_area-=item.area
        #判断面积
        if self.free_area<=0:
            print(item.name+"摆放失败")
            return
        # else:
        #家具名称添加进列表
        self.item_list.append(item.name)
        print(self.item_list)
        print(item.name+"摆放成功")
        #打印剩余面积
        print(f"房间面积还剩{self.free_area}")

p1=House("单间",10)
print(p1)
p1.add_item(bed)
p1.add_item(chest)
p1.add_item(table)
#多摆放一张床
p1.add_item(bed)



