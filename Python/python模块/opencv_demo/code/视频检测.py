# 导入模块
# 读取视频
# 循环读取每一帧
    # 视频存在,读取。
    # 视频不存在,终止循环。
# 释放视频 || 摄像头
# 等待
# 释放内存

import cv2
# 读取视频文件
bp = cv2.VideoCapture("../video/v1.mp4")  # <class 'cv2.VideoCapture'>
# 循环读取每一帧
while True:
    # bp.read() : 一次读取视频中的每一帧，会返回两个值；
    # isTrue : 为bool类型表示这一帧是否真确读取，正确读取为True，如果文件读取到结尾，它的返回值就为False;
    # frame : 表示这一帧的像素点数组
    isTrue, frame = bp.read()
    if isTrue: # isTrue 值为true 读取视频
        cv2.imshow('My Video', frame)
        if cv2.waitKey(10) & 0xFF == ord('d'):
            break
    else:#isTrue 值为false 打印"没有视频可被读取"
        print("没有视频可被读取")
        break
# 释放视频 || 摄像头
bp.release()
cv2.waitKey(0)
cv2.destroyAllWindows()



