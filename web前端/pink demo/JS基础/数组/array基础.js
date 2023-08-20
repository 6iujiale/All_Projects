/* 
数组
    定义
        一组数据的集合
    创建
        var arr=new Array();//创建一个空数组
        var arr=[]
    获取数组元素
        arr[索引],从0开始计数
    修改数组元素
        arr[索引]=值
    数组的遍历
        for(var i=0;i<arr.length;i++){
            console.log(arr[i])
        } 
*/
var arr=[1,2,3,4,"ljl",true];//js数组,可以放任意类型数据
//获取数组元素
console.log(arr[0]);//1
//修改数组元素
//修改true->flase
arr[arr.length-1]=false;
console.log(arr);//[ 1, 2, 3, 4, 'ljl', false ]

//数组的遍历
var arr2=["星期一","星期二","星期三","星期四","星期五"];
for(var i=0;i<arr2.length;i++){
    console.log(arr2[i])
}
