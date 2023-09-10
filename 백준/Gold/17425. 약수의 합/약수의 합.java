import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX = 1000000;
	
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		long[] fx = new long[MAX + 1];
		long[] gx = new long[MAX + 1];
		
		for (int i = 1; i <= MAX; i++) {
			for (int j = 1; i * j <= MAX; j++) {
				fx[i * j] += i;
			}
		}
		
		for (int i = 1; i <= MAX; i++) {
			gx[i] += gx[i - 1] + fx[i];
		}
		
		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(gx[n] + "\n");
		}
        System.out.print(sb);
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}