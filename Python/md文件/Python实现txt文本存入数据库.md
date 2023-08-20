# Python将txt文本存入MYSQL数据库

## 1、创建txt文本

![image-20230131140311093](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20230131140311093.png)

## 2、脚本编写

### 2.1导入pymysql模块

```python
import pymysql
```

### 2.2进行数据库连接

```python
try:
    db=pymysql.connect(
        #默认为本机地址
        host="127.0.0.1",
        #用户名
        user="用户名",
        #密码
        password='密码',
        #指定数据库
        database='指定数据库',#database所指定的数据库必须真实存在
        charset='utf8',
        port=3306
    )
except Exception as e:
    print("数据库连接失败",e)
else:
    print("数据库连接成功")
```

### 2.3编写sql语句

```python
#先创建一个游标对象
cursor = connect.cursor()
#使用游标对象来执行所写的sql语句,比如新建一个数据库(pc_english)用于存放文本内容
sql='drop database PC_English;'
#执行sql语句
cursor.execute(sql)
#获取查询结果
cursor.fetchall()  # 全量查询结果
cursor.fetchone()  # 单条查询结果
#sql语句写完后,需要关闭游标
cursor.close()
```

### 2.4关闭于数据库的连接

```python
connect.close()
```

## 3、完整代码

```python
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
    folderName='C:\\Users\\Lenovo\\Desktop\\生单词2.txt'
    count=0
    try:
        cursor=db.cursor()
        sql='drop database PC_English;'
        cursor.execute(sql)
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
```

## 4、运行结果

![image-20230131141918570](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20230131141918570.png)