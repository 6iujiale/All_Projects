"""
导入模块(sys && PyQt5.QtWidgets)
创建QApplication对象
创建QWidget对象
关闭窗口 
""" 

#setWindowsTitle    窗口标题


from cProfile import label
import sys
from PyQt5.QtWidgets import *
if __name__=="__main__":
    # 创建QApplication对象
    app=QApplication([])# sys.argv：运行时的命令参数传递给QApplication对象
    # 创建QWidget对象
    w=QWidget()
    # 设置窗口标题
    w.setWindowTitle("刘佳乐的第一个QT界面")
    def showMSG():
       QLabel(w, "信息提示框", "OK，弹出测试信息")
    btn = QPushButton("测试按钮", w)
    btn.clicked.connect(showMSG)
    # 展示窗口
    w.show()
    # 关闭窗口
    sys.exit(app.exec_())

