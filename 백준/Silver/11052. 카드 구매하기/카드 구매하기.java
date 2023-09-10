import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cost = new int[n + 1];
        int[] dp = new int[n + 1];
        int max = 0;

        for (int i = 1; i <= n; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++){
            int tmp = i - 1;
            dp[n - i] = cost[i];

            for (int j = n - 1; j > n - i; j--){
                dp[n - i] = Math.max(dp[n - i], dp[j] + cost[tmp]);
                tmp--;
            }
        }

        System.out.println(dp[0]);
    }
}