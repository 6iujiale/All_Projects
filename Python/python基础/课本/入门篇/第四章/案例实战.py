print('定制自己的手机套餐')

talk={
    '1':'0分钟',
    '2':'50分钟',
    '3':'100分钟',
    '4':'300分钟',
    '5':'不限量',
    }
for key,values in talk.items():
    print('%s.'%key,values)

    


flow={
    '1':'0M',
    '2':'500M',
    '3':'1G',
    '4':'5G',
    '5':'不限量',
}
for key,values in flow.items():
    print('%s.'%key,values)

note={
    '1':'0条',
    '2':'50条',
    '3':'100条',
}
for key,values in note.items():
    print('%s.'%key,values)