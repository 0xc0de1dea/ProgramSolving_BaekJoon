import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 2; i <= n; i += 2){
            dp[i] = 3 * dp[i - 2];

            for (int j = 4; j <= i; j += 2)
                dp[i] += 2 * dp[i - j];
        }

        System.out.println(dp[n]);
    }
}