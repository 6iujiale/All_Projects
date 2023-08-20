# 前提准备
    # 读取视频文件
    # 加载人脸识别器
# 循环读取每一帧
    # 视频存在,读取
        # 灰度转换
        # 检测所有面孔
        # 遍历视频绘制矩形
        # 显示视频
    # 视频不存在,终止循环
# 释放视频or摄像头
# 等待
# 释放内存


# import cv2
# # 读取视频文件
# bp = cv2.VideoCapture("../video/v1.mp4")
# # 值为0是读取摄像头
# bp = cv2.VideoCapture(0)  # <class 'cv2.VideoCapture'>
# # 加载人脸识别器
# face_detect=cv2.CascadeClassifier('D:/Opencv/opencv/sources/data/haarcascades/haarcascade_frontalface_alt2.xml')
# # 循环读取每一帧
# while True:
#     isTrue, frame = bp.read()
#     if isTrue: # isTrue 值为true 读取视频
#         gray_bp=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
#         # resize_img=cv2.resize(gray_bp,dsize=(1000,1000))
#         # cv2.imshow("修改后大小",resize_img)
#         face=face_detect.detectMultiScale(gray_bp)##检测图像中的所有面孔
#         for x,y,w,h in face:
#             cv2.rectangle(frame,(x,y),(x+w,y+h),(0,0,255),thickness=2)
#         cv2.imshow('人脸检测',frame)
#         # print(resize_img.shape)
#         # 设置退出键和展示频率
#         if cv2.waitKey(5) & 0xFF == ord('d'):
#             break
#     else:#isTrue 值为false 打印"没有视频可被读取"
#         print("没有视频可被读取")
#         break
# # 释放视频 || 摄像头
# bp.release()
# cv2.waitKey(0)
# cv2.destroyAllWindows()


import cv2
# 读取视频文件
bp = cv2.VideoCapture(0)  # <class 'cv2.VideoCapture'>
# 加载人脸识别器
face_detect=cv2.CascadeClassifier('D:/Opencv/opencv/sources/data/haarcascades/haarcascade_frontalface_alt2.xml')
# 循环读取每一帧
while True:
    isTrue, frame = bp.read()
    if isTrue: # isTrue 值为true 读取视频
        gray_bp=cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
        resize_img=cv2.resize(gray_bp,dsize=(1000,1000))
        cv2.imshow("修改后大小",resize_img)

        face=face_detect.detectMultiScale(gray_bp)##检测图像中的所有面孔
        for x,y,w,h in face:
            cv2.rectangle(frame,(x,y),(x+w,y+h),(0,0,255),thickness=2)
        cv2.imshow('人脸检测',frame)
        print(resize_img.shape)
        # 设置退出键和展示频率
        if cv2.waitKey(5) & 0xFF == ord('d'):
            break
    else:#isTrue 值为false 打印"没有视频可被读取"
        print("没有视频可被读取")
        break
# 释放视频 || 摄像头
bp.release()
cv2.waitKey(0)
cv2.destroyAllWindows()