import java.lang.Math;


public class MainRunner {

	//Given a size generate a matrix into location 'x'
	public static int[][] generateMatrix(int[][] x, int size){
		x = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			for (int j= 0; j < size; j++) {
				x[i][j] = (int)Math.round(Math.random()) * 10;
			}
		}
		
		return x;
	}//End of Matrix Generation
	
	//Given 3 times return the fastest
	//0 -> x; 1 -> y; 2 -> z
	public static int fastestRuntime(long x, long y, long z) {
		if(x < y) {
			if (x < z)
				return 0;
			return 2;
		}
		else if(y < z) {
			return 1;
		}
		else
			return 2;
	}//End of FastestRuntime
	
	//Determine if the previous fastest runtime is still the fastest
	//Returns true if the current fastest is different
	public static boolean compare(int older, int newer, int i) {
		String[] x = {"Brute Force", "Divide and Conq", "Strassen"};
		if(older != newer) {
			System.out.println("\n"+x[newer]+" is now faster than " + x[older]+" at matrix size "+i+ " by " + i);
			return true;
		}
		return false;
	}//End of Compare Runtime
	
	public static void main(String[] args) {
		BruteForceMethod bruteForce = new BruteForceMethod();
		DivideAndConqMethod divideAndConq = new DivideAndConqMethod();
		StrassenMethod strassen = new StrassenMethod();
		
		String[] x = {"Brute Force", "Divide and Conq", "Strassen"};
		
		int i = 4;
		int[][] matrix1 = new int[i][i];
		int[][] matrix2 = new int[i][i];
		
		long startTime;
		long bruteTimeAvg = 0;
		long divideTimeAvg = 0;
		long strassenTimeAvg = 0;
		
		int fastest = 0;
		//Loop that increases i every iteration
		while(true) {
			i = i*2;
			bruteTimeAvg = 0;
			divideTimeAvg = 0;
			strassenTimeAvg = 0;
			System.out.println("-------------------------------");
			System.out.println("Previous Fastest: " + x[fastest]);
			System.out.println("Matrix Size: " + i);
			
			//Run 10 cases for matrix size i
			for(int j = 0; j < 10; j++) {
				generateMatrix(matrix1, i);
				generateMatrix(matrix2, i);
				
				startTime = System.nanoTime();
				bruteForce.productOfMatrix(matrix1, matrix2);
				bruteTimeAvg += System.nanoTime() - startTime;
				
				startTime = System.nanoTime();
				divideAndConq.productOfMatrix(matrix1, matrix2);
				divideTimeAvg += System.nanoTime() - startTime;
				/*
				startTime = System.nanoTime();
				divideAndConqLesser.multip(matrix1, matrix2);
				divideTimeAvg += System.nanoTime() - startTime;
				*/
				startTime = System.nanoTime();
				strassen.productOfMatrix(matrix1, matrix2);
				strassenTimeAvg += System.nanoTime() - startTime;
				
			}
			
			bruteTimeAvg = bruteTimeAvg/10;
			divideTimeAvg = divideTimeAvg/10;
			strassenTimeAvg = strassenTimeAvg/10;
			System.out.println("Brute Force Time: " + bruteTimeAvg);
			System.out.println("Divide and Conq Time: " + divideTimeAvg);
			System.out.println("Strassen Time: " + strassenTimeAvg);
			
			if (compare(fastest,fastestRuntime(bruteTimeAvg, divideTimeAvg, strassenTimeAvg), i)) {
				fastest = fastestRuntime(bruteTimeAvg, divideTimeAvg, strassenTimeAvg);
			}
			
			System.gc();
			
		}
	}
}

