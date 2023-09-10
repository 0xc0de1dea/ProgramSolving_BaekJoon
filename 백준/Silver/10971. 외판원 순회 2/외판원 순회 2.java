import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] graph;
    static int[][] dp;

    public static int tsp(int visit_bit, int curLoc){
        visit_bit |= 1 << curLoc - 1;

        if (visit_bit == (1 << n) - 1){
            if (graph[curLoc][1] != 0)
                return graph[curLoc][1];
            else
                return 10041004;
        }

        if (dp[visit_bit][curLoc] != 0)
            return dp[visit_bit][curLoc];

        dp[visit_bit][curLoc] = 10041004;

        for (int i = 1; i <= n; i++){
            if (curLoc == i || (visit_bit & (1 << i - 1)) != 0 || graph[curLoc][i] == 0) continue;

            int tmp = graph[curLoc][i] + tsp(visit_bit, i);
            dp[visit_bit][curLoc] = Math.min(dp[visit_bit][curLoc], tmp);
        }

        return dp[visit_bit][curLoc];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];
        dp = new int[1 << n][n + 1];
        StringTokenizer st;
        
        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            
            for (int j = 1; j <= n; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = tsp(0, 1);
        System.out.println(min);
    }
}