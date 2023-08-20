import sys
from PyQt5.QtWidgets import *
class Windows(QWidget):
    def __init__(self):
        super().__init__()
        self.init_ui()

    def init_ui(self):
        self.setFixedSize(300,150)
        #外层容器
        container=QVBoxLayout()
        #表单容器
        form_layout=QFormLayout()
        #账号输入框
        edit1=QLineEdit()
        edit1.setPlaceholderText("请输入账号：")
        form_layout.addRow("账号：",edit1)
        #密码输入框
        edit2=QLineEdit()
        edit2.setPlaceholderText("请输入密码：")
        form_layout.addRow("密码：",edit2)

        #form_layout添加到垂直布局容器里
        container.addLayout(form_layout)
       

        #按钮
        login_btn=QPushButton("登录")
        login_btn.setFixedSize(100,30)
        container.addWidget(login_btn)
    
        self.setLayout(container)

if __name__=="__main__":
    app=QApplication(sys.argv)
    w=Windows()
    w.show()
    # w.resize()
    w.resize(300,200)
    w.setWindowTitle("表单布局")
    app.exec()


