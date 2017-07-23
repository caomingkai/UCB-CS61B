
import java.io.*;


public class TestDate{
    
public static void main( String[] arg) throws Exception{

    System.out.println("---Test:into main");//test
    System.out.println("Input the data in the this format: month/day/year,like:*/**/****");
    
    BufferedReader Keyboard;
    String InputData;

    Keyboard=new BufferedReader(new InputStreamReader(System.in));
    InputData=Keyboard.readLine();

/*----------------String.toCharArray();函数无区别对待内部值，对于数字“23”，直接拆成“2”、“3”作为单独的char分量
    char[] CharData;
    CharData=InputData.toCharArray();
    int i=0;
    while(i<CharData.length){//查看9/23/2009格式的输入转化为数组时候，‘23’还是‘2’，‘3’？
        System.out.println(CharData[i]);
        i++;
    }
*/

    Date myDate=new Date(InputData);//第一个constructor
//    Date myDate=new Date(2,28,2000);//第二个constructor
    
    if(myDate.isLeapYear(myDate.year))
        System.out.println(myDate.toString()+":"+"a LeapYear");// year
    else
        System.out.println(myDate.toString());
    
    Date myDate2=new Date(2,28,2000);
    if(myDate.isBefore(myDate2))
        System.out.println("Before 2/28/2000");
    if(myDate.isAfter(myDate2))
        System.out.println("After  2/28/2000");
    
    System.out.println(myDate.difference(myDate2));

}
}