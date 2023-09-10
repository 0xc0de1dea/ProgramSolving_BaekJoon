import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int[][] map;
    static int[] dx = new int[] { 1, 0, -1, 0 };
    static int[] dy = new int[] { 0, 1, 0, -1 };

    public static void bfs(int id, int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { y, x });
        map[y][x] = id;

        while (!q.isEmpty()){
            int cx = q.peek()[1];
            int cy = q.poll()[0];

            for (int i = 0; i < 4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n){
                    if (map[ny][nx] == -1){
                        q.add(new int[] { ny, nx });
                        map[ny][nx] = id;
                    }
                }
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            map = new int[n][m];

            for (int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = -1;
            }

            int cnt = 0;
            
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    if (map[i][j] == -1){
                        bfs(++cnt, j, i);
                    }
                }
            }

            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
    }
}