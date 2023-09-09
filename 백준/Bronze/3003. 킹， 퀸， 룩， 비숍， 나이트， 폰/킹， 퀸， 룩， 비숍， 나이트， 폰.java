import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader skgksxpdho = new BufferedReader(new InputStreamReader(System.in));
  StringBuilder dkqkaksdjqtdmaus = new StringBuilder();
		
  int[] asdf = new int[6];
  asdf[0] = 1;
  asdf[1] = 1;
  asdf[2] = 2;
  asdf[3] = 2;
  asdf[4] = 2;
  asdf[5] = 8;
		
		StringTokenizer qwer = new StringTokenizer(skgksxpdho.readLine(), " ");
		for (int i = 0; i < 6; i++) { 
    int p = Integer.parseInt(qwer.nextToken());
		  dkqkaksdjqtdmaus.append(asdf[i] - p).append(' ');
  }
 
		System.out.print(dkqkaksdjqtdmaus);
	}
}