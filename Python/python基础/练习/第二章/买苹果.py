#超市买苹果初级版
price=8.5
weight=7.5
money=weight*price
print(money)



#超市买苹果返五块钱
price=8.5
weight=7.5
money=weight*price
money=money-5
print(money)
 

#超市买苹果增强版
price_str=float(input("苹果的单价："))
weight_str=float(input("苹果的重量："))
money=int(price_str*weight_str)
money_all=str(money)
print("商品总金额为："+money_all)

