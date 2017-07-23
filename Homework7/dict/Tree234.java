/* Tree234.java */

package dict;

/**
 *  A Tree234 implements an ordered integer dictionary ADT using a 2-3-4 tree.
 *  Only int keys are stored; no object is associated with each key.  Duplicate
 *  keys are not stored in the tree.
 *
 *  @author Jonathan Shewchuk
 **/
public class Tree234 extends IntDictionary {
    
    /**
     *  (inherited)  size is the number of keys in the dictionary.
     *  root is the root of the 2-3-4 tree.
     *
     *  You may add fields if you wish, but don't change anything that
     *  would prevent toString() or find() from working correctly.
     **/
    Tree234Node root;
    
    public Tree234() {
        root = null;
        size = 0;
    }
    
    /**
     *  toString() prints this Tree234 as a String.  Each node is printed
     *  in the form such as (for a 3-key node)
     *
     *      (child1)key1(child2)key2(child3)key3(child4)
     *
     *  where each child is a recursive call to toString, and null children
     *  are printed as a space with no parentheses.  Here's an example.
     *      ((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))
     *
     *  DO NOT CHANGE THIS METHOD.
     *
     *  @return a String representation of the 2-3-4 tree.
     **/
    public String toString() {
        if (root == null) {
            return "";
        } else {
            return root.toString();
        }
    }
    
    /**
     *  printTree() prints this Tree234 as a tree, albeit sideways.
     *
     *  You're welcome to change this method if you like.  It won't be tested.
     **/
    public void printTree() {
        if (root != null) {
            root.printSubtree(0);
        }
    }
    
    /**
     *  find() prints true if "key" is in this 2-3-4 tree; false otherwise.
     *
     *  @param key is the key sought.
     *  @return true if "key" is in the tree; false otherwise.
     **/
    public boolean find(int key) {
        Tree234Node node = root;
        while (node != null) {
            if (key < node.key1) {
                node = node.child1;
            } else if (key == node.key1) {
                return true;
            } else if ((node.keys == 1) || (key < node.key2)) {
                node = node.child2;
            } else if (key == node.key2) {
                return true;
            } else if ((node.keys == 2) || (key < node.key3)) {
                node = node.child3;
            } else if (key == node.key3) {
                return true;
            } else {
                node = node.child4;
            }
        }
        return false;
    }
    
    
    public void adjustment(Tree234Node node){
        Tree234Node nodeDown1=new Tree234Node(null,node.key1);
        Tree234Node nodeDown2=new Tree234Node(null,node.key3);
        nodeDown1.child1=node.child1;
        nodeDown1.child2=node.child2;
        nodeDown2.child1=node.child3;
        nodeDown2.child2=node.child4;
        if(node.child1!=null){//非叶子节点
            node.child1.parent=nodeDown1;}
        if(node.child1!=null){//非叶子节点
            node.child2.parent=nodeDown1;}
        if(node.child1!=null){//非叶子节点
            node.child3.parent=nodeDown2;}
        if(node.child1!=null){//非叶子节点
            node.child4.parent=nodeDown2;}
        
        if(node.parent==null){//rootNode
            Tree234Node newRoot=new Tree234Node(null,node.key2);
            root=newRoot;
            root.child1=nodeDown1;
            root.child2=nodeDown2;
            nodeDown1.parent=root;
            nodeDown2.parent=root;
            
        }else{//not a rootNode
            if(node.parent.keys==1){
                node.parent.keys=2;
                if(node.key2<node.parent.key1){
                    node.parent.key2=node.parent.key1;
                    node.parent.key1=node.key2;
                    node.parent.child3=node.parent.child2;
                    node.parent.child1=nodeDown1;
                    node.parent.child2=nodeDown2;
                    nodeDown1.parent=node.parent;
                    nodeDown2.parent=node.parent;
                }else{//node.key2>node.parent.key1,不存在相等情况
                    node.parent.key2=node.key2;
                    node.parent.child2=nodeDown1;
                    node.parent.child3=nodeDown2;
                    nodeDown1.parent=node.parent;
                    nodeDown2.parent=node.parent;
                }
            }else{//node.parent.keys==2,不可能存在==3的情况，因为从上至下到这里时，都adjust了
                node.parent.keys=3;
                if(node.key2<node.parent.key1){
                    node.parent.key3=node.parent.key2;
                    node.parent.key2=node.parent.key1;
                    node.parent.key1=node.key2;
                    node.parent.child4=node.parent.child3;
                    node.parent.child3=node.parent.child2;
                    node.parent.child1=nodeDown1;
                    node.parent.child2=nodeDown2;
                    nodeDown1.parent=node.parent;
                    nodeDown2.parent=node.parent;
                }else if(node.key2>node.parent.key2){
                    node.parent.key3=node.key2;
                    node.parent.child3=nodeDown1;
                    node.parent.child4=nodeDown2;
                    nodeDown1.parent=node.parent;
                    nodeDown2.parent=node.parent;
                }else{//node.key2>node.parent.key1 && node.key2<node.parent.key2
                    node.parent.key3=node.parent.key2;
                    node.parent.key2=node.key2;
                    node.parent.child4=node.parent.child3;
                    node.parent.child2=nodeDown1;
                    node.parent.child3=nodeDown2;
                    nodeDown1.parent=node.parent;
                    nodeDown2.parent=node.parent;
                }
            }
            node.parent=null;
        }
    }
    
    
    public void child1and2(int key,Tree234Node curNode,Tree234Node curNodeParent) {
        if(key<curNode.key1){
            curNodeParent.child1.keys=2;
            curNodeParent.child1.key2=curNodeParent.child1.key1;
            curNodeParent.child1.key1=key;
        }else if(key>curNode.key1 && key<curNode.key2){
            curNodeParent.child1.keys=2;
            curNodeParent.child1.key2=key;
        }else if(key>curNode.key2 && key<curNode.key3){
            curNodeParent.child2.keys=2;
            curNodeParent.child2.key2=curNodeParent.child2.key1;
            curNodeParent.child2.key1=key;
        }else if(key>curNode.key3){
            curNodeParent.child2.keys=2;
            curNodeParent.child2.key2=key;
        }else{}//==
    }
    
    public void child2and3(int key,Tree234Node curNode,Tree234Node curNodeParent) {
        if(key<curNode.key1){
            curNodeParent.child2.keys=2;
            curNodeParent.child2.key2=curNodeParent.child2.key1;
            curNodeParent.child2.key1=key;
        }else if(key>curNode.key1 && key<curNode.key2){
            curNodeParent.child2.keys=2;
            curNodeParent.child2.key2=key;
        }else if(key>curNode.key2 && key<curNode.key3){
            curNodeParent.child3.keys=2;
            curNodeParent.child3.key2=curNodeParent.child3.key1;
            curNodeParent.child3.key1=key;
        }else if(key>curNode.key3){
            curNodeParent.child3.keys=2;
            curNodeParent.child3.key2=key;
        }else{}//==
    }
    
    public void child3and4(int key,Tree234Node curNode,Tree234Node curNodeParent) {
        if(key<curNode.key1){
            curNodeParent.child3.keys=2;
            curNodeParent.child3.key2=curNodeParent.child3.key1;
            curNodeParent.child3.key1=key;
        }else if(key>curNode.key1 && key<curNode.key2){
            curNodeParent.child3.keys=2;
            curNodeParent.child3.key2=key;
        }else if(key>curNode.key2 && key<curNode.key3){
            curNodeParent.child4.keys=2;
            curNodeParent.child4.key2=curNodeParent.child4.key1;
            curNodeParent.child4.key1=key;
        }else if(key>curNode.key3){
            curNodeParent.child4.keys=2;
            curNodeParent.child4.key2=key;
        }else{}//==
    }
        /**
     *  insert() inserts the key "key" into this 2-3-4 tree.  If "key" is
     *  already present, a duplicate copy is NOT inserted.
     *
     *  @param key is the key sought.
     **/
    public void insert(int key) {
        // Fill in your solution here.
        if(root==null){//与while的情况互补，即：只有根节点
            Tree234Node curNode=new Tree234Node(null,key);
            root=curNode;
        }else{
            Tree234Node curNode=root;
            while(curNode.child1!=null||curNode.child2!=null||curNode.child3!=null||curNode.child4!=null){//找出叶子curNode（所有child==null）
                if(key < curNode.key1){
                    curNode=curNode.child1;
                }else if((key > curNode.key1 &&curNode.keys == 1) || (key < curNode.key2)){
                    curNode=curNode.child2;
                }else if((key > curNode.key2 &&curNode.keys == 2) || (key < curNode.key3)){
                    curNode=curNode.child3;
                }else if(key > curNode.key3){
                    curNode=curNode.child4;
                }else{//key == node.key1/key3/key3的情况
                    //do nothing!
                }
                if(curNode.parent.keys==3){//调整结构
                    adjustment(curNode.parent);
//－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
//                    String treeString = toString();
//                    System.out.println("while内部调整结果"+treeString);
//－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
                }
            }//while找到待插入的叶子节点curNode
            if(curNode.keys==1){//找到待插入的叶子节点后，进行插入操作
                curNode.keys=2;
                if(key<curNode.key1){
                    curNode.key2=curNode.key1;
                    curNode.key1=key;
                }else if(key>curNode.key1){//key>curNode.key1
                    curNode.key2=key;
                }else{}//==
            }else if(curNode.keys==2){//curNode.keys==3，“叶子节点”调整结构
                curNode.keys=3;
                if(key<curNode.key1){
                    curNode.key3=curNode.key2;
                    curNode.key2=curNode.key1;
                    curNode.key1=key;
                }else if(key>curNode.key2){//
                    curNode.key3=key;
                }else if(key>curNode.key1 && key<curNode.key2){//
                    curNode.key3=curNode.key2;
                    curNode.key2=key;
                }else{}//==
            }else{//curNode.keys==3
                Tree234Node curNodeParent=curNode.parent;//需要讨论parent的keys，可以让adjustment返回int类型的child位置
                adjustment(curNode);//调整之后curNodeParent.keys>=2
//－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
//                String treeString = toString();
//                System.out.println("叶子节点调整结果"+treeString);
//－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
                if(curNodeParent==null){//rootNode
                    child1and2(key,curNode,root);
                }else{
                    if(curNodeParent.keys==2){
                        if(curNode.key2==curNodeParent.key1){//----child1and2
                            child1and2(key,curNode,curNodeParent);
                        }else if(curNode.key2==curNodeParent.key2){//----child2and3
                            child2and3(key,curNode,curNodeParent);
                        }else{}
                    }else if(curNodeParent.keys==3){
                        if(curNode.key2==curNodeParent.key1){//----child1and2
                            child1and2(key,curNode,curNodeParent);
                        }else if(curNode.key2==curNodeParent.key2){//----child2and3
                            child2and3(key,curNode,curNodeParent);
                        }else if(curNode.key2==curNodeParent.key3){//----child3and4
                            child3and4(key,curNode,curNodeParent);
                        }else{}
                    }else{}
                }
            }
        }
    }
    
    
    /**
     *  testHelper() prints the String representation of this tree, then
     *  compares it with the expected String, and prints an error message if
     *  the two are not equal.
     *
     *  @param correctString is what the tree should look like.
     **/
    public void testHelper(String correctString) {
        String treeString = toString();
        System.out.println(treeString);
        if (!treeString.equals(correctString)) {
            System.out.println("ERROR:  Should be " + correctString);
        }
    }
    
    /**
     *  main() is a bunch of test code.  Feel free to add test code of your own;
     *  this code won't be tested or graded.
     **/
    public static void main(String[] args) {
        Tree234 t = new Tree234();
        
        System.out.println("\nInserting 84.");
        t.insert(84);
        t.testHelper("84");
        
        System.out.println("\nInserting 7.");
        t.insert(7);
        t.testHelper("7 84");
        
        System.out.println("\nInserting 22.");
        t.insert(22);
        t.testHelper("7 22 84");
        
        System.out.println("\nInserting 95.");
        t.insert(95);
        t.testHelper("(7)22(84 95)");
        
        System.out.println("\nInserting 50.");
        t.insert(50);
        t.testHelper("(7)22(50 84 95)");
        
        System.out.println("\nInserting 11.");
        t.insert(11);
        t.testHelper("(7 11)22(50 84 95)");
        
        System.out.println("\nInserting 37.");
        t.insert(37);
        t.testHelper("(7 11)22(37 50)84(95)");
        
        System.out.println("\nInserting 60.");
        t.insert(60);
        t.testHelper("(7 11)22(37 50 60)84(95)");
        
        System.out.println("\nInserting 1.");
        t.insert(1);
        t.testHelper("(1 7 11)22(37 50 60)84(95)");
        
        System.out.println("\nInserting 23.");
        t.insert(23);
        t.testHelper("(1 7 11)22(23 37)50(60)84(95)");
        
        System.out.println("\nInserting 16.");
        t.insert(16);
        t.testHelper("((1)7(11 16)22(23 37))50((60)84(95))");
        
        System.out.println("\nInserting 100.");
        t.insert(100);
        t.testHelper("((1)7(11 16)22(23 37))50((60)84(95 100))");
        
        System.out.println("\nInserting 28.");
        t.insert(28);
        t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(95 100))");
        
        System.out.println("\nInserting 86.");
        t.insert(86);
        t.testHelper("((1)7(11 16)22(23 28 37))50((60)84(86 95 100))");
        
        System.out.println("\nInserting 49.");
        t.insert(49);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((60)84(86 95 100))");
        
        System.out.println("\nInserting 81.");
        t.insert(81);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((60 81)84(86 95 100))");
        
        System.out.println("\nInserting 51.");
        t.insert(51);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86 95 100))");
        
        System.out.println("\nInserting 99.");
        t.insert(99);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51 60 81)84(86)95(99 100))");
        
        System.out.println("\nInserting 75.");
        t.insert(75);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(75 81)84(86)95" +
                     "(99 100))");
        
        System.out.println("\nInserting 66.");
        t.insert(66);
        t.testHelper("((1)7(11 16)22(23)28(37 49))50((51)60(66 75 81))84((86)95" +
                     "(99 100))");
        
        System.out.println("\nInserting 4.");
        t.insert(4);
        t.testHelper("((1 4)7(11 16))22((23)28(37 49))50((51)60(66 75 81))84" +
                     "((86)95(99 100))");
        
        System.out.println("\nInserting 80.");
        t.insert(80);
        t.testHelper("(((1 4)7(11 16))22((23)28(37 49)))50(((51)60(66)75" +
                     "(80 81))84((86)95(99 100)))");
        
        System.out.println("\nFinal tree:");
        t.printTree();
    }
    
}