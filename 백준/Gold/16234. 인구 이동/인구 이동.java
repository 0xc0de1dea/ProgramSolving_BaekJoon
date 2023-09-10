import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l, r;
    static int[][] map;
    static int[][] isVisited;
    static int[] moved;
    static int[] dx = new int[] { 1, 0, -1, 0 };
    static int[] dy = new int[] { 0, 1, 0, -1 };

    public static void bfs(int sy, int sx, int id){
        int sum = map[sy][sx];
        int cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { sy, sx });
        isVisited[sy][sx] = id;

        while(q.size() != 0){
            int x = q.peek()[1];
            int y = q.poll()[0];
            
            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n){
                    int diff = Math.abs(map[ny][nx] - map[y][x]);

                    if (diff >= l && diff <= r && isVisited[ny][nx] == 0){
                        q.add(new int[] { ny, nx });
                        isVisited[ny][nx] = id;
                        sum += map[ny][nx];
                        cnt++;
                    }
                }
            }
        }

        moved[id] = sum / cnt;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        if (n == 1){
            System.out.println(day);
            return;
        }

        while (true){
            int id = 0;
            isVisited = new int[n][n];
            moved = new int[2501];

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (isVisited[i][j] == 0){
                        bfs(i, j, ++id);
                    }
                }
            }

            if (id == n * n){
                break;
            }

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    map[i][j] = moved[isVisited[i][j]];
                }
            }

            day++;
        }

        System.out.println(day);
    }  
}