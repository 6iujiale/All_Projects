import sys
from PyQt5.QtWidgets import QApplication,QWidget,QLabel,QPushButton,QLineEdit
if __name__=="__main__":
    app=QApplication(sys.argv)
    w=QWidget()
    #设置窗口标题
    w.setWindowTitle("输入框窗口")

    #纯文本
    label=QLabel("账号",w)
    label.setGeometry(20,50,50,30)

    #文本框
    edit=QLineEdit(w)
    edit.setPlaceholderText("请输入账号：")
    edit.setGeometry(80,40,200,50)
    
    #按钮
    dtn=QPushButton("注册",w)
    dtn.setGeometry(120,120,100,50)

    w.resize(500,200)

    #展示窗口
    w.show()
    #等待
    app.exec()

