/* LockDListNode.java */

package list1;

/**
 *  A LockDListNode is a node in a DList (doubly-linked list).
 */

public class LockDListNode extends DListNode{
    
    /**
     *  lockState references whether the item is locked
     */
    
    protected boolean lockState;
    
    /**
     *  LockDListNode() constructor.
     *  @param i the item to store in the node.
     *  @param p the node previous to this node.
     *  @param n the node following this node.
     */
    LockDListNode(Object i, DListNode p, DListNode n) {
        super(i,p,n);//直接
        lockState=false;
    }
}