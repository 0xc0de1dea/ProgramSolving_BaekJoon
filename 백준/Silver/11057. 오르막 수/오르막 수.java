import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][10];
        int cnt = 0;
        
        for (int i = 0; i < 10; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++){
            for (int j = 0; j < 10; j++){
                for (int k = j; k < 10; k++){
                    dp[i][j] += dp[i - 1][k];
                }

                dp[i][j] %= 10007;
            }
        }

        for (int i = 0; i < 10; i++)
            cnt += dp[n][i];
        
        cnt %= 10007;

        System.out.println(cnt);
    }
}