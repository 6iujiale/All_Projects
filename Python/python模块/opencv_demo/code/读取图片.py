# # 导入cv2模块
# # 显示图片
# # 展示图片
# # 等待
# # 释放内存
# import cv2
# a = cv2.imread("images/face1 (1).jpg")  # 路径
# cv2.imshow("ljl", a)  # 窗口名+对象
# cv2.waitKey(0)
# cv2.destroyAllWindows()



import cv2 
from PIL import Image
img=cv2.imread("images/pics/background.jpg")
temp1=cv2.imread("images/pics/template.png")
width,height,c=temp1.shape
results=cv2.matchTemplate(img,temp1,cv2.TM_SQDIFF_NORMED)
minValue,maxValue,minLoc,maxLoc=cv2.minMaxLoc(results)
resultsPoint1=minLoc
resultsPoint2=(resultsPoint1[0]+width,resultsPoint1[1]+height)
cv2.rectangle(img,resultsPoint1,resultsPoint2,(0,0,225),2)
cv2.imshow("ljl",img)
cv2.waitKey(0)
cv2.destroyAllWindows()


#画一个正方形,边长100,粗细3,颜色红

import cv2
import numpy as np
huabu=np.zeros((300,300,3),np.uint8)
huabu=cv2.rectangle(huabu,(100,100),(200,200),(0,0,225),2)
cv2.imshow("ljl",huabu)
cv2.waitKey(0)
cv2.destroyAllWindows()








