from unittest import result


peom_str='登鹳雀楼\t \t王之涣  白日依山尽\n 黄河入海流\t\n 欲穷千里目 更上一层楼'
print(peom_str)
# 1.拆分字符串
pemo_list=peom_str.split() #拆分为字符串列表
print(pemo_list)
# 2.合并字符串
result=' '.join(pemo_list)  
print(result)


