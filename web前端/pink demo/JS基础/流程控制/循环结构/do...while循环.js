/* 
格式
    do{
        循环体
        计数器++;/计算器--
    }while(条件表达式)
*/


/* 案例一：打印1~10之间的数字 */
var i=0;
do{
    console.log(i);
    i++;
}while(i<=10);


/* 案例二：计算1~100的数字和 */
var i=1;
var sum=0;
do{
    sum+=i;
    i++;
}while(i<=100)
console.log(sum)//5050

