
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
	  if(floors == 1 || floors == 0)
		  return floors;
	  if (sheets == 1)
		  return floors;

    
      int min = Integer.MAX_VALUE;  
      int res;  
    
 
      for (int x = 1; x <= floors; x++)  
      {  
          res = Math.max(glassFallingRecur(x-1, sheets-1),  
                      glassFallingRecur(floors-x, sheets));  
          if (res < min)  
              min = res;  
      }  
    
      return min + 1;  

 }
  
  public int glassFallingMemo(int floors, int sheets) 
  {
	  int memo[][] = new int[floors + 1][sheets + 1];

      return glassFallingMemoRecur(floors, sheets, memo);
  }
  
  public int glassFallingMemoRecur(int floors, int sheets, int[][] memo)
  {

	 if(memo[floors][sheets] != 0)
	 {
		 return memo[floors][sheets];
	 }
	 if(floors == 0 || floors == 1)
	 {
		 return floors;
		 
	 }
	 
	 if(sheets == 1) {
		 return floors;
	 }
	 memo[floors][sheets] = floors;
	 int value;
	 for(int i = 1; i <= floors; i++) 
	 {
		 value = Math.max(glassFallingMemoRecur(i - 1, sheets - 1, memo), glassFallingMemoRecur(floors - i, sheets, memo));

		 if(value < memo[floors][sheets])
		 {
			 memo[floors][sheets] = value + 1;
		 }
	 }
	 return memo[floors][sheets];
  }
	
  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
      int [][] glassDrops = new int [floors+1][sheets+1];
    
      for (int i = 1; i <=sheets ; i++) {
          glassDrops[0][i] = 0;
          glassDrops[1][i] = 1;
      }
      for (int i = 1; i <=floors ; i++) {
          glassDrops[i][1] = i;
      }

      for (int i = 2; i <=floors ; i++) {
          for (int j = 2; j <=sheets ; j++) {
              glassDrops[i][j] = Integer.MAX_VALUE;
              int tempResult;
              for (int k = 1; k <=j ; k++) {
                  tempResult = 1 + Math.max(glassDrops[i-1][k-1], glassDrops[i][j-k]);
                  glassDrops[i][j] = Math.min(tempResult,glassDrops[i][j]);
              }
          }
      }

      return glassDrops[floors][sheets];

  }


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemo(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}