import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n][2];
        int max;
        dp[0][0] = dp[0][1] = max = sequence[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(sequence[i], dp[i - 1][0] + sequence[i]);
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + sequence[i]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(max);
    }
}