/* SimpleBoard.java */

/**
 *  Simple class that implements an 8x8 game board with three possible values
 *  for each cell:  0, 1 or 2.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class SimpleBoard {
  private final static int DIMENSION = 8;
  private int[][] grid;

  /**
   *  Invariants:  
   *  (1) grid.length == DIMENSION.
   *  (2) for all 0 <= i < DIMENSION, grid[i].length == DIMENSION.
   *  (3) for all 0 <= i, j < DIMENSION, grid[i][j] >= 0 and grid[i][j] <= 2.
   **/

  /**
   *  Construct a new board in which all cells are zero.
   */

  public SimpleBoard() {
    grid = new int[DIMENSION][DIMENSION];
  }

  /**
   *  Set the cell (x, y) in the board to the given value mod 3.
   *  @param value to which the element should be set (normally 0, 1, or 2).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   **/

  public void setElementAt(int x, int y, int value) {
    grid[x][y] = value % 3;
    if (grid[x][y] < 0) {
      grid[x][y] = grid[x][y] + 3;
    }
  }

  /**
   *  Get the valued stored in cell (x, y).
   *  @param x is the x-index.
   *  @param y is the y-index.
   *  @return the stored value (between 0 and 2).
   *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
   *  is given.
   */

  public int elementAt(int x, int y) {
    return grid[x][y];
  }

  /**
   *  Returns true if "this" SimpleBoard and "board" have identical values in
   *    every cell.
   *  @param board is the second SimpleBoard.
   *  @return true if the boards are equal, false otherwise.
   */

  public boolean equals(Object board) {
    // Replace the following line with your solution.  Be sure to return false
    //   (rather than throwing a ClassCastException) if "board" is not
    //   a SimpleBoard.
	try{
		int[][] curGrid=((SimpleBoard)board).grid;
		for(int i=0;i<DIMENSION;i++){
			for(int j=0;j<DIMENSION;j++){
				if(grid[i][j]!=curGrid[i][j])
					return false;
			}
		}
		return true;
	}catch(ClassCastException e){
		return false;
	}
  }

  /**
   *  Returns a hash code for this SimpleBoard.
   *  @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
   */

//  public int hashCode() {
//    // Replace the following line with your solution.
//	int result[]=new int[]{0,0};
//	int k=0;
//	result[0]=grid[DIMENSION-1][DIMENSION-1]*(int)(Math.pow(3,0));
//
//	for(int i=DIMENSION-1;i>=0;i--){
//		for(int j=DIMENSION-2;j>=0;j--){
//
//			result[1]=result[0]+grid[i][j]*(int)(Math.pow(3,k));
//			if(result[1]>=Integer.MAX_VALUE||result[1]<=Integer.MIN_VALUE){
//				System.out.println("---");
//				System.out.println(result[0]);
//				return result[0];
//			}
//			j--;                                       // ������һ��j
//			if(j>=0){
//				result[0]+=grid[i][j]*(int)(Math.pow(3,k));
//				k++;
//			}
//		}
//    }
//	System.out.println("+++");
//	System.out.println(result[1]);
//	return result[1];
//  }
public int hashCode() {
    // Replace the following line with your solution.
	int boardNum=0;
	int k=0;
	for(int i=DIMENSION-1;i>=0;i--){
		for(int j=DIMENSION-1;j>=0;j--){
			boardNum=boardNum+(int)((double)grid[i][j]*(Math.pow(3,k)));
//			System.out.println("<<--"+(int)(grid[i][j]*(Math.pow(3,k))));
//			System.out.println("#-------"+boardNum);
			k++;
//			System.out.println(grid[i][j]*(Math.pow(3,k)));
		}
    }
//	System.out.println(boardNum+"--------------");
	return boardNum;
  }

}

