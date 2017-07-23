/* Entry.java */

package dict;

/**
 *  A class for dictionary entries.
 *
 *  DO NOT CHANGE THIS FILE.  It is part of the interface of the
 *  Dictionary ADT.
 **/

public class LinkListNode {


  protected Entry item;
  protected LinkListNode prev;
  protected LinkListNode next;

  protected LinkListNode(){
	  item=null;
	  prev=null;
	  next=null;
  }

  protected LinkListNode(Object key, Object value){
	  item=new Entry();
	  item.key=key;
	  item.value=value;
	  prev=null;
	  next=null;
  }

  protected Entry remove(){
	  if(this.next==null){
		  this.prev.next=null;
	  }else{
		  this.prev.next=this.next;
		  this.next.prev=this.prev;
	  }
	  this.next=null;
	  this.prev=null;
	  return this.item;
  }
}
