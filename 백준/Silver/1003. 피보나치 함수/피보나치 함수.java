import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[41][2];
        dp[0][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= 40; i++){
            dp[i][0] += dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] += dp[i - 1][1] + dp[i - 2][1];
        }

        while (t-- > 0){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][0]).append(' ').append(dp[n][1]).append('\n');
        }

        System.out.println(sb);
    }
}