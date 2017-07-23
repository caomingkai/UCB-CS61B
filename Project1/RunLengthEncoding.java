/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */


public class RunLengthEncoding {
    
    /**
     *  Define any variables associated with a RunLengthEncoding object here.
     *  These variables MUST be private.
     */
    
    private static oneRun flag;
    private oneRun head;
    private oneRun tail;
    
    private int cellNum;
    private int width;
    private int height;
    private int starveTime;
    
    

    public oneRun getHead(){
        return head;
    }
    public oneRun getFlag(){
        return flag;
    }
    public oneRun getTail(){
        return tail;
    }
    public int getCellNum(){
        return cellNum;
    }
    /**
     *  The following methods are required for Part II.
     */
    
    /**
     *  RunLengthEncoding() (with three parameters) is a constructor that creates
     *  a run-length encoding of an empty ocean having width i and height j,
     *  in which sharks starve after starveTime timesteps.
     *  @param i is the width of the ocean.
     *  @param j is the height of the ocean.
     *  @param starveTime is the number of timesteps sharks survive without food.
     */
    
    public RunLengthEncoding(int i, int j, int starveTime) {
        // Your solution here.
        cellNum=i*j;
        width=i;
        height=j;
        this.starveTime=starveTime;
        head=new oneRun(0,cellNum);
        tail=head;
        flag=head;
    }
    
    /**
     *  RunLengthEncoding() (with five parameters) is a constructor that creates
     *  a run-length encoding of an ocean having width i and height j, in which
     *  sharks starve after starveTime timesteps.  The runs of the run-length
     *  encoding are taken from two input arrays.  Run i has length runLengths[i]
     *  and species runTypes[i].
     *  @param i is the width of the ocean.
     *  @param j is the height of the ocean.
     *  @param starveTime is the number of timesteps sharks survive without food.
     *  @param runTypes is an array that represents the species represented by
     *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
     *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
     *         sharks (which are equivalent to sharks that have just eaten).
     *  @param runLengths is an array that represents the length of each run.
     *         The sum of all elements of the runLengths array should be i * j.
     */
    
    public RunLengthEncoding(int i, int j, int starveTime,
                             int[] runTypes, int[] runLengths) {

        int allRunLength=0;//count number of cells handled
        int k=1;// used for counting,'0' is snipped off due to head field
        
        cellNum=i*j;
        width=i;
        height=j;
        this.starveTime=starveTime;
        head=new oneRun(runTypes[0],runLengths[0]);
        flag=head;
        
        oneRun cur=head;// medium
        allRunLength=runLengths[0];
        
        while(allRunLength<cellNum){
            cur.next=new oneRun(runTypes[k],runLengths[k]);
            cur.next.prev=cur;
            cur=cur.next;
            allRunLength+=runLengths[k];
            k++;
            }
        if (allRunLength>cellNum) System.out.println("size is calculated worong");
        else {
            tail=cur;
        }
    }
    
    /**
     *  restartRuns() and nextRun() are two methods that work together to return
     *  all the runs in the run-length encoding, one by one.  Each time
     *  nextRun() is invoked, it returns a different run (represented as a
     *  TypeAndSize object), until every run has been returned.  The first time
     *  nextRun() is invoked, it returns the first run in the encoding, which
     *  contains cell (0, 0).  After every run has been returned, nextRun()
     *  returns null, which lets the calling program know that there are no more
     *  runs in the encoding.
     *
     *  The restartRuns() method resets the enumeration, so that nextRun() will
     *  once again enumerate all the runs as if nextRun() were being invoked for
     *  the first time.
     *
     *  (Note:  Don't worry about what might happen if nextRun() is interleaved
     *  with addFish() or addShark(); it won't happen.)
     */
    
    /**
     *  restartRuns() resets the enumeration as described above, so that
     *  nextRun() will enumerate all the runs from the beginning.
     */
    
    public void restartRuns() {
        flag=head;
    }
    
    /**
     *  nextRun() returns the next run in the enumeration, as described above.
     *  If the runs have been exhausted, it returns null.  The return value is
     *  a TypeAndSize object, which is nothing more than a way to return two
     *  integers at once.
     *  @return the next run in the enumeration, represented by a TypeAndSize
     *          object.
     */
    
    public TypeAndSize nextRun() {
        // Replace the following line with your solution.
        
//        System.out.println("进入nextRun()");
        flag=getFlag();
        
        if(flag==head){
            restartRuns();
//            System.out.print("进入 if(flag==head)");
        }
        if(flag==null){
            restartRuns();
            System.out.println("exhauste to the end");
            return null;
        }
        
        int type=0;
        if(flag.type/10==0){
            type=flag.type%10;
        }else{
            type=flag.type/10;
        }
        int length=flag.length;
        
        flag=flag.next;
        return new TypeAndSize(type,length);
    }
    
    /**
     *  toOcean() converts a run-length encoding of an ocean into an Ocean
     *  object.  You will need to implement the three-parameter addShark method
     *  in the Ocean class for this method's use.
     *  @return the Ocean represented by a run-length encoding.
     */
    
    public Ocean toOcean() {
        
//        System.out.println(width);
//        System.out.println(height);
        int[][]cell=new int[width][height];
        int[] listCell=new int[cellNum];
        oneRun cur=head;
        int curIndex=0;
        while(cur!=null){
            for(int i=curIndex;i<curIndex+cur.length;i++){
                listCell[i]=cur.type;
            }
            curIndex+=cur.length;
            cur=cur.next;
        }
        
//        if((curIndex-cur.prev.length)!=cellNum){
//            System.out.println("listcell计算的长度不对");
//        }
//        else{
            for(int j=0;j<height;j++){
                for(int i=0;i<width;i++){
                    int k=j*height+i;
                    cell[i][j]=listCell[k];
                }
            }
//        }
        Ocean myOcean=new Ocean(width, height, starveTime);
        myOcean.setCell(cell);
        return myOcean;
    }
    
    /**
     *  The following method is required for Part III.
     */
    
    /**
     *  RunLengthEncoding() (with one parameter) is a constructor that creates
     *  a run-length encoding of an input Ocean.  You will need to implement
     *  the sharkFeeding method in the Ocean class for this constructor's use.
     *  @param sea is the ocean to encode.
     */
    
    public RunLengthEncoding(Ocean sea) {
        
        int [][]cell=sea.cell();
        int width=sea.width();
        int height=sea.height();
        int starveTime=sea.starveTime();
        
        int[] listCell=new int[width*height];
        int[] detailedType= new int[width*height];
        int[] detailedLength= new int[width*height];
        int diffTypeIndex=0;
        int consecutiveNum=0;
        
        for(int j=0;j<height;j++){
            for(int i=0;i<width;i++){
                int k=j*height+i;
                listCell[k]=cell[i][j];
            }
        }
        
        detailedType[0]=listCell[0];
        int cur=detailedType[0];
        
        for(int i=1;i<=width*height-1;i++){
            if(cur==listCell[i]){
                consecutiveNum++;
            }
            else{
                detailedLength[diffTypeIndex]=consecutiveNum+1;
                
                diffTypeIndex++;
                detailedType[diffTypeIndex]=listCell[i];
                
                consecutiveNum=0;
                cur=detailedType[diffTypeIndex];
            }
        }
        detailedLength[diffTypeIndex]=consecutiveNum+1;
//        System.out.println("consecutiveNum:"+consecutiveNum);
//        System.out.println("width/height/starveTime:"+width+"-"+height+"-"+starveTime);
//        System.out.println("diffTypeIndex:"+diffTypeIndex);
//        for(int i=0;i<=diffTypeIndex;i++){
//            System.out.println("type"+i+":"+detailedType[i]+"  "+"length"+i+":"+detailedLength[i]);
//            System.out.println();
//        }
        RunLengthEncoding oceanEncoding=new RunLengthEncoding(width,height,starveTime,detailedType,detailedLength);
        this.head=oceanEncoding.head;
        this.flag=oceanEncoding.head;
        this.tail=oceanEncoding.tail;
        this.cellNum=oceanEncoding.cellNum;
        this.width=oceanEncoding.width;
        this.height=oceanEncoding.height;
        this.starveTime=oceanEncoding.starveTime;
        
        check();
    }
    
    /**
     *  The following methods are required for Part IV.
     */
    
    /**
     *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
     *  cell is already occupied, leave the cell as it is.  The final run-length
     *  encoding should be compressed as much as possible; there should not be
     *  two consecutive runs of sharks with the same degree of hunger.
     *  @param x is the x-coordinate of the cell to place a fish in.
     *  @param y is the y-coordinate of the cell to place a fish in.
     */
    
    public void addFish(int x, int y) {
        // Your solution here, but you should probably leave the following line
        //   at the end.
        int TYPE = 2;
        addAnimal(TYPE,x,y);
        check();
    }
    
    /**
     *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
     *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
     *  just eaten.  If the cell is already occupied, leave the cell as it is.
     *  The final run-length encoding should be compressed as much as possible;
     *  there should not be two consecutive runs of sharks with the same degree
     *  of hunger.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     */
    
    public void addShark(int x, int y) {
        // Your solution here, but you should probably leave the following line
        //   at the end.
        int TYPE =starveTime+10;
        addAnimal(TYPE,x,y);
        check();
    }
    
    public void addAnimal(int TYPE, int x, int y) {
        
        int pos=convertDimension(x,y);
//        System.out.println("pos:-----"+pos);//----------
        
        if(pos>cellNum+1){
            System.out.println("animal added is out of the boundry of the ocean");
        }
        else{
            oneRun cur=head;
            oneRun trueCur=head;
            int lengthSum=0;
            
            while(lengthSum<pos&&cur!=null){
                lengthSum+=cur.length;
                trueCur=cur;//trueCur在cur前
                cur=cur.next;//cur在trueCur后
            }
//            System.out.println("lengthSum:-----"+lengthSum);//----------
            
            if(lengthSum==pos){
                int type1=trueCur.type;
                if(cur!=null){//插入位置后边还有run
                    int type2=cur.type;
//                    if(type1==TYPE){
//                        trueCur.length++;
//                    }else
                    if(type2==TYPE){
                        trueCur.length--;
                        cur.length++;
                    }else{
                        oneRun newRun= new oneRun(TYPE,1);
                        trueCur.length--;
                        newRun.prev=trueCur;
                        newRun.next=cur;
                        trueCur.next=newRun;
                        cur.prev=newRun;
                    }
                }else{//插入位置后边没有run了
                    if(type1!=TYPE){
                        trueCur.length--;
                        oneRun newRun= new oneRun(TYPE,1);
                        newRun.prev=trueCur;
                        trueCur.next=newRun;
                        tail=newRun;
                    }
                }
            }else{//lengthSum>pos
                if(trueCur.type!=TYPE){
                    oneRun newRun=new oneRun(TYPE,1);
                    oneRun backHalfRun=new oneRun(trueCur.type,(trueCur.length-(lengthSum-pos+1)-1));
                    trueCur.length=lengthSum-pos+1;
                    trueCur.next=newRun;
                    newRun.prev=trueCur;
                    newRun.next=backHalfRun;
                    backHalfRun.prev=newRun;
                    if(cur!=null){
                        backHalfRun.next=cur;
                        cur.prev=backHalfRun;
                    }else{
                        tail=backHalfRun;
                    }
                }
            }
        }
    }
    
    public int convertDimension(int x, int y) {
        
        return x+y*height+1;
    }
    /**
     *  check() walks through the run-length encoding and prints an error message
     *  if two consecutive runs have the same contents, or if the sum of all run
     *  lengths does not equal the number of cells in the ocean.
     */
    
    public void check() {
        
        oneRun current=head;
        int cellSum=0;
        
        while(current!=null){
            cellSum+=current.length;
//            System.out.println(cellSum);
            if (current.next!=null&&current.type==current.next.type){
                System.out.println("There exists consecutive runs!");
            }
            current=current.next;
        }
        
        if(cellSum!=cellNum)System.out.println("The sum of all run lengths does not equal the number of cells in the ocean!");
        
        
    }
    
}