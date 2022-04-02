/*
 * Divide and Conquer Method which uses index slicing
 * Used in Main Runner
 */
public class DivideAndConqMethod {
	
	public int[][] productOfMatrix(int[][] x, int [][] y){
		int size = x.length;
		
		return POMhelper(x,y,0,0,0,0,size);
	}
	
	//Wrapper Function
	//Matrix A, Matrix B, Position of A(rowX,colX), Position of B(rowY, colY),
	//Size of resulting matrix (size x size)
	public int[][]  POMhelper(int[][] x, int[][] y,
			int rowX, int colX, 
			int rowY, int colY,
			int size){
		
		int[][] result = new int[size][size];
		
		//BASE CASE
		if (size == 4) {
			BruteForceMethod brute = new BruteForceMethod();
			result = brute.productOfMatrix(x,y);
		}
		
		else {
			//Will be used to represent the mid point of array
			int hSize = size/2;
			
			//Result(1,1) 
			//X11 * Y11
			//X12 * Y21
			sumOfMatrix(result,
					POMhelper(x, y, rowX, colX, rowY, colY, hSize),
					POMhelper(x, y, rowX, colX+hSize, rowY+hSize, colY, hSize),
					0,0);

			
			//Result(1,2)
			//X11 * Y12 
			//X12 * Y22 
			sumOfMatrix(result,
					POMhelper(x, y, rowX, colX, rowY, colY+hSize, hSize),
					POMhelper(x, y, rowX, colX+hSize, rowY+hSize, colY+hSize, hSize),
					0,hSize);

			
			//Result(2,1)
			//X21 * Y11
			//X22 * Y21
			sumOfMatrix(result,
					POMhelper(x, y, rowX+hSize, colX, rowY, colY, hSize),
					POMhelper(x, y, rowX+hSize, colX+hSize, rowY+hSize, colY, hSize),
					hSize,0);

			
			//Result(2,2)
			//X21 * Y12
			//X22 * Y22
			sumOfMatrix(result,
					POMhelper(x, y, rowX+hSize, colX, rowY, colY+hSize, hSize),
					POMhelper(x, y, rowX+hSize, colX+hSize, rowY+hSize, colY+hSize, hSize),
					hSize,hSize);

		}//End of Recursive Statement
		
		return result;
	}//END OF Product Of Matrix
	
	//Add 2 matrix together and put sum into matrix result
	//Row and Col are used to define the position in the resulting matrix
	public void sumOfMatrix(int[][] result, int[][] x, int[][] y, int row, int col) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				result[i+row][j+col] = x[i][j] + y[i][j];
			}
		}
	}//End of Sum of Matrix
	
	public void printMatrix(int[][] result) {
        for(int i = 0; i < result.length; i++) {
        	for(int j = 0; j < result.length; j++) {
        		System.out.print(result[i][j] + "\t");
        	}
        	System.out.println();
        }
	}//End of Print Matrix
	
	public static void main(String[] args) {
		int[][] firstMatrix =  { {2, 0, -1, 6}, {3, 7, 8, 0}, {-5, 1, 6, 2}, {8, 0, 2, 7} };
        int[][] secondMatrix = { {0, 1, 6, 3}, {-2, 8, 7, 1}, {2, 0, -1, 0}, {9, 1, 6, -2} };
        
        DivideAndConqMethod divideConq = new DivideAndConqMethod();
        
        int [][] result = divideConq.productOfMatrix(firstMatrix, secondMatrix);
        
        divideConq.printMatrix(result);
	}
}
