#盒子布局
    #水平布局
1        #QHBoxLayout   
    #垂直布局
        #OVBoxLayout

import sys
from PyQt5.QtWidgets import QApplication,QWidget,QVBoxLayout,QPushButton

class MyWindows(QWidget):
    def __init__(self):
        #调用父类的__init__方法
        super().__init__()
        #设置窗口大小
        self.resize(300,300)
        self.setWindowTitle("垂直布局")
        #垂直布局
        layout=QVBoxLayout()
       
        #按钮
        btn1=QPushButton("按钮一")
        #添加到布局器中
        layout.addWidget(btn1)#Widget控件
        btn2=QPushButton("按钮二")
        layout.addWidget(btn2)
        btn3=QPushButton("按钮三")
        layout.addWidget(btn3)

        #添加伸缩器，不加空间平均分配空间
        layout.addStretch()
        
        #让当前的窗口使用垂直布局器
        self.setLayout(layout)
if __name__=="__main__":
    app=QApplication(sys.argv)
    w=MyWindows()
    w.show()
    app.exec()

