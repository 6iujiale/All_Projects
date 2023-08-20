#对象.信号.content(槽函数)、

import sys
from PyQt5.QtWidgets import *

class Windows1(QWidget):
    def __init__(self):
        super().__init__()
        QLabel("我是2要显示的内容",self)
        self.setStyleSheet("background_color:yellow")


class Windows2(QWidget):
    def __init__(self):
        super().__init__()
        QLabel("我是2要显示的内容",self)
        self.setStyleSheet("background_color:red")

class MyWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.create_stacked_layout()
        self.init_ui()

    def create_stacked_layout(self):
        #创建抽屉布局
        self.stacked_layout=QStackedLayout()
        #创建单独的Widget
        win1=Windows1()
        win2=Windows2()
        #将创建的2个Widget添加到抽屉布局容器中
        self.stacked_layout.addWidget(win1)
        self.stacked_layout.addWidget(win2)

    def init_ui(self):
        self.setFixedSize(300,270)
        #1.创建整体的布局其
        container=QVBoxLayout()
        #2.创建1个要显示具体内容的子Widget
        widget=QWidget()
        widget.setLayout(self.stacked_layout)
        widget.setStyleSheet("background-color:grey")

        #3.创建2个按钮，用来点击进行切换抽屉布局器中的Widget
        btn_press1=QPushButton("抽屉1")
        btn_press2=QPushButton("抽屉2")
        #按钮添加时间(即点击后要调用的函数)
        btn_press1.clicked.connect(self.btn_press1_clicked)
        btn_press2.clicked.connect(self.btn_press2_clicked)

        container.addWidget(widget)
        container.addWidget(btn_press1)
        container.addWidget(btn_press2)

        #5.设置当前要显示的Widget，从而能够显示布局器中的控件
        self.setLayout(container)

    def btn_press1_cliced(self):
        #设置抽屉布局器中当前索引值，即可切换显示哪个widget
        self.stacked_layout.setCurrentIndex(0)

    def btn_press2_cliced(self):
        #设置抽屉布局器中当前索引值，即可切换显示哪个widget
        self.stacked_layout.setCurrentIndex(1)

if __name__=="__main__":
    app=QApplication(sys.argv)
    w=MyWindow()
    w.show()
    w.resize(600,600)
    w.setWindowTitle("信号与槽")
    app.exec()


