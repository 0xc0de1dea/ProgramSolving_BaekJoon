import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < n; i++) {
			int divisor = Integer.parseInt(st.nextToken());

			if (divisor < min)
				min = divisor;
			
			if (divisor > max)
				max = divisor;
		}
		
		System.out.print(min * max);
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}
