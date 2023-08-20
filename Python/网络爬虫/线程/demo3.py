import os
import re
from PIL import Image

path="./"
dir_list=os.listdir(path)
res="Images?\S"
result=bool(re.search(res,str(dir_list)))
if result is True:
    s_match=re.findall(res,str(dir_list))
    count=0
    for i in s_match:
        for j in os.listdir(f"{path}{i}/"):
            im = Image.open(f"{path}{i}/{j}")
            im = im.convert('RGB')
            out = im.resize((500,500), Image.ANTIALIAS)#宽,高
            filename=os.path.basename(f"{path}{i}/{j}")
            try:
                os.mkdir(f"./test_{i}/")
            except FileExistsError:
                pass
            finally:
                out.save(f'./test_{i}/{filename}')
else:
    print(f"{dir_list}没有Images文件夹")
print(dir_list)