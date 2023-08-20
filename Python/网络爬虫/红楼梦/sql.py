# import 自定义加全部
import pymysql
#远程连接数据库
db = pymysql.connect(
    host='127.0.0.1',
    port=3306,
    user='root',
    passwd='liujiale',
    db='test2',
    charset='utf8'
)

#使用cursor()方法获取操作游标 
cursor = db.cursor()
try:

    sql = 'use test2' 
    # cursor.execute(sql)
    # print('未创建数据库前：',cursor.fetchall()) #获取创建数据库前全部数据库

    # sql="create table 红楼梦(标题 varchar(30),内容 tinytext)"
    # cursor.execute(sql)
    # # sql="insert into 红楼梦 values();"

    # with open("C:\\Users\\Lenovo\\Desktop\\红楼梦.txt","a",encoding="utf-8") as f2:
        # [f2.write(i) for i in all]
        
        # sql="into table person(标题,内容) values(%s,%s);"
        # # print(all)
        # args=(all,all)
        # cursor.execute(sql,args=args)
        # cursor.execute(sql)
    # sql="truncate  table 红楼梦"
    # cursor.execute(sql)
    with open("红楼梦.txt","r",encoding="utf-8")as f:
        dates=f.readlines()
        print(dates)
        # dates =[i for i in str.split("\n") if i!=""]   
    for i in dates:
        sql1='insert into 红楼梦(标题,内容) values(%s,%s)'
        args =(dates[0],dates[1])
        cursor.execute(sql1,args=args)
        db.commit()
except Exception as e:
    print(e)
    db.rollback()  #回滚事务

finally:

    cursor.close() 
    db.close()  #关闭数据库连接



