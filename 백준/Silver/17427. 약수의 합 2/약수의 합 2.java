import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		long sum = 0;
		
		for (int i = 1; i <= n; i++) {
			sum += n / i * i;
		}
		
		System.out.print(sum);
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}
