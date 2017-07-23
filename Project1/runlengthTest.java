import java.util.*;

public class runlengthTest {
    public static void main(String[] args){
        
        

        System.out.println("----------------1------------------");
        RunLengthEncoding test1=new RunLengthEncoding(3,3,4);
        oneRun test1Head=test1.getHead();
        oneRun cur1= test1Head;
        while(cur1!=null){
            System.out.print(cur1.type+"-"+cur1.length+"  ");
            cur1=cur1.next;
        }
        System.out.println();
        System.out.println();
        
        System.out.println("-----------------2-----------------");
        int[] runTypes=new int[]{1,2,1,0};
        int[] runLengths=new int[]{1,3,1,4};
        RunLengthEncoding test2=new RunLengthEncoding(3,3,4,runTypes,runLengths);
        oneRun test2Head=test2.getHead();
        oneRun cur2= test2Head;
        while(cur2!=null){
            System.out.print(cur2.type+"-"+cur2.length+"  ");
            cur2=cur2.next;
        }
        System.out.println();
        System.out.println();
        
        System.out.println("-----------------3-----------------");
        Ocean myOcean=new Ocean(3,3,4);
        myOcean.addFish(1,0);
        myOcean.addFish(2,0);
        myOcean.addFish(0,1);
        myOcean.addShark(0,0);
        myOcean.addShark(1,1);
        RunLengthEncoding test3=new RunLengthEncoding(myOcean);

        oneRun test3Head=test3.getHead();
        oneRun cur3= test3Head;
        while(cur3!=null){
            System.out.print(cur3.type+"-"+cur3.length+"  ");
            cur3=cur3.next;
            
            TypeAndSize a=test3.nextRun();
            System.out.println("     "+a.type+"_"+a.size+"  ");

        }
        
        Ocean myOcean2=test3.toOcean();
        int[][]curCell=myOcean2.cell();
        System.out.println(myOcean2.height());
        System.out.println(myOcean2.width());
        
        for(int j=0;j<myOcean2.height();j++){
            for(int i=0;i<myOcean2.width();i++){
                System.out.print(curCell[i][j]+"-");
            }
            System.out.println();
        }
        
        SimText.paint(myOcean2);
        
        
        test3.addFish(1,2);
        Ocean myOcean3=test3.toOcean();
        SimText.paint(myOcean3);
        
        test3.addShark(2,2);
        Ocean myOcean4=test3.toOcean();
        SimText.paint(myOcean4);
        
        test3.addFish(0,0);
        Ocean myOcean5=test3.toOcean();
        SimText.paint(myOcean5);
        
        test3.addShark(2,0);
        Ocean myOcean6=test3.toOcean();
        SimText.paint(myOcean6);
        
        
        System.out.println();
        System.out.println();
      

        

        
    }
}