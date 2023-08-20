
import sys
from PyQt5.QtWidgets import QApplication,QWidget,QPushButton
if __name__=="__main__":
    app=QApplication(sys.argv)
    w=QWidget()
    w.setWindowTitle("显示按钮界面")
    btn=QPushButton("按钮1",w)
    # btn.setParent(w)
    w.resize(500,500)
    w.show()
    app.exec()








