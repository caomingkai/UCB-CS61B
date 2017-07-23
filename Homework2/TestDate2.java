
import java.io.*;


public class TestDate2{
    
    public static void main( String[] arg) throws Exception{
        System.out.println("into main.");
        Date d1=new Date(1,1,1);
        Date d2=new Date("2/4/2");
        
        Date d3=new Date(2,29,2000);
        Date d4=new Date("2/29/1904");
        System.out.println("---------");
        Date d5=new Date(12,31,1975);
        Date d6=new Date("1/1/1976");
        Date d7=new Date(1,2,1976);
        Date d8=new Date(2,27,1977);
        Date d9=new Date(8,31,2110);

        
        System.out.println("Testing constructors.");//test
        System.out.println("Date should be 1/1/1: "+d1.toString());
        System.out.println("Date should be 2/4/2: "+d2.toString());
        System.out.println("Date should be 2/29/2000: "+d3.toString());
        System.out.println("Date should be 2/29/1904: "+d4.toString());
        System.out.println("Date should be 12/31/1975: "+d5.toString());
        System.out.println("Date should be 1/1/1976: "+d6.toString());
        System.out.println("Date should be 1/2/1976: "+d7.toString());
        
        System.out.println(" ");//test

        System.out.println("Testing Before and After.");
        System.out.println( d6.toString()+" after "+d5.toString()+" should be true: " +d6.isAfter(d5));//test
        System.out.println( d7.toString()+" after "+d6.toString()+" should be true: " +d7.isAfter(d6));//test
        System.out.println( d5.toString()+" after "+d5.toString()+" should be false: " +d5.isAfter(d5));//test
        System.out.println( d5.toString()+" after "+d6.toString()+" should be false: " +d5.isAfter(d6));//test
        System.out.println( d6.toString()+" after "+d7.toString()+" should be false: " +d6.isAfter(d7));//test
        System.out.println( d5.toString()+" before "+d6.toString()+" should be true: " +d5.isBefore(d6));//test
        System.out.println( d6.toString()+" before "+d7.toString()+" should be ture: " +d6.isBefore(d7));//test
        System.out.println( d5.toString()+" before "+d5.toString()+" should be false: " +d5.isBefore(d5));//test
        System.out.println( d6.toString()+" before "+d5.toString()+" should be false: " +d6.isBefore(d5));//test
        System.out.println( d7.toString()+" before "+d6.toString()+" should be false: " +d7.isBefore(d6));//test
        
        System.out.println(" ");//test
        
        System.out.println("Testing difference.");
        System.out.println( d5.toString()+" - "+d5.toString()+" should be 0: " +d5.difference(d5));//test
        System.out.println( d6.toString()+" - "+d5.toString()+" should be 1: " +d6.difference(d5));//test
        System.out.println( d7.toString()+" - "+d5.toString()+" should be 2: " +d7.difference(d5));//test
        System.out.println( d7.toString()+" - "+d8.toString()+" should be -422: " +d7.difference(d8));//test
        System.out.println( d9.toString()+" - "+d8.toString()+" should be 48762: " +d9.difference(d8));//test
        

        
    }
}