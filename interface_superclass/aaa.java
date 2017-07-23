public class aaa{

    public static void main(String arg []){
//    char[] X=new char[10];
//    char[] Y=new char[12];
        
        
        char[] X=new char[]{'G','T','T','C','C','T','A','A','T','A'};
        char[] Y=new char[]{'C','G','A','T','A','A','T','T','G','A','G','A'};
        int n = X.length;
        int m = Y.length;
        int[ ][ ] L = new int[n+1][m+1];
        L=LCS(X, Y);

        System.out.println("--------------------------------------------------------");
        for(int i=0;i<=n;i++){
            System.out.print(" | ");
            for(int j=0;j<=m;j++){
                System.out.print(L[i][j]+" | ");
            }
            System.out.println();
            System.out.print("--------------------------------------------------------");
            System.out.println();
        }
        
//        L=LCS(Y, X);
//        System.out.println("--------------------------------------------------------");
//        for(int i=0;i<=m;i++){
//            System.out.print(" | ");
//            for(int j=0;j<=n;j++){
//                System.out.print(L[i][j]+" | ");
//            }
//            System.out.println();
//            System.out.print("--------------------------------------------------------");
//            System.out.println();
//        }
    
    }



    public static int[ ][ ] LCS(char[ ] X, char[ ] Y) {
    int n = X.length;
    int m = Y.length;
    int[ ][ ] L = new int[n+1][m+1];
        for (int j=0; j < n; j++){
            for (int k=0; k < m; k++){
                if (X[j] == Y[k]) // align this match
                    L[j+1][k+1] = L[j][k] + 1;
                else // choose to ignore one character
                    L[j+1][k+1] = Math.max(L[j][k+1], L[j+1][k]);
            }
        }
    return L;
    }
}
