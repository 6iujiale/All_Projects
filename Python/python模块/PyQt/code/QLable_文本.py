import sys
from PyQt5.QtWidgets import QApplication,QWidget,QPushButton,QLabel
if __name__=="__main__":
    app=QApplication(sys.argv)
    w=QWidget()
    #窗口主题
    w.setWindowTitle("文本窗口")
    #按钮
    btn=QPushButton("按钮一",w)
    btn.setGeometry(200,200,200,50)
    #文本
    label=QLabel("账号:",w)#"文本",父亲(w)
    label.setGeometry(20,50,50,20)
    #设置窗口大小
    #x,y,w,h
    #xy坐标 w,h宽高
    label.setGeometry(100,100,400,100)
    #设置父对象
    # btn.setParent(w)
    w.show()
    app.exec()

