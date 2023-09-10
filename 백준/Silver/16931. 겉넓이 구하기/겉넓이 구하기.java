import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] dx = new int[] { 0, 1, 0, -1 };
        int[] dy = new int[] { -1, 0, 1, 0 };

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] space = new int[n + 2][m + 2];

        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++){
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 2 * (n * m);

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                for (int k = 0; k < 4; k++){
                    if (space[i][j] > space[i + dy[k]][j + dx[k]]){
                        cnt += space[i][j] - space[i + dy[k]][j + dx[k]];
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}