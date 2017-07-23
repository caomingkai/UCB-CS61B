// 第一种方法－－－－String.toCharArray
/*import java.io.*;

class Nuke2{
    public static void main( String[] arg) throws Exception{
      
        BufferedReader keyboard;
        String inputLine;
        char[] string2char;
        
        keyboard=new BufferedReader(new InputStreamReader( System.in));
        inputLine=keyboard.readLine();
        string2char=inputLine.toCharArray();
        
        for(int i=0;i<inputLine.length()-1;i++ ){//string2char[i]!='/0'
            if(i!=1){
                System.out.print(string2char[i]);
            }
        }
        System.out.println(string2char[inputLine.length()-1]);

    }
}
*/

// 第二种方法－------------------------－－－用到了StringBuilder对象，
// string对象不能改变，然而stringbuilder对象可以随意操作！！！
import java.io.*;

class Nuke2{
    public static void main( String[] arg) throws Exception{
        
        BufferedReader keyboard;
        String inputLine;
        StringBuilder middle;
        
        keyboard=new BufferedReader(new InputStreamReader( System.in));
        inputLine=keyboard.readLine();
        middle=new StringBuilder(inputLine);
        //middle=keyboard.readLine();//keyboard.readLine()返回型为String，因此不能用返回型为stringbuilder的middle
        middle=middle.deleteCharAt(1);
        inputLine=middle.toString();
        
        System.out.println(inputLine);
       
        
    }
}