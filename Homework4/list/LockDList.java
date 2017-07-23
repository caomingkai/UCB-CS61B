/* LockDList.java */

package list;


public class LockDList extends DList{
    
    protected LockDListNode head;
//    protected DListNode head;
//    protected int size;
    
    /* DList invariants:
     *  1)  head != null.
     *  2)  For any DListNode x in a DList, x.next != null.
     *  3)  For any DListNode x in a DList, x.prev != null.
     *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
     *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
     *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
     *      that can be accessed from the sentinel (head) by a sequence of
     *      "next" references.
     */
    
    /**
     *  newNode() calls the LockDListNode constructor. 
     *  区别在于：调用了LockDListNode() 构造器，返回LockDListNode,此处用到了java的“多态特性”：即便需要extend父类，只需要在子类中加入想增加的功能即可，对于文件之外别的地方使用父类类型作为变量，不必做任何更改！因为父类对子类具有“多态特性”！
     */
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {//返回值不用LockDListNode,是因为：LockDList子类在inherit其父类的众多函数中有众多语句：DListNode curNode=newNode(item,null,null);，将DListNode 改为LockDListNode返回值，修改做很多改动；保持不动的话恰好利用“多态特性”，只不过DListNode没有lockState的遥控按钮，需要使用lockState时候，需要将DListNode强制转换为LockDListNode。
        return new LockDListNode(item, prev, next);
    }
    
    /**
     *  LockDList() constructor for an empty DList.
     */
    public LockDList() {
        //  Your solution here.
        super();
        head=(LockDListNode)newNode("00000",null,null);//此处的newNode()不同于DList类下的，这个override了父类的newNode(),引入lockState变量--->使在LockDList作用范围内的所有DListNode实质为LockDListNode(多态特性)，利用强制转换转换为LockDListNode
    }
    
    /**
     *  isEmpty() returns true if this DList is empty, false
     *  length() returns the length of this DList.
     *  insertFront() inserts an item at the front of this DList
     *  insertBack() inserts an item at the back of this DList.
     *  front() returns the node at the front of this DList.  If
     *  back() returns the node at the back of this DList.  If
     *  next() returns the node following "node" in this DList.
     *  prev() returns the node prior to "node" in this DList.      
     *  insertAfter() inserts an item in this DList immediately
     *  insertBefore() inserts an item in this DList immediately            
     **/
    
     /**
     *  remove() removes "node" from this DList.  If "node" is null or locked, do nothing.
     *  Performance:  runs in O(1) time.
     */
    public void remove(DListNode node) {
        // Your solution here.
        LockDListNode curNode=(LockDListNode)node;
        if(curNode!=null&&curNode.lockState==false){
            size--;
            curNode.prev.next=curNode.next;
            curNode.next.prev=curNode.prev;
        }
        
    }
    
    /**
     *  lockNode() set the "node" to the state:locked
     *  Performance:  runs in O(1) time.
     */
    public void lockNode(DListNode node) {
        LockDListNode curNode=(LockDListNode)node;
        curNode.lockState=true;
    }
}

