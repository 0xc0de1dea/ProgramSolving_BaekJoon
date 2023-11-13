import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String str[] = s.split(" ");
		int n = Integer.parseInt(str[0]);
		int c = Integer.parseInt(str[1]);
		int crr[] = new int[c+1];
		int x=0;
		for(int i=0;i<n;i++){
			x = Integer.parseInt(br.readLine());
			for(int j=x; j<crr.length;j+=x){
				crr[j] = 1;
			}
		}
		int cnt = 0;
		for(int i=0;i<crr.length;i++){
			if(crr[i]==1){
				cnt+=1;
			}
		}
		System.out.println(cnt);

	}
}

//TEST!@#$%