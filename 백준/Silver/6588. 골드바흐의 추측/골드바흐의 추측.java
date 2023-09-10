import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX = 1000000;
	boolean[] isCheck;
	
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		isCheck = new boolean[MAX + 1];
		isCheck[1] = true;
		
		for (int i = 2; i <= (int)Math.sqrt(MAX); i++) {
			if (isCheck[i] == true) {
				continue;
			}
			
			for (int j = i * 2; j <= MAX; j += i) {
				isCheck[j] = true;
			}
		}
		
		while (true) {
			int n = Integer.parseInt(br.readLine());
			
			if (n == 0)
				break;
			
			for (int i = 2; i <= n / 2; i++) {
				if (isCheck[i] == false && isCheck[n - i] == false) {
					sb.append(n + " = " + i + " + " + (n - i)).append("\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}
