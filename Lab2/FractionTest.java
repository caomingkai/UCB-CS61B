import java.io.*;


public class FractionTest {
    public static void main(String[] argv) {
        
        Fraction f1=new Fraction(1,2);
        Fraction f2=new Fraction(3,4);
        Fraction sumOfTwo=f1.add(f2);
    
    System.out.println("The sum of " + f1.toString() + " and " + f2.toString() + " is " + sumOfTwo.toString());
    }
    
    
}