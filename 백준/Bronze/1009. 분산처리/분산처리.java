import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        while (t-- > 0){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long ans = a;
            
            b = b % 4 + 4;

            for (long i = 2; i <= b; i++)
                ans = (ans * a) % 10;

            if (ans == 0)
                ans = 10;
                
            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }
}