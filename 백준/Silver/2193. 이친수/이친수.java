import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n + 1][2];
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i <= n; i++){
            if (dp[i - 1][0] > 0){
                dp[i][0] += dp[i - 1][0];
                dp[i][1] += dp[i - 1][0];
            }

            if (dp[i - 1][1] > 0)
                dp[i][0] += dp[i - 1][1];
        }

        System.out.println(dp[n][0] + dp[n][1]);
    }
}