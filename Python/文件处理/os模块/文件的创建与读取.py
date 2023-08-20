""" 
文件的创建与读取
    open(file_name,mode,encoding)

文件读写模式
    r、w、a(追加)
    r+:可读可写,文件不存在会报错,写操作会被覆盖
    w+:可读可写,文件不存在先创建,会覆盖
    a+:可读可写,文件不存在先创建,不会覆盖,追加到末尾
"""

""" 
file=open("backup/文本3.txt","tr")#t：文本,r：读取
content=file.read()#默认：读取里面的所有内容
print(content)
file.close()
 """

#上下文管理器,好处使用后可不close
with open("backup/文本3.txt","r+") as f:
    content=f.read()
    f.write("123")
    print(content)
