import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public int gcd(int a, int b) {
		int r;
		
		do {
			r = a % b;
			a = b;
			b = r;
		}while (r != 0);
		return a;
	}
	
	public int lcm(int a, int b, int gcd) {
		return a * b / gcd;
	}
	
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int gcd = gcd(a, b);
		int lcm = lcm(a, b, gcd);
		
		System.out.print(gcd + "\n" + lcm);
	}	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}
