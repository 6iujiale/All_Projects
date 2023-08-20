#size   大小
#move   位置

import sys
from PyQt5.QtWidgets import QApplication,QWidget,QLabel
if __name__=="__main__":
    # 创建QApplication对象
    app=QApplication(sys.argv)# sys.argv：运行时的命令参数传递给QApplication对象
    # 创建QWidget对象
    w=QWidget()
    # 设置窗口标题
    w.setWindowTitle("刘佳乐的第一个QT界面")
    #hello world文本
    label=QLabel("Hello world",w)
    # 展示窗口
    w.show()
    w.resize(1000,500)#设窗口大小
    # w.move(0,0)#电脑桌面为参考坐标
    # 关闭窗口
    app.exec()

