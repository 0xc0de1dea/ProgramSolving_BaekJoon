import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static double[][] graph;
    static double[][] dp;
    static int[][] axis;

    public static double tsp(int visit_bit, int curLoc){
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

            double tmp = graph[curLoc][i] + tsp(visit_bit, i);
            dp[visit_bit][curLoc] = Math.min(dp[visit_bit][curLoc], tmp);
        }

        return dp[visit_bit][curLoc];
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new double[n + 1][n + 1];
        dp = new double[1 << n][n + 1];
        axis = new int[n + 1][n + 1];
        StringTokenizer st;
        
        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j <= 1; j++){
                axis[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++){
            for (int j = 1 ; j <= n; j++){
                graph[i][j] = Math.sqrt(Math.pow(axis[i][0] - axis[j][0], 2) + Math.pow(axis[i][1] - axis[j][1], 2));
            }
        }

        double min = tsp(0, 1);
        System.out.println(min);
    }
}