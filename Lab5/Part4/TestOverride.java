
public class TestOverride {

     public static void main(String[] args) {
         
         Child  child=new Child();
         Parent parent= new Parent();
         
         child.dosomething();//Question1
         ((Parent) child).dosomething();
         
         parent.dosomething();//Question2
//         ((Child)parent).dosomething();
         
         child.dosomethingSuper();//Question3
         
         
     }

 }