/* 
数组常用方法
    获取数组长度
        arr.length 
*/

//数组求和及平均值
var arr=[1,2,3,4,5];
var sum=0;
for(var i=0;i<arr.length;i++){
    sum+=arr[i];
}
var avg=sum/arr.length
console.log("总和是"+sum)
console.log("平均值是"+avg)

//求数组的最大值
/*
1.取出数组的第一个值(max)
2.max依次和数组的元素相比较
3.如果元素比max大,值存入max里面,max的值永远是最大 
*/
var max=arr[0];
for(var i=0;i<arr.length;i++){
    // 如果元素比max大,值存入max里面
    if(arr[i]>max){
        max=arr[i]//max的值永远是最大 
    }
}
console.log(max);

//分割数组
var arr2=["red","green","gray","blue","purple"]



