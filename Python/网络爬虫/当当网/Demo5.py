# # import re
# # list=[['httpNone', ' 威尔斯科幻小说精选：星球大战', '¥16.50'], 
# # ['http//img3m5.ddimg.cn/47/30/28526105-1_b_2.jpg', ' 威尔斯科幻小说精选：时间机器', '¥10.00'], 
# # ['http//img3m6.ddimg.cn/48/31/28526106-1_b_3.jpg', ' 威尔斯科幻小说精选：隐身人', '¥13.50'], 
# # ['http//img3m3.ddimg.cn/50/9/29438393-1_b_2.jpg', ' 时光球原创少儿科幻小说：无边量子号 火星', '¥12.30'], 
# # ['http//img3m1.ddimg.cn/79/17/29256361-1_b_3.jpg', ' 未来的序曲：二十一世纪科幻小说杰作选（全2册）', '¥39.50'],
# # ['http//img3m4.ddimg.cn/0/12/29270934-1_b_3.jpg', ' 弦歌：中篇科幻小说集（收获·科幻故事空间站丛书. 第一辑）', '¥19.00'],
# # ['http//img3m8.ddimg.cn/59/0/26444048-1_b_3.jpg', ' 2018中国年度科幻小说', '¥20.80'], 
# # ['http//img3m7.ddimg.cn/73/15/28993807-1_b_3.jpg', ' 龙虾星球 （科幻小说）', '¥21.00'],
# # ['http//img3m3.ddimg.cn/77/20/29195573-1_b_2.jpg', ' 人工智能科幻小说丛书:讲故事的机器人', '¥19.00']]
# # dict={}
# # # for a,b,c in list:
# # #     print(a,b,c)

# # # for a,b,c in list:
# # sum=0
# # for i in list:
# #     sum+=1
# #     dict["book"+str(sum)]=i
# #     # print(i[0],i[1],i[2])

# # r=re.compile(r'book[0-9]+')
# # # for i in dict:
# # #     # print(dict["book1"][0])
# # a=r.findall("book1 gggg")
# # print(a)
# # print(r.findall(str(dict)))

# # for i in dict:
# #     b=r.findall(i)
# #     # a=dict(b)[0]
# #     # print(a)
# #     print(dict(str(i)[b]),[0])
# # # print(dict)

# a=[0,1,2]
# b=[3,4,5]
# c=[7,8,9]
# # number={}
# # count=0
# # for i,j,k in a,b,c:
# #     # print(i)
# #     # print(j)
# #     # print(k)
# #     count+=1
# #     # number["a"+str(count)]=i
# #     # number["b"+str(count)]=j
# #     # number["c"+str(count)]=k
# #     number["a"+str(count),"b"+str(count),"c"+str(count)]=[i,j,k]
# # print(number)
a={'book1': ['http:None', ' 威尔斯科幻小说精选：星球大战', '¥16.50'], 
    'book2': ['http://img3m5.ddimg.cn/47/30/28526105-1_b_2.jpg', ' 威尔斯科幻小说精选：时间机器', '¥10.00'], 
    'book3': ['http://img3m6.ddimg.cn/48/31/28526106-1_b_3.jpg', ' 威尔斯科幻小说精选：隐身人', '¥13.50'], 
    'book4': ['http://img3m3.ddimg.cn/50/22/28977053-1_b_3.jpg', ' 未来之城：科幻小说中的城市', '¥34.80'], 
    'book5': ['http://img3m9.ddimg.cn/35/12/27850319-1_b_23.jpg', ' 2001：太空漫游（永恒不朽的科幻经典，全球公认“一生bi读”的科幻神作。刘慈欣：“我所有作品都是对《太空漫游》的拙劣模', '¥31.00'], 
    }

for key,values in a.items():
    print(values[0])

# b={'book6': ['http://img3m6.ddimg.cn/81/10/29370906-1_b_1.jpg', ' 科幻小说三部曲：流浪地球+水星播种+人类基地（套装全3册）', '¥74.20'], 
#     'book7': ['http://img3m4.ddimg.cn/45/4/29299194-1_b_11.jpg', ' 银河帝国：基地（人类历史上不容错过的系列小说。被马斯克用火箭送上太空的科幻神作，百万册珍藏版）（读客科幻文库）', '¥22.50'],
#     'book8': ['http://img3m8.ddimg.cn/18/7/24168888-1_b_26.jpg', ' 沙丘（同名电影狂揽6项奥斯卡大奖！《降临》导演执导、“甜茶”“灭霸”、张震等主演。每个“不可不读”的书单上都有《沙丘》）', '¥34.00'],
#     'book9': ['http://img3m0.ddimg.cn/97/11/29113720-1_b_3.jpg', ' 人造神祇：中国人工智能科幻小说佳作选', '¥24.50'], 
#     'book10': ['http://img3m5.ddimg.cn/20/2/29214425-1_b_5.jpg', ' 明日杀机——中国惊险悬疑科幻小说佳作选', '¥24.50']
# }

# with open("C:\\Users\\Lenovo\\Desktop\\"+"当当.txt","w",encoding="utf-8") as f2:
#     for key1,key2 in zip(a.keys(),b.keys()):
#         print(a[key1][1:])
#         # f2.write(a[key1][2])
#         # for i in a[key1][1]:
#         #     print(i)
#         #     f2.write(i)
#         # for i in a[key1][1]:
#             # f2.write(a[key1][1])
#             # f2.write(a[key1][2])
#         # print(a[key1][1])


