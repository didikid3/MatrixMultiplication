
public class StrassenMethod {
	
		public int[][] productOfMatrix(int[][] x, int [][] y){

			return productHelper(x,y);
		}
		
		//Wrapper Function
		public int[][] productHelper(int[][] x,int[][] y)
		{
			int size = x.length;
			int[][] result = new int[size][size];
			
			//Base Case
			if (size == 4) {
				BruteForceMethod brute = new BruteForceMethod();
				result = brute.productOfMatrix(x,y);
			}
			
			else {
				int[][] p1 = new int[size/2][size/2];
				int[][] p2 = new int[size/2][size/2];
				int[][] p3 = new int[size/2][size/2];
				int[][] p4 = new int[size/2][size/2];
				int[][] p5 = new int[size/2][size/2];
				int[][] p6 = new int[size/2][size/2];
				int[][] p7 = new int[size/2][size/2];
				
				int[][] c11;
				int[][] c12;
				int[][] c21;
				int[][] c22;
				
				//X11 * (Y12 - Y22)
				p1 = productHelper(
						subArray(x,0,0), 
						addMatrix(subArray(y,0,1), subArray(y,1,1),
								size/2, -1)
						);

				//(X11 + X12) * Y22
				p2 = productHelper( 
						addMatrix(subArray(x,0,0), subArray(x,0,1),
								size/2, 1),
						subArray(y,1,1)
						);
				
				//(X21 + X22) * Y11
				p3 = productHelper( 
						addMatrix(subArray(x,1,0), subArray(x,1,1),
								size/2, 1),
						subArray(y,0,0)
						);
				
				//X22 * (Y21 - Y11)
				p4 = productHelper(
						subArray(x,1,1), 
						addMatrix(subArray(y,1,0), subArray(y,0,0),
								size/2, -1)
						);
				
	
				//(X11 + X22) * (Y11 + Y22)
				p5 = productHelper(
						addMatrix(subArray(x,0,0), subArray(x,1,1),
								size/2, 1), 
						addMatrix(subArray(y,0,0), subArray(y,1,1),
								size/2, 1)
						);
				
				//(X12 - X22) * (Y21 + Y22)
				p6 = productHelper(
						addMatrix(subArray(x,0,1), subArray(x,1,1),
								size/2, -1), 
						addMatrix(subArray(y,1,0), subArray(y,1,1),
								size/2, 1)
						);
				
				//(X11 - X21) * (Y11 + Y12)
				p7 = productHelper(
						addMatrix(subArray(x,0,0), subArray(x,1,0),
								size/2, -1), 
						addMatrix(subArray(y,0,0), subArray(y,0,1),
								size/2, 1)
						);
				

				c11 = addMatrix(
						addMatrix(p4, p2, size/2, -1),
						addMatrix(p5, p6, size/2, 1),
						size/2, 1
						);
				
				c12 = addMatrix(p1, p2, size/2, 1);
				
				c21 = addMatrix(p3, p4, size/2, 1);
				
				c22 = addMatrix(
						addMatrix(p1, p3, size/2, -1),
						addMatrix(p5, p7, size/2, -1),
						size/2, 1
						);


				//Add Quadrants into final array
				mergeArray(result, c11, 0, 0, size/2);
				mergeArray(result, c12, 0, size/2, size/2);
				mergeArray(result, c21, size/2, 0, size/2);
				mergeArray(result, c22, size/2, size/2, size/2);
				
			}//End of Recursive
			
			return result;
		}//End of Matrix Multiplication
		
			
		//Divide matrix into 4 quadrants and return a certain quadrant
		//X and Y denote the quadrant of the matrix (0 or 1 To denote)
		public int[][] subArray(int[][] a, int x, int y) {
			int size = a.length / 2;
			int[][] result = new int[size][size];
			for(int i = x*size; i < (x*size)+size; i++) {
				for(int j = y*size; j < (y*size)+size; j++) {
					result[i-x*size][j-y*size] = a[i][j];
				}
			}
		
			return result;
		}//End of Sub-array
		
		//Add a quadrant back into the returning arrays
		//Row and Col denote position in result matrix
		//Size determines how large of a matrix is being added
		public void mergeArray(int[][] result, int[][] x,
				int row, int col, int size) {
			for(int i = row; i < row + size; i++) {
				for(int j = col; j < col + size; j ++) {
					result[i][j] = x[i-row][j-col];
				}
			}
		}//End of Merge-Array
		
		//Add 2 matrix together
		//Size determines how large of a matrix is being added
		//Subtract takes a scalar value but is only used as 1 and -1
		//Matrix Y is multiplied by 'subtract' to determine whether subtraction occurs 
		public int[][] addMatrix(int[][] x, int[][] y,
				int size, int subtract) {
			int[][] result = new int[x.length][x.length];
			for( int i = 0; i < size; i++) {
				for( int j = 0; j < size; j++ ) {
					result[i][j] = x[i][j] + (subtract * y[i][j]);
				}
			}
			
			return result;
		}//End of Add Matrix
		
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
        
			StrassenMethod strassen = new StrassenMethod();
			
	        int [][] result = strassen.productOfMatrix(firstMatrix, secondMatrix);

	        strassen.printMatrix(result);
		}

		
}
