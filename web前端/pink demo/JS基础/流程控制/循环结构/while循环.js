/* 
定义计数器
while(条件表达式){
    代码体
    计数器++/计数器--
}
*/

/* 案例一：打印1~100 */
var i=1;
while(i<=100){
    console.log(i);
    i++; 
}

/* 案例二：计算1~100的和 */
var i=1;
var sum=0;
while(i<=100){
    sum+=i
    i++
} 
console.log(sum)//5050


