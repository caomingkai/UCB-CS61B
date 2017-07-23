interface pet {
    abstract public void test();
}



public class dog extends animal implements pet{
   
    public void test(){
        System.out.println("This is dog");
        super.test();
    }
   
    public static void main(String[] args){
        dog mydog=new dog();
        mydog.test();
//        super.test();
    }
    
        
    
}