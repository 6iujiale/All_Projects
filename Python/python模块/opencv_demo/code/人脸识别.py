# 导入模块
# 定义人脸检测函数
# 读取图片
# 灰度转换
# 加载人脸识别器(CascadeClassifier) haarcascade_frontalface_alt2.xml
# 检测图像所有面孔(detectMultiScale)
# 遍历人像绘制矩形
# 显示图片
# 等待
# 释放内存
# 调用人脸检测函数

import cv2
def face_detect_demo(img_url):# 传入图片相对地址
    img=cv2.imread(img_url)
    gray=cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    # D:/Opencv/opencv/sources/data/haarcascades/haarcascade_frontalface_alt2.xml
    face_detect=cv2.CascadeClassifier('D:/Opencv/opencv/sources/data/haarcascades/haarcascade_frontalface_alt2.xml')#加载人脸识别器
    face=face_detect.detectMultiScale(gray)##检测图像中的所有面孔
    # red=(0,0,255)
    for x,y,w,h in face:
        cv2.rectangle(img,(x,y),(x+w,y+h),(0,0,255),thickness=2)
    cv2.imshow('人脸检测',img)
    cv2.waitKey(0)
    cv2.destroyAllWindows()
face_detect_demo("../images/face1 (2).jpg")
