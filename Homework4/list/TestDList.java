/* TestDList.java */

package list;

/**
 *  A DListNode is a node in a DList (doubly-linked list).
 */

public class TestDList {
    
    public static void main(String[] argv) {
       
        DList myDList=new DList();
        System.out.println(myDList.head.item);
        System.out.println(myDList.size);
        
        System.out.println(myDList.isEmpty());
        System.out.println(myDList.length());
        
        myDList.insertFront(1);
        System.out.println(myDList);
        System.out.println(myDList.size);
        System.out.println("------------------------");
        myDList.insertBack(2);
        System.out.println(myDList);
        System.out.println(myDList.size);
        System.out.println("------------------------");
        System.out.println(myDList.front().item);
        System.out.println(myDList.next(myDList.front()).item);
//        System.out.println(myDList.next(myDList.back()).item);
        System.out.println("------------------------");
        System.out.println(myDList.back().item);
        System.out.println(myDList.prev(myDList.back()).item);
//        System.out.println(myDList.prev(myDList.front()).item);
        System.out.println("------------------------");
        myDList.insertAfter(3, myDList.back());
        System.out.println(myDList);
        System.out.println(myDList.size);
        System.out.println("------------------------");
        myDList.insertBefore(0, myDList.front());
        System.out.println(myDList);
        System.out.println(myDList.size);
        System.out.println("------------------------");
        myDList.remove(myDList.front());
        System.out.println(myDList);
        System.out.println(myDList.size);
        System.out.println("------------------------");
        
        
        
        
    }
    
    
}