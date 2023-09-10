import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			int n = 0;
			String input = br.readLine();
			
			if (input != null)
				n = Integer.parseInt(input);
				
			int target = 0;
			
			if (input == null)
				break;
			
			for (int i = 1; ; i++) {
				target = (target * 10) + 1;
				target = target % n;
				
				if (target == 0) {
					sb.append(i+"\n");
					break;
				}
			}
		}
		
		System.out.println(sb);
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}
