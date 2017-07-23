/* Entry.java */

package dict;

/**
 *  A class for dictionary entries.
 *
 *  DO NOT CHANGE THIS FILE.  It is part of the interface of the
 *  Dictionary ADT.
 **/

public class LinkList {

  private LinkListNode head;
  private int length;

  protected LinkListNode newNode(Object key, Object value){
	  return new LinkListNode(key, value);
  }

  protected LinkList() {
	  head=new LinkListNode();
	  length=0;
  }

  protected LinkListNode findNode(Object key) {
	  if(length==0){
		  System.out.println("The key: "+key+" is not in the Hashtable.");
		  return null;}
	  else{
		  LinkListNode curNode=head.next;
		  while(curNode.item.key!=key){
			  curNode=curNode.next;
			  if(curNode==null){
				  System.out.println("The key: "+key+" is not in the Hashtable.");
				  return null;}
		  }
		  return curNode;
	  }
  }

  public int length(){
	  return length;
  }

  protected Entry find(Object key) {
	  LinkListNode curNode=findNode(key);
	  return curNode.item;
  }

  protected Entry insertFront(Object key, Object value) {
	  LinkListNode newNode=newNode(key, value);
	  if (length==0){
		  head.next=newNode;
		  newNode.prev=head;
	  }else
		  {
		  newNode.next=head.next;
		  head.next.prev=newNode;
		  head.next=newNode;
		  newNode.prev=head;
		  }
	  length++;
	  return newNode.item;
  }

  protected Entry remove(Object key) {
	  LinkListNode curNode=findNode(key);
	  if(curNode!=null){
		  length--;
		  return curNode.remove();}
	  else{
		  //System.out.println("The key is not in the Hashtable.");
		  return null;
	  }
  }

  public void makeEmpty() {
    // Your solution here.
	if(length!=0){
		length=0;
		head.next.prev=null;
		head.next=null;
	}
  } 

  public String toString() {
        // Replace the following line with your solution.
    String result="";
	if(length!=0){
		LinkListNode current=head.next;
		while(current!=null){
//			result=result+"["+current.item.value+"]  ";
			result=result+"["+current.item.key.hashCode()+" "+current.item.value+"]  ";
			current=current.next;
		}
	}
	return result;
  }

}

