//this method is all about Date stuff.

import java.net.*;
import java.io.*;

public class Date{

int day;
int month;
int year;


/** Constructs a Date with the given month, day and year.   If the Date is
 *  not valid, the entire program will halt with an error message.
 *  @param month is a month, numbered in the range 1...12.
 *  @param day is between 1 and the number of days in the given month.
 *  @param year is the year in question, with no digits omitted.
 */
public Date(int month, int day, int year) {
//    System.out.println("---Test:into Data constructor---");
    if(isValidDate(month,day,year)){
        this.month=month;
        this.day=day;
        this.year=year;
    }
    else{
        System.out.println("Not a valid date");
        System.exit(0);
    }
}
/** Constructs a Date object corresponding to the given string.
*  @param s should be a string of the form "month/day/year" where month must
*  be one or two digits, day must be one or two digits, and year must be
*  between 1 and 4 digits.  If s does not match these requirements or is not
*  a valid Date, the program halts with an error message of your choice.
*/
public Date(String s) {

    String[] SplitCharData;
    int[] IntData=new int[3];
    
    SplitCharData=s.split("/");//String.split();对象为string，拆分后仍为substring
    for(int i=0;i<=2;i++){
        IntData[i]=Integer.parseInt(SplitCharData[i]);//Integer.parseInt(String);将string转化为int
    }
    if(isValidDate(IntData[0],IntData[1],IntData[2])){
        this.month=IntData[0];
        this.day=IntData[1];
        this.year=IntData[2];
    }
    else{
        System.out.println("Not a valid date");
        System.exit(0);
    }

}
    

/** Checks whether the given year is a leap year.
 *  @return true if and only if the input year is a leap year.
 */
public static boolean isLeapYear(int year) {
    if(year%4==0)
        if(year%100==0)
            if(year%400==0)return true;//能被4/100/400除，true
            else return false;//能被100除，不能被400除，false
        else return true;//能被4除，不能被100除，true
    else return false;//不能被4除，false

}

/** Returns the number of days in a given month.
 *  @param month is a month, numbered in the range 1...12.
 *  @param year is the year in question, with no digits omitted.
 *  @return the number of days in the given month.
 */
public static int daysInMonth(int month, int year) {
boolean year_flag=isLeapYear(year);
switch(month){
    case 1: case 3: case 5: case 7: case 8: case 10: case 12: {return 31; }
    case 4: case 6: case 9: case 11: {return 30;}
    case 2: if (year_flag){ return 29;}
            else {return 28;}
    default: return 0;
}//switch
}

/** Checks whether the given Date is valid.
 *  @return true if and only if month/day/year constitute a valid Date.
 *
 *  Years prior to A.D. 1 are NOT valid.
 */
public static boolean isValidDate(int month, int day, int year) {
//    System.out.println("---Test:into isValidDate---");
if(year>=1&&year<=9999)
    if(month>=1&&month<=12)
        if(day>=1&&day<=daysInMonth(month, year))
             return true;
        else return false;
    else return false;
else return false;

}// isValidDate


/** Determines whether this Date is before the Date d.
 *  @return true if and only if this Date is before d.
 */
public boolean isBefore(Date d) {
    if(this.year<d.year)return true;
    if(this.year==d.year&&this.month<d.month)return true;
    if(this.year==d.year&&this.month==d.month&&this.day<d.day)return true;
    return false;
}

/** Determines whether this Date is after the Date d.
 *  @return true if and only if this Date is after d.
 */
public boolean isAfter(Date d) {
    if(isBefore(d)) return false;
    if(this.year==d.year&&this.month==d.month&&this.day==d.day)return false;
    return true;
}

/** Returns the number of this Date in the year.
 *  @return a number n in the range 1...366, inclusive, such that this Date
 *  is the nth day of its year.  (366 is only used for December 31 in a leap
 *  year.)
 */
public int dayInYear() {
    int sum=0;
    
    for(int i=1;i<=12;i++){
        sum=sum+daysInMonth(i,year);
    }
    return sum;
}
//给定Data d{month/day/year},返回本年度已经过了的天数；
public int pastDays(){
    int sum=0;
    
    for(int i=1;i<month;i++){
        sum=sum+daysInMonth(i,year);//累加当前月份之前从1月起至今的所有月份，for循环适用于month＝1；
    }
    sum=sum+day;
    return sum;
}
    
/** Determines the difference in days between d and this Date.  For example,
 *  if this Date is 12/15/1997 and d is 12/14/1997, the difference is 1.
 *  If this Date occurs before d, the result is negative.
 *  @return the difference in days between d and this Date.
 */
public long difference(Date d) {
    long diff=0;
    long diffTmp=0;
    int deltYear=this.year-d.year;
    Date smaller=new Date(1,1,1);
    Date bigger=new Date(1,1,1);
    Date temp=new Date(1,1,1);;
    
    if(deltYear>=0)  {bigger=this; smaller=d; }
    if(deltYear<0)  {bigger=d; smaller=this; }
//    System.out.println("--------Test:difference-----------");
    for(int i=0;i<Math.abs(deltYear);i++){//计算两个年份之间那几个年份的天数
        temp=new Date(1,1,smaller.year+i);//1/1/year的1/1是随便写的，为了构造出含有year+i的Data对象
        diffTmp=diffTmp+temp.dayInYear();//但是在for循环内使用new Date()会一直新建对象！！！！
    }
    diff=diffTmp+bigger.pastDays()-smaller.pastDays();//得到的结果一定为正值
    
    if(deltYear>=0) return diff;
    else return -diff;
    

}

    
public String toString() {
    return this.month+"/"+this.day+"/"+this.year;//月份后含天数month（days）
//    return this.month+"("+this.daysInMonth(this.month,this.year)+")"+"/"+this.day+"/"+this.year;//月份后含天数month（days）
}



}// class Date