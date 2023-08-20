# 导入模块
# 读取图片
# 灰度转换
# 查看原图or灰度图
# 保存灰度图片
# 等待
# 释放内存

import cv2
img = cv2.imread("../images/face1 (1).jpg")
# 灰度图片
# cv2.COLOR_BGR2GRAY
gray_img=cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
# 查看原图
cv2.imshow("原图", img)
# 查看灰度转换图
cv2.imshow("灰度转换图",gray_img)
# 保存灰度图
cv2.imwrite("../images/face_gray.jpg",gray_img)# 路径+对象
cv2.waitKey(0)
cv2.destroyAllWindows()

# path="../images/face_gray.jpg"
# # 上下文管理器
# with open(path,"w") as f2:
#     f2.write(gray_img.content)


