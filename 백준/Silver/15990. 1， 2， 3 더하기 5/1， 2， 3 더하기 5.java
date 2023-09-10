import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int n = 0;
        int mod = 1000000009;
        long[][] dp = new long[100001][3];
        dp[1][0] = 1;
        dp[2][1] = 1;
        dp[3][0] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;

        for (int i = 4; i <= 100000; i++){
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % mod;
            dp[i][1] = (dp[i - 2][0] + dp[i - 2][2]) % mod;
            dp[i][2] = (dp[i - 3][0] + dp[i - 3][1]) % mod;
        }

        while (t-- > 0){
            n = Integer.parseInt(br.readLine());
            sb.append(((dp[n][0] % mod + dp[n][1] % mod) + dp[n][2] % mod) % mod).append('\n');
        }

        System.out.println(sb);
    }
}