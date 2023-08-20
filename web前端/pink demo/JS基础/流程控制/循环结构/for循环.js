/* 
for(初始化变量;条件表达式;操作表达式){
    循环体
}

*/


/* 案例一：用户输入结束的范围,打印0~这个反胃的数字
var num=prompt("输入一个范围")
假设范围为10 */
/* var num=10;
for(var i=0;i<num;i++){
    console.log(i)
} */
 
/* 案例二：求1~10之间的和 */
var sum=0
for(var i=0;i<=10;i++){
    sum+=i
}
console.log(sum)//55


/* 案例三：求1~100之间,奇数的和与偶数的和 */
var even=0//偶数
var odd=0//奇数
for(var i=1;i<=100;i++){
    if(i%2==0){
        even+=i
    }else{
        odd+=i
    }
}
console.log("偶数总和是"+even)//2550
console.log("奇数总和是"+odd)//2500


/* 案例四：打印⭐,打印到一行*/
/* var str=""//打印到一行,使用追加字符串的方式
for(var i=0;i<5;i++){
    str+="⭐"
}
console.log(str) */


/* 案例五：打印五行五列的星星 */
var str="";
for(var i=0;i<5;i++){//外层循环负责打印五行
    for(var b=0;b<5;b++){//内层循环负责一行打印5个
        str+="⭐";
    }
    //如果一行打印完毕5个星星就要另起一行 \n
    str+="\n"
}
console.log(str);


/* 案例六：打印倒三角 */
var str="";
for(var i=0;i<5;i++){//外层循环负责打印五行
    for(var b=i;b<5;b++){//内层循环负责一行打印5个
        str+="⭐";
    }
    //如果一行打印完毕5个星星就要另起一行 \n
    str+="\n"
}
console.log(str);


/* 案例七：九九乘法表 */
str=""
for(var i=1;i<=9;i++){
    for(var j=1;j<=i;j++){
        str+=j+"x"+i+"="+i*j+"\t"
    }
    str+="\n"
}
console.log(str); 












