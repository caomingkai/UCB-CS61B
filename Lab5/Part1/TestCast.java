//1-－－14行Y y1=(Y)x1;必须加（Y）,编译才能通过；并且只有x1真正指向的是Y类型的对象（11行），运行才能通过（即便编译已经通过了也不行）
//2-－－该程序第二部分为测试对象构成的数组的cast情况

public class TestCast {

     public static void main(String[] args) {
         
         X x=new X();
         Y y=new Y();
         System.out.println(x.a);
         System.out.println(y.a+" "+y.b);
         
         X x1=y;
         System.out.println(x1.a);
         
         Y y1=(Y)x1;
         System.out.println(y1.a+" "+y1.b);
//--------------------对象数组---------------------

         X[] xa=new X[3];
         Y[] ya=new Y[3];
         for(int i=0;i<3;i++){
             xa[i]=new X();
         }
         for(int i=0;i<3;i++){
             ya[i]=new Y();
         }
         
         xa=ya;
         for(int i=0;i<3;i++){
             System.out.println(xa[i].a);
         }
         ya=(Y[])xa;
         for(int i=0;i<3;i++){
             System.out.println(ya[i].a+" "+ya[i].b);
         }
         
//         byte a = 1000;   // 编译出错 Type mismatch: cannot convert from int to byte
//         float b = 1.5;   // 编译出错 Type mismatch: cannot convert from double to float
         byte c = 3;      // 编译正确
     }

 }