/*
 * Divide and Conquer Method which creates new arrays when slicing
 * Not used in Runner File
 */
public class DivideAndConqLessEfficientMethod {
	public int[][] multip(int[][] x, int[][] y){
				
		return wrapperMulti(x, y);
		
	}

	//Divide matrix into 4 quadrants and return a certain quadrant
	public int[][] subArray(int[][] a, int x, int y) {
		int size = a.length / 2;
		int[][] result = new int[size][size];
		for(int i = x*size; i < (x*size)+size; i++) {
			for(int j = y*size; j < (y*size)+size; j++) {
				result[i-x*size][j-y*size] = a[i][j];
			}
		}
	
		return result;
	}
	
	public int[][] addMatrix(int[][] x, int[][] y,
			int size, int subtract) {
		int[][] result = new int[x.length][x.length];
		for( int i = 0; i < size; i++) {
			for( int j = 0; j < size; j++) {
				result[i][j] = x[i][j] + (subtract * y[i][j]);
			}
		}
		
		return result;
	}
	
	public void mergeArray(int[][] result, int[][] x,
			int row, int col, int size) {
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j ++) {
				result[i][j] = x[i-row][j-col];
			}
		}
	}
	
	public int[][] wrapperMulti(int[][] x, int[][] y){
		int[][] result = new int[x.length][x.length];
		
		if(x.length == 1) {
			result[0][0] = x[0][0] * y[0][0];
		}
		else {
			int size = x.length;
			int[][] c11  = new int[size/2][size/2];
			int[][] c12  = new int[size/2][size/2];
			int[][] c21  = new int[size/2][size/2];
			int[][] c22  = new int[size/2][size/2];
			
			c11 = addMatrix(
					wrapperMulti(
							subArray(x,0,0),
							subArray(y,0,0)
							),
					wrapperMulti(
							subArray(x,0,1),
							subArray(y,1,0)
							),
					size/2,1);
			
			
			c12 = addMatrix(
					wrapperMulti(
							subArray(x,0,0),
							subArray(y,0,1)
							),
					wrapperMulti(
							subArray(x,0,1),
							subArray(y,1,1)
							),
					size/2,1);
			
			
			c21 = addMatrix(
					wrapperMulti(
							subArray(x,1,0),
							subArray(y,0,0)
							),
					wrapperMulti(
							subArray(x,1,1),
							subArray(y,1,0)
							),
					size/2,1);
			
			
			c22 = addMatrix(
					wrapperMulti(
							subArray(x,1,0),
							subArray(y,0,1)
							),
					wrapperMulti(
							subArray(x,1,1),
							subArray(y,1,1)
							),
					size/2,1);
			
			mergeArray(result, c11, 0, 0, size/2);
			mergeArray(result, c12, 0, size/2, size/2);
			mergeArray(result, c21, size/2, 0, size/2);
			mergeArray(result, c22, size/2, size/2, size/2);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[][] firstMatrix =  { {2, 0, -1, 6}, {3, 7, 8, 0}, {-5, 1, 6, 2}, {8, 0, 2, 7} };
        int[][] secondMatrix = { {0, 1, 6, 3}, {-2, 8, 7, 1}, {2, 0, -1, 0}, {9, 1, 6, -2} };
        
        DivideAndConqLessEfficientMethod divideConqLesser = new DivideAndConqLessEfficientMethod();
        
        int [][] result = divideConqLesser.multip(firstMatrix, secondMatrix);
        
        for(int i = 0; i < result.length; i++) {
        	for(int j = 0; j < result.length; j++) {
        		System.out.print(result[i][j] + "\t");
        	}
        	System.out.println();
        }
	}
	
}
