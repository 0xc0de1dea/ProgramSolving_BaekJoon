import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][][] dp = new int[2][2][3];
        StringTokenizer st;
        
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < 3; j++){
                int tmp = Integer.parseInt(st.nextToken());
                dp[0][1][j] = Integer.MAX_VALUE;
                dp[1][1][j] = Integer.MIN_VALUE;
                int stop = j == 0 ? 2 : (j == 1 ? -1 : 0);
                
                for (int k = 0; k < 3; k++){
                    if (k == stop) continue;

                    dp[0][1][j] = Math.min(dp[0][1][j], tmp + dp[0][0][k]);
                    dp[1][1][j] = Math.max(dp[1][1][j], tmp + dp[1][0][k]);
                }
            }

            for (int j = 0; j < 3; j++){
                dp[0][0][j] = dp[0][1][j];
                dp[1][0][j] = dp[1][1][j];
            }
        }

        System.out.print(Math.max(Math.max(dp[1][0][0], dp[1][0][1]), dp[1][0][2]) + " " + Math.min(Math.min(dp[0][0][0], dp[0][0][1]), dp[0][0][2]));
    }
}