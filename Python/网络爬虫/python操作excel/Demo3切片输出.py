import openpyxl
import os
path="C:\\Users\\Lenovo\\Desktop\\ex6.xlsx"
bk=openpyxl.load_workbook(path)#使用openpyxl创建工作表
sheet1=bk["成绩表"]
a=list(sheet1.values)
aa=list(sheet1.values)[1:3]#切片
# print(a)
print(aa)


