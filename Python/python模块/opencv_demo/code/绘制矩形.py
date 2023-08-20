# 导入模块
# 读取图片
# 绘制矩形
# 显示图片
# 等待
# 释放内存

# ../images/face1 (1).jpg
import cv2
a=cv2.imread("../images/face1 (1).jpg")
# 绘制矩形
# cv2.rectangle(image,starting vertex,opposit vertex,color,thickness)
# cv2.rectangle(对象，(左上角坐标),(右下角),(颜色),(粗细))
cv2.rectangle(a,(20,20),(230,250),(65,111,123),2)
cv2.imshow("图片",a)
cv2.waitKey(0)
cv2.destroyAllWindows()






