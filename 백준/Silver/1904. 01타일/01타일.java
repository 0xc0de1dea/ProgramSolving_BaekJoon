import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        int mod = 15746;

        for (int i = 2; i <= n; i++)
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;

        System.out.println(dp[n]);
    }
}