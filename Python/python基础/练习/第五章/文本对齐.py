# 文本对齐
    # string.ljust(width)   字符串左对齐
    # string.rjust(width)   字符串右对齐
    # string.center(width)  字符串居中  

#去除空白字符
    # string.lstrip()   截掉string左边(开始)的空白字符串
    # string.rstrip()   截掉string右边(结束)的空白字符串
    # string.strip()   截掉string左右两边的空白字符串

'''
#要求顺序并且居中对齐输出以下内容
poem=['登鹳雀楼',
        '王之涣',
        '白日依山尽',
        '黄河入海流',
        '欲穷千里目',
        '更上一层楼',]
for poem_str in poem:
    print('|%s|'%poem_str.center(10))#居中对齐输出内容
    print('|%s|'%poem_str.ljust(10))#向左对齐输出内容
    print('|%s|'%poem_str.rjust(10))#向右对齐输出内容
'''
#去除空格并对齐 
poem=['\t\n登鹳雀楼',
        '王之涣',
        '白日依山尽',
        '黄河入海流',
        '欲穷千里目',
        '更上一层楼',]
for poem_str in poem:
    print('|%s|'%poem_str.strip().center(10))