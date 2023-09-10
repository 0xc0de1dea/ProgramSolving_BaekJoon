import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	int[] dwarf = new int[9];
	
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		
		for (int i = 0; i < 9; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
			sum += dwarf[i];
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (sum - dwarf[i] - dwarf[j] == 100) {
					dwarf[i] = 0;
					dwarf[j] = 0;
					Arrays.sort(dwarf);
					
					for (int k = 2; k < 9; k++) {
						sb.append(dwarf[k]).append("\n");
					}
					System.out.print(sb);
					return;
				}
			}
		}
	}	
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		new Main().solution();
	}
}
