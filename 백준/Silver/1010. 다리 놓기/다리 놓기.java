import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp = new int[30][30];

    public static int nCm(int n, int m){
        if (n == 0 || m == 0 || m == n){
            return 1;
        }

        if (dp[n][m] != 0){
            return dp[n][m];
        }

        return dp[n][m] = nCm(n - 1, m - 1) + nCm(n - 1, m);
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        
        while (t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int cnt = nCm(m, n);
            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
    }
}