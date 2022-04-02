
public class BruteForceMethod {
	public int[][] productOfMatrix(int[][] x, int [][] y){
		int[][] result = new int[x.length][y.length];
		
		// i and j represent position in result array
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				
				//k represents the number of calculations to find result(i,j)
				for (int k = 0; k < x.length; k++ ) {		
					result[i][j] += x[i][k] * y[k][j];
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[][] firstMatrix =  { {2, 0, -1, 6}, {3, 7, 8, 0}, {-5, 1, 6, 2}, {8, 0, 2, 7} };
        int[][] secondMatrix = { {0, 1, 6, 3}, {-2, 8, 7, 1}, {2, 0, -1, 0}, {9, 1, 6, -2} };
        
        BruteForceMethod bruteForce = new BruteForceMethod();
        int [][] result = bruteForce.productOfMatrix(firstMatrix, secondMatrix);
        
        for(int i = 0; i < result.length; i++) {
        	for(int j = 0; j < result.length; j++) {
        		System.out.print(result[i][j] + "\t");
        	}
        	System.out.println();
        }
	}

}
