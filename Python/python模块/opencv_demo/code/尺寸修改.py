# 导入模块
# 读取图片
# 修改尺寸
# 显示图片(原图+修改后)
# 打印图片大小(原图+修改后)
# 等待
# 释放内存

import cv2
img=cv2.imread("../images/face1 (1).jpg")
resize_img=cv2.resize(img,dsize=(500,500))# 对像+尺寸(高+宽)
cv2.imshow("修改前",img)
cv2.imshow("修改后",resize_img)
print("修改前",img.shape[2])# 显示宽高  0高 1为宽
print("修改后",resize_img.shape)
cv2.waitKey(0)
cv2.destroyAllWindows()

