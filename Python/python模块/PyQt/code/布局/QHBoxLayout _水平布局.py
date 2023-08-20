#盒子布局
    #水平布局
        #QHBoxLayout   
    #垂直布局
        #OVBoxLayout

import sys
from PyQt5.QtWidgets import QApplication,QWidget,QHBoxLayout,QPushButton,QVBoxLayout

class MyWindows(QWidget):
    def __init__(self):
        super().__init__()
        self.resize(1000,400)
        layout=QHBoxLayout()
        #按钮一
        btn1=QPushButton("按钮一")
        # btn1.setGeometry()
        btn2=QPushButton("按钮二")
        btn3=QPushButton("按钮三")
        layout.addWidget(btn1)
        layout.addWidget(btn2)
        layout.addWidget(btn3)
        self.setLayout(layout)

if __name__=="__main__":
    app=QApplication(sys.argv)
    w=MyWindows()
    w.show()
    app.exec()


