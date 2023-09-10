import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long d = gcd(a, b);
        sb.append(a * b / d);
		System.out.print(sb);
	}

	public static long gcd(long a, long b) {
		while (b != 0) {
			long r = a % b;
			a = b;
			b = r;
		}
        
		return a;
	}
}//!@##$)($&*($)%^*()_+)