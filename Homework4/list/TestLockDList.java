/* TestLockDList.java */

package list;

public class TestLockDList {
    
    protected static void printlnTest(LockDList a){
        System.out.println(a);
        System.out.println(a.head.item);
        System.out.println(a.head.lockState);
        System.out.println(a.size);
    }
    
    public static void main(String[] argv) {
        
        LockDList myLockDList=new LockDList();
        printlnTest(myLockDList);
        System.out.println("------------0-----------");
        System.out.println(myLockDList.isEmpty());
        System.out.println(myLockDList.length());
        
        System.out.println("------------1-----------");
        
        myLockDList.insertFront(1);
        printlnTest(myLockDList);
        System.out.println("------------2-----------");
        myLockDList.insertBack(2);
        printlnTest(myLockDList);
        System.out.println("------------3-----------");
        System.out.println(myLockDList.front().item);
        System.out.println(myLockDList.next(myLockDList.front()).item);
        //        System.out.println(myDList.next(myDList.back()).item);
        System.out.println("------------4-----------");
        System.out.println(myLockDList.back().item);
        System.out.println(myLockDList.prev(myLockDList.back()).item);
        //        System.out.println(myDList.prev(myDList.front()).item);
            System.out.println("------------5-----------");
        myLockDList.insertAfter(3, myLockDList.back());
        printlnTest(myLockDList);        System.out.println("-------------6----------");
        myLockDList.insertBefore(0, myLockDList.front());
        printlnTest(myLockDList);        System.out.println("------------7-----------");
        myLockDList.remove(myLockDList.front());
        printlnTest(myLockDList);        System.out.println("------------8-----------");
        
        LockDListNode List1=(LockDListNode)myLockDList.front();
        LockDListNode List2=(LockDListNode)List1.next;
        LockDListNode List3=(LockDListNode)List2.next;
        
        myLockDList.lockNode(List1);
        myLockDList.lockNode(List2);
        myLockDList.lockNode(List3);
        myLockDList.remove(List1);
        myLockDList.remove(List2);
        myLockDList.remove(List3);
        
        System.out.println(List1.lockState);
        System.out.println(List2.lockState);
        System.out.println(List3.lockState);
        printlnTest(myLockDList);
        
        
        
        
    }
    
    
}