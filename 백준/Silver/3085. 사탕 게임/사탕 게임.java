import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {
	public void swap(int i1, int j1, int i2, int j2, char[][] a) {
		char tmp = a[i1][j1];
		a[i1][j1] = a[i2][j2];
		a[i2][j2] = tmp;
	}
	
	public int check(int i, int j, int n, char[][] a) {
		int max = 1;
		int cnt = 1;
		char cur_i = a[i][0];
		char cur_j = a[0][j];
		
		for (int col = 1; col < n; col++) {
			if (cur_i != a[i][col]) {
				cur_i = a[i][col];
				cnt = 1;
			}
			else {
				cnt++;
			}
			max = Math.max(cnt, max);
		}
		cnt = 1;
		
		for (int row = 1; row < n; row++) {
			if (cur_j != a[row][j]) {
				cur_j = a[row][j];
				cnt = 1;
			}
			else {
				cnt++;
			}
			max = Math.max(cnt, max);
		}
		return max;
	}
	
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = sc.nextInt();
		char[][] candies = new char[n][n];
		int max = 1;
		
		for (int i = 0; i < n; i++) {
			candies[i] = sc.next().toCharArray();
		}
//		for (int i = 0; i < n; i++) {
//			String tmp = sc.next();
//			
//			for (int j = 0; j < n; j++) {
//				candies[i][j] = tmp.charAt(j);
//			}
//		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j + 1 < n) {
					swap(i, j, i, j + 1, candies);
					max = Math.max(max, check(i, j, n, candies));
					max = Math.max(max, check(i, j + 1, n, candies));
					swap(i, j, i, j + 1, candies);
				}
				if (i + 1 < n) {
					swap(i, j, i + 1, j, candies);
					max = Math.max(max, check(i, j, n, candies));
					max = Math.max(max, check(i + 1, j, n, candies));
					swap(i, j, i + 1, j, candies);
				}
			}
		}
		System.out.println(max);
	}	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}
