/**
 * 
 * @author zmm2962
 *
 */

import java.io.*;
import java.math.*;

public class AllWhiteSquare {

	public static void main(String[] args) {
		
		BufferedReader in;
		
		int n = 0;
		int[][] square = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			
			n = Integer.parseInt(in.readLine());
			square = new int[n][n];
			
			//Instantiate the square array with 1 representing white squares and 0 representing black squares.
			for (int i = 0; i < n; i++) {
				String t = in.readLine();
				for (int j = 0; j < n; j++) {
					char temp = t.charAt(j);
					if (temp == 'b') 
						square[i][j] = 0;
					else
						square[i][j] = 1;
				}
			}
			
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		int[][] temp = new int[n][n];	//create an auxiliary array which will hold the max side length up to indices i and j. 
		
		//set the first row and column equal to the original array, since we will use these values to generate the rest of temp
		for (int i = 0; i < n; i++) {
			temp[0][i] = square[0][i];
			temp[i][0] = square[i][0];
		}
		
		//This section generates the rest of the auxiliary array temp. 
		//The algorithm builds the max square from the top left to the bottom right. 
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
			
				if(square[i][j] == 1) {
					
					int x = temp[i-1][j-1];		//Here it takes the minimum of the values to the left, top, and top-left.
					int y = temp[i][j-1];
					int z = temp[i-1][j];
					
					if (z > y)
						z = y;
					if (z > x)
						z = x;
											//We get the minimum value and put it in the auxiliary array. Each time there is a white square it increases.
					temp[i][j] = z+1;		//Add one to the minimum since we are now increasing our square by one, since square[i][j] = 1 (white)
				}
				else {
					temp[i][j] = 0;		//if the original array value is zero (black), then the square is broken and reset to 0
				}				
			}
		}
		
		
		
		// This section simply calculates the maximum (bottom-right) indices of our white square in the auxiliary array. Since we built the
		// aux array by incrementing by one each time, we can simply do a less than check to see if we need to keep re-setting the max values. 
		int maxTemp = temp[0][0];
		int[] maxIndices = new int[2];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (temp[i][j] > maxTemp) {
					maxTemp = temp[i][j];
					maxIndices[0] = i;
					maxIndices[1] = j;
				}
			}
		}
		
		//These next two sections iterate backwards through the original array starting from the max indices for i and j and count how many previous values are
		//1's aka white squares. Then we print the lesser of the two, which will be the maximum side length of a kxk square of white spaces.
		int iCount = 0;
		int jCount = 0;
		for (int i = maxIndices[0]; i >= 0; i--) {
			if (square[i][maxIndices[1]] == 1)
				iCount++;
			else
				break;
		}
		
		for (int j = maxIndices[1]; j >= 0; j--) {
			if (square[maxIndices[0]][j] == 1)
				jCount++;
			else
				break;
		}
		
		int f;
		

		if (jCount < iCount) 
			f = jCount;
		else 
			f = iCount;
		System.out.println(f);
		
	}
	
}
