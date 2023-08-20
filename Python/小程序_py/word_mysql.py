#实现 生单词.txt读取和写入数据库
import pymysql
import re
try:
    db=pymysql.connect(
        host="127.0.0.1",
        user="test",
        password='123456',
        database='test',
        charset='utf8',
        port=3306
    )
except Exception as e:
    print("数据库连接失败",e)
else:
    print("数据库连接成功")
    folderName='C:\\Users\\Lenovo\\Desktop\\生单词.txt'
    count=0
    try:
        cursor=db.cursor()
        sql1_create_database="create database if not exists PC_English default character set 'utf16';"
        sql2_use_database="use pc_english;"
        #创建表使用unique唯一约束,作用是区分记录的唯一性,防止重复插入
        sql3_create_table="create table if not exists word(序号 int primary key auto_increment,英文 varchar(40) unique,词性 varchar(8),中文 varchar(50),出处 varchar(100));"
        cursor.execute(sql1_create_database)
        cursor.execute(sql2_use_database)
        cursor.execute(sql3_create_table)
        with open(folderName,"r",encoding="utf-8") as f2:
            content=f2.readlines()#读取文本内容,返回list
        for i in content:
            count+=1
            txt=re.split('\s+',i)#去除任意空白字符
            print(count,txt[0],txt[1],txt[2],txt[3])
            #ignore关键字+unique,防止重复       
            sql4_insert_info='insert ignore into word(英文,词性,中文,出处) values(%s,%s,%s,%s);'
            args1=(txt[0],txt[1],txt[2],txt[3])
            cursor.execute(sql4_insert_info,args=args1)
            db.commit()
        print("数据插入完毕")
        sql5_select_info='select * from word;'
        cursor.execute(sql5_select_info)
        print(cursor.fetchall())
    except Exception as e:
        db.rollback()
        print("错误："+str(e))
    finally:
        cursor.close()
        db.close()