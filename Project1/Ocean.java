/* Ocean.java */

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {
    
    /**
     *  Do not rename these constants.  WARNING:  if you change the numbers, you
     *  will need to recompile Test4.java.  Failure to do so will give you a very
     *  hard-to-find bug.
     */
    
    public final static int EMPTY = 0;
    public final static int SHARK = 1;
    public final static int FISH = 2;

    /**
     *  Define any variables associated with an Ocean object here.  These
     *  variables MUST be private.
     */
    private int height;
    private int width;
    private int starveTime;
    private int[][] cell;
    
    
    
    /**
     *  The following methods are required for Part I.
     */
    
    /**
     *  Ocean() is a constructor that creates an empty ocean having width i and
     *  height j, in which sharks starve after starveTime timesteps.
     *  @param i is the width of the ocean.
     *  @param j is the height of the ocean.
     *  @param starveTime is the number of timesteps sharks survive without food.
     */
    
    public Ocean(int i, int j, int starveTime) {
        // Your solution here.
        width=i;
        height=j;
        this.starveTime=starveTime;
        cell=new int[i][j];
    }
    
    /**
     *  width() returns the width of an Ocean object.
     *  @return the width of the ocean.
     */
    
    public int width() {
        // Replace the following line with your solution.
        return width;
    }
    
    /**
     *  height() returns the height of an Ocean object.
     *  @return the height of the ocean.
     */
    
    public int height() {
        // Replace the following line with your solution.
        return height;
    }
    
    /**
     *  starveTime() returns the number of timesteps sharks survive without food.
     *  @return the number of timesteps sharks survive without food.
     */
    
    public int starveTime() {
        // Replace the following line with your solution.
        return starveTime;
    }
    /**
     *  cell()
     *  @return the reference of 2-dimension array 
     */
    public int[][] cell() {
        // Replace the following line with your solution.
        return cell;
    }
    public void setCell(int[][] cell) {
        // Replace the following line with your solution.
        this.cell=cell;
    }
    
    /**_ok
     *  wrapAround() convert invalid arraies out of the boundry to valid arraies
     *  @param i is the x-coordinate of the cell to place a fish in.
     *  @param j is the y-coordinate of the cell to place a fish in.
     *  @return  validCell[0] and validCell[1]====(x,y)
     */
    public int[] wrapAround(int x, int y) {
        int[] validCell=new int[2];
        if (x>=0)
            validCell[0]=x%width;
        else
            validCell[0]=width+x%width;
        
        if (y>=0)
            validCell[1]=y%height;
        else
            validCell[1]=height+y%height;
        
        return validCell;
    }


    /**
     *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
     *  cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a fish in.
     *  @param y is the y-coordinate of the cell to place a fish in.
     */
    
    public void addFish(int x, int y) {
        // Your solution here.
        if(cell[x][y]==EMPTY)
            cell[x][y]=FISH;

    }

    /**
     *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
     *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
     *  just eaten.  If the cell is already occupied, leave the cell as it is.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     */
    
    public void addShark(int x, int y) {
        if(cell[x][y]==EMPTY)
            cell[x][y]=10+starveTime;//假设starvetime＝5，用15表示刚下生或刚吃饱的鲨鱼。
    }
    
    /**
     *  vanish() (with two parameters) assing cell (x, y) to EMPTY
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     */
    public void vanish(int x, int y) {

        cell[x][y]=EMPTY;
    }
    
    /**
     *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
     *  a fish, and SHARK if it contains a shark.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     */
    
    public int cellContents(int x, int y) {
//        System.out.println("x: "+x);
//        System.out.println("y: "+y);
        if(cell[x][y]==EMPTY)
            return EMPTY;
        else if(cell[x][y]==FISH)
            return FISH;
        else
            return SHARK;
    }

    
    /**_ok
     *  cellAround() get the eight cell contents around cell[i][j]
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     *  @return an int array
     */
    
    public int[]  cellAround(int x, int y) {
        int[] kindNum=new int[]{0,0,0};
        int validCell[]=new int[2];
//        System.out.println(x+","+y+"--- "+cellContents(x,y));
        for(int i=x-1;i<=x+1;i++){
            for (int j=y-1;j<=y+1;j++){
                validCell =wrapAround(i,j);
                int p=validCell[0];
                int q=validCell[1];
//                System.out.println(p+","+q+": "+cellContents(p,q));
                if (p==x&&q==y);//如果是x／y自己，排除之＝＝什么也不做
                else{
                     switch(cellContents(p,q)){
                        case EMPTY:kindNum[0]++; break;
                        case SHARK:kindNum[1]++; break;
                        case FISH: kindNum[2]++; break;
                        default: System.out.println("aroundContent() out of index"); break;
                    }
                }
            }
        }
//        System.out.println(x+","+y+"empty/shark/fish: "+kindNum[0]+" "+kindNum[1]+" "+kindNum[2]);
        return kindNum;
    }
    
    
    
    /**
     *  timeStep() performs a simulation timestep as described in README.
     *  @return an ocean representing the elapse of one timestep.
     */
    
    public Ocean timeStep() {
        // Replace the following line with your solution.
        Ocean nextOcean=new Ocean(width, height, starveTime);
        
//        for (int j=0;j<height;j++){
//            for(int i=0;i<width;i++){
//                System.out.print("-"+cell[i][j]+"-");
//            }
//            System.out.println(" ");
//        }
//        
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                int[] aroundContent=cellAround(i,j);//check num of EMPTY/SHARK/FISH around
//                System.out.println(aroundContent[0]+" "+aroundContent[1]+" "+aroundContent[2]);
                if(cellContents(i,j)==EMPTY){
                    if(aroundContent[2]>=2&&aroundContent[1]<=1) nextOcean.addFish(i,j);
                    else if(aroundContent[2]>=2&&aroundContent[1]>=2) nextOcean.addShark(i,j);
                    else nextOcean.vanish(i,j);
                }
                if(cellContents(i,j)==FISH){
                    if(aroundContent[1]==1) nextOcean.vanish(i,j);
                    else if(aroundContent[1]>=2) nextOcean.addShark(i,j);
                    else nextOcean.addFish(i,j);
                }
                if(cellContents(i,j)==SHARK){
                    if(aroundContent[2]==0){
                        if(cell[i][j]==11)//
                            nextOcean.vanish(i,j);
                        else
                            nextOcean.addShark(i,j,(cell[i][j]-1));
                    }
                    if(aroundContent[2]>=1){
                        nextOcean.addShark(i,j);
                    }
                }
            }
        }
        return nextOcean;
    }
    

    /**
     *  The following method is required for Part II.
     */
    
    /**
     *  addShark() (with three parameters) places a shark in cell (x, y) if the
     *  cell is empty.  The shark's hunger is represented by the third parameter.
     *  If the cell is already occupied, leave the cell as it is.  You will need
     *  this method to help convert run-length encodings to Oceans.
     *  @param x is the x-coordinate of the cell to place a shark in.
     *  @param y is the y-coordinate of the cell to place a shark in.
     *  @param feeding is an integer that indicates the shark's hunger.  You may
     *         encode it any way you want; for instance, "feeding" may be the
     *         last timestep the shark was fed, or the amount of time that has
     *         passed since the shark was last fed, or the amount of time left
     *         before the shark will starve.  It's up to you, but be consistent.
     */
    
    public void addShark(int x, int y, int feeding) {
        // Your solution here.
        if (feeding<=starveTime)
            cell[x][y]=10+feeding;//假设starvetime＝5，用15表示新鲨鱼；用14、13、12、11、10表示不同饥饿程度的鲨鱼以及死鲨鱼。
        
    }
    
    /**
     *  The following method is required for Part III.
     */
    
    /**
     *  sharkFeeding() returns an integer that indicates the hunger of the shark
     *  in cell (x, y), using the same "feeding" representation as the parameter
     *  to addShark() described above.  If cell (x, y) does not contain a shark,
     *  then its return value is undefined--that is, anything you want.
     *  Normally, this method should not be called if cell (x, y) does not
     *  contain a shark.  You will need this method to help convert Oceans to
     *  run-length encodings.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     */
    
    public int sharkFeeding(int x, int y) {
        // Replace the following line with your solution.
            return cell[x][y]%10;//若starveTime=5;则 5/4/3/2/1依次表示鲨鱼“由饱到饿”。
    }
    
}