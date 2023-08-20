#导入临时文件模块
from tempfile import TemporaryDirectory,TemporaryFile

with TemporaryDirectory() as temp_dir:
    print("文件已经创建：",temp_dir)#获取临时文件的位置


with TemporaryFile(mode="w+") as temp_file:
    temp_file.write("我是临时文件")
    temp_file.seek(0)
    data=temp_file.read()
    print(data)
