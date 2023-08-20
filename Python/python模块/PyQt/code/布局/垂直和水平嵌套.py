#盒子布局
    #水平布局
        #QHBoxLayout   
    #垂直布局
        #OVBoxLayout

import sys
from PyQt5.QtWidgets import *
class MyWindows(QWidget):
    def __init__(self):
        super().__init__()
        self.init_ui()
        
    
    def init_ui(self):
        #最外层的垂直布局，包含两部分：爱好和性别
        container=QVBoxLayout()
        #爱好
        hobby_box=QGroupBox("爱好")
        #爱好容器
        v_layout=QVBoxLayout()
        btn1=QRadioButton("睡觉")
        btn2=QRadioButton("吃饭")
        btn3=QRadioButton("发呆")
        #添加到v_layout中
        v_layout.addWidget(btn1)
        v_layout.addWidget(btn2)
        v_layout.addWidget(btn3)
        #把v_layout添加到hobby_box中
        hobby_box.setLayout(v_layout)

        #性别
        gender_box=QGroupBox('性别')
        #性别选项
        h_layout=QHBoxLayout()
        btn1=QRadioButton("男")
        btn2=QRadioButton("女")
        #追加到性别容器
        h_layout.addWidget(btn1)
        h_layout.addWidget(btn2)
        #添加到box中
        gender_box.setLayout(h_layout)

        #爱好内容添加到容器中
        container.addWidget(hobby_box)
        container.addWidget(gender_box)

        #设置窗口显示的内容是最外层容器
        self.setLayout(container)

if __name__=="__main__":
    app=QApplication(sys.argv)
    w=MyWindows()
    w.show()
    w.resize(300,300)
    w.setWindowTitle("垂直和水平嵌套")
    app.exec()



