public class Child extends Parent implements Interface{

    public int add(int name3){
        
        System.out.println(Interface.str);
        System.out.println(super.str);
        
        return name3;
    }
}