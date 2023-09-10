import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int MAX = 1234567;

        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n + 1][3];
        int[][] dp = new int[n + 1][3];
        int ans = MAX;

        for (int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < 3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (i == j){
                    dp[1][j] = cost[1][j];
                }
                else {
                    dp[1][j] = MAX;
                }
            }

            for (int j = 2; j <= n; j++){
                dp[j][0] = cost[j][0] + Math.min(dp[j - 1][1], dp[j - 1][2]);
                dp[j][1] = cost[j][1] + Math.min(dp[j - 1][0], dp[j - 1][2]);
                dp[j][2] = cost[j][2] + Math.min(dp[j - 1][0], dp[j - 1][1]);
            }

            for (int j = 0; j < 3; j++){
                if (i == j) continue;

                ans = Math.min(ans, dp[n][j]);
            }
        }

        System.out.println(ans);
    }
}