/* HashTableChained.java */

package dict;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
   protected LinkList bucket[]; //实际上每个bucket即LinkedList的reference
   protected int capacity;//bucket数目
   protected int itemNum;
   private static int prime=109345121;
   private static int scale=97;
   private static int shift=73;  
  
  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
	  capacity=(int)((double)sizeEstimate*1.5);//loadFactor=0.6666
	  itemNum=0;
	  bucket=new LinkList[capacity];
	  for(int i=0;i<capacity;i++){
		  bucket[i]=new LinkList();
	  }
    // Your solution here.
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
	  capacity=101;//
	  itemNum=0;
	  bucket=new LinkList[capacity];
	  for(int i=0;i<capacity;i++){
		  bucket[i]=new LinkList();
	  }
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    return (int)((Math.abs(code * scale + shift) %prime)%capacity);
	//return ((Math.abs(code))%capacity);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return itemNum;
  }
  
  public int capacity() {
    // Replace the following line with your solution.
    return capacity;
  }

  public LinkList [] bucket() {
    // Replace the following line with your solution.
    return bucket;
  }
  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return itemNum==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
	int bucketIndex=compFunction(key.hashCode());
	Entry newItem=bucket[bucketIndex].insertFront(key, value);
	itemNum++;
    return newItem;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
	int bucketIndex=compFunction(key.hashCode());
	Entry newItem=bucket[bucketIndex].find(key);
    return newItem;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
	int bucketIndex=compFunction(key.hashCode());
	Entry newItem=bucket[bucketIndex].remove(key);
    return newItem;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
	itemNum=0;
	for(int i=0;i<capacity;i++){
		bucket[i].makeEmpty();
		}
  } 


  public static void main(String[] args) {
    int numBoards;
    numBoards = 13;

    HashTableChained table = new HashTableChained(numBoards);
	
	for(int i=0;i<12;i++){
		table.insert(i,i);
	}

	table.remove(1);
	table.remove(11);
/*-------------------------------测试区---------------------------------------------*/

    System.out.println("table.capacity：  "+table.capacity);
		
	for(int i=0;i<table.capacity;i++){
		System.out.println("Bucket["+i+"]中的元素是：  "+table.bucket[i].toString());
		}

/*-------------------------------测试区---------------------------------------------*/
    // To test your hash function, add a method to your HashTableChained class
    // that counts the number of collisions--or better yet, also prints
    // a histograph of the number of entries in each bucket.  Call this method
    // from here.
  }

}

