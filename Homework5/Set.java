/* Set.java */

import list.*;
import java.lang.Comparable;
/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
    /* Fill in the data fields here. */
    protected List elementList;
    /**
     * Set ADT invariants:
     *  1)  The Set's elements must be precisely the elements of the List.
     *  2)  The List must always contain Comparable elements, and those elements
     *      must always be sorted in ascending order.
     *  3)  No two elements in the List may be equals().
     **/
    
    /**
     *  Constructs an empty Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public List newElementList(){//编写属于Set自己的newElementList（）方法目的：可以使用除了Dlist之外的其他List，如SList。
        return new DList();
    }
    
    public Set() {
        // Your solution here.
        elementList=newElementList();
    }
    
    /**
     *  cardinality() returns the number of elements in this Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public int cardinality() {
        // Replace the following line with your solution.
        return elementList.length();
    }
    
    /**
     *  insert() inserts a Comparable element into this Set.
     *
     *  Sets are maintained in sorted order.  The ordering is specified by the
     *  compareTo() method of the java.lang.Comparable interface.
     *
     *  Performance:  runs in O(this.cardinality()) time.
     **/
    public void insert(Comparable c){//c可以是Integer、Boolean等对象，这些对象都实现了Comparable接口下的compareTo（）方法。可以用接口引用对象。
        // Your solution here.
        
        try{
            ListNode curNode=elementList.front();//1、用父类引用DListNode；2、返回的是对象
            Object item=curNode.item();
            while(c.compareTo(item)>0){//Comparable类型与Object类型比较，转换类型？？？
                curNode=curNode.next();//结果比较相等时候，插在所有相等值的最后
                item=curNode.item();
            }
            if(c.compareTo(item)<0){//相等的情况不考虑，不做任何操作
                curNode.insertBefore(c);
            }
        }
        catch(InvalidNodeException e){
            this.elementList.insertBack(c);
        }
    }
    
    /**
     *  union() modifies this Set so that it contains all the elements it
     *  started with, plus all the elements of s.  The Set s is NOT modified.
     *  Make sure that duplicate elements are not created.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Your implementation should NOT copy elements of s or "this", though it
     *  will copy _references_ to the elements of s.  Your implementation will
     *  create new _nodes_ for the elements of s that are added to "this", but
     *  you should reuse the nodes that are already part of "this".
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
     **/
    public void union(Set s) {
        // Your solution here.
        ListNode curNode1=this.elementList.front();
        ListNode curNode2=s.elementList.front();
        try{
            int i=s.elementList.length();
            int j=this.elementList.length();
            if(i==0){//若s是空集，什么也不做，保持Set为原来的样子。
            }else if(i>0&&j==0){//若this是空集，则将this填充为Set s的样子。
                int k=i;
                Comparable item2=(Comparable)curNode2.item();
                while(k>1){//k==1最后一个while循环体后单独处理，因为涉及到sentinel.item()!
                    this.elementList.insertBack(item2);
                    curNode2=curNode2.next();
                    item2=(Comparable)curNode2.item();
                    k--;
                }
                this.elementList.insertBack(item2);//避免sentinel.item()!
            }else{//i>=1&&j>=1:两个集合都是非空集合的情况
                Comparable item1=(Comparable)curNode1.item();
                Comparable item2=(Comparable)curNode2.item();
                for(;i>0;i--){
                    while(item2.compareTo(item1)>0&&j>1){//j>1是为了获得最后一个node，单独处理。因为如果不满足while条件，最后一个node不能调用insertBefore（）。
                        curNode1=curNode1.next();//结果比较相等时候，插在所有相等值的最后
                        item1=(Comparable)curNode1.item();
                        j--;
                    }
                    if(item2.compareTo(item1)<0&&j>1){//表明进入while循环体
                        curNode1.insertBefore(item2);
                    }else if(item2.compareTo(item1)<0&&j<=1){
                        this.elementList.insertFront(item2);
                    }else if(item2.compareTo(item1)>0&&j<=1){
                        this.elementList.insertBack(item2);
                    }else//item2.compareTo(item1)==0&&j<=1&&j>1
                    {}//什么也不做。
                    item2=(Comparable)curNode2.next().item();
                }
            }
        }
        catch(InvalidNodeException e1){
            System.out.println("union()中调用front()next()item()方法的是head(sentinel)");
        }
        
    }
    
    /**
     *  intersect() modifies this Set so that it contains the intersection of
     *  its own elements and the elements of s.  The Set s is NOT modified.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Do not construct any new ListNodes during the execution of intersect.
     *  Reuse the nodes of "this" that will be in the intersection.
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT CONSTRUCT ANY NEW NODES.
     *  DO NOT ATTEMPT TO COPY ELEMENTS.
     **/
    public void intersect(Set s) {
        // Your solution here.
        ListNode curNode1=this.elementList.front();
        ListNode curNode2=s.elementList.front();
        try{
            int i=s.elementList.length();
            int j=this.elementList.length();
            if(j==0){//若this是空集，什么也不做，保持Set为原来的样子：空集。
            }else if(i==0&&j>0){//若Set s 是空集，则将this删除为空集。
                int k=j;
                while(k>1){
                    curNode1=curNode1.next();//取后一个节点
                    curNode1.prev().remove();
                    k--;
                }
                curNode1.remove();//k=1即最后一个节点单独处理，避免sentinel
            }else{//i>=1&&j>=1:两个集合都是非空集合的情况
                Comparable item1=(Comparable)curNode1.item();
                Comparable item2=(Comparable)curNode2.item();
                for(;i>0;i--){
                    while(item2.compareTo(item1)>0&&j>1){//
                        curNode1=curNode1.next();//取后一个节点
                        curNode1.prev().remove();//删除比item2小的节点
                        item1=(Comparable)curNode1.item();
                        j--;
                    }
                    if(item2.compareTo(item1)==0&&j==1){//
                        elementList.insertBack(item2);
                        curNode1.remove();//与item2相同的点删除（前提是已经把相同点在前边插入）
                        j--;
                    }
                    if(item2.compareTo(item1)>0&&j==1){
                        curNode1.remove();
                        j--;
                    }
                    if(item2.compareTo(item1)==0&&j>1){
                        elementList.insertBack(item2);
                        curNode1=curNode1.next();//取后一个节点
                        curNode1.prev().remove();//比item2大的节点也删除
                        item1=(Comparable)curNode1.item();
                        j--;
                    }
                    if(item2.compareTo(item1)<0 && j>1){ //只要比item1小的，留给下一个item2与之比较
                    }
                    if(item2.compareTo(item1)<0&&j==1){//只要比item1小的，留给下一个item2与之比较
                    }

                    if(i>1){
						curNode2=curNode2.next();
                        item2=(Comparable)curNode2.item();
                    }else{
                        i=0;
                    }
                }
            }
        }
        catch(InvalidNodeException e){
            System.out.println("intersect()中调用front()next()item()方法的是head(sentinel)");
        }
    }
    
    /**
     *  toString() returns a String representation of this Set.  The String must
     *  have the following format:
     *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
     *            between them.
     *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
     *            "{" or after "}"; two spaces before and after each element.
     *            Elements are printed with their own toString method, whatever
     *            that may be.  The elements must appear in sorted order, from
     *            lowest to highest according to the compareTo() method.
     *
     *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
     *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
     *            DEVIATIONS WILL LOSE POINTS.
     **/
    public String toString() {
        // Replace the following line with your solution.
        String result="{  ";
        try{
            ListNode current=elementList.front();
            int i=elementList.length();
            while(i>0){
                result=result+current.item()+"  ";
                current = current.next();
                i--;
            }
        }
        catch(InvalidNodeException e){
            System.out.println("toString()中的Exception");
        }
        return result+"}";
    }
    
    public static void main(String[] argv) {
        Set s = new Set();
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(3));
        System.out.println("Set s = " + s);
        
        Set s2 = new Set();
        s2.insert(new Integer(4));
        s2.insert(new Integer(5));
        s2.insert(new Integer(5));
        System.out.println("Set s2 = " + s2);
        
        Set s3 = new Set();
        s3.insert(new Integer(5));
        s3.insert(new Integer(3));
        s3.insert(new Integer(8));
        System.out.println("Set s3 = " + s3);
        
        s.union(s2);
        System.out.println("After s.union(s2), s = " + s);
        
        s.intersect(s3);
        System.out.println("After s.intersect(s3), s = " + s);
        
        System.out.println("s.cardinality() = " + s.cardinality());
        // You may want to add more (ungraded) test code here.
        Set s4 = new Set();
        System.out.println("Empty Set s4 = " + s4);
        
        System.out.println("s4.cardinality() = " + s4.cardinality());
        
        s4.union(s4);
        System.out.println("After s4.union(s4), s4 = " + s4);
        
        s4.intersect(s4);
        System.out.println("After s4.intersect(s4), s4 = " + s4);
        
        Set s5 = new Set();
        System.out.println("Empty Set s5 = " + s5);
        System.out.println("Set s3 = " + s3);
        s5.union(s3);
        System.out.println("After s5.union(s3), s5 = " + s5);
		s4.intersect(s3);
        System.out.println("After s4.intersect(s3), s4 = " + s4);
        

        System.out.println("------------------- " );
        Set s6 = new Set();
        s6.insert(new Integer(79));
        s6.insert(new Integer(8));
        s6.insert(new Integer(9));
        s6.insert(new Integer(19));
        s6.insert(new Integer(29));
        s6.insert(new Integer(6));
        s6.insert(new Integer(119));
        
        Set s7 = new Set();
        s7.insert(new Integer(79));
        s7.insert(new Integer(8));
        s7.insert(new Integer(10));
        s7.insert(new Integer(119));
        s7.insert(new Integer(29));
        s7.insert(new Integer(6));
        s7.insert(new Integer(7));
        System.out.println("Set s6 = " + s6);
        System.out.println("Set s7 = " + s7);
        s6.intersect(s7);
        System.out.println("After s6.intersect(s7), s6 = " + s6);
        
        
        System.out.println("------------------- " );
        s3.union(s4);
        System.out.println("After s3.union(s4), s3 = " + s3);
        
        s3.intersect(s4);
        System.out.println("After s3.intersect(s4), s3 = " + s3);
    }
}