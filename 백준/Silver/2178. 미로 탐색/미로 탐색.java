import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] graph;
    static boolean[][] isVisited;
    static int[] dirX = new int[] { 1, 0, -1, 0 };
    static int[] dirY = new int[] { 0, 1, 0, -1 };

    public static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { y, x });
        isVisited[y][x] = true;

        while (q.size() != 0){
            int[] cur = q.remove();

            for (int i = 0; i < 4; i++){
                int nextX = cur[1] + dirX[i];
                int nextY = cur[0] + dirY[i];

                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;

                if (graph[nextY][nextX] != 0 && !isVisited[nextY][nextX]){
                    graph[nextY][nextX] = graph[cur[0]][cur[1]] + 1;
                    isVisited[nextY][nextX] = true;
                    q.add(new int[] { nextY, nextX });
                }
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        isVisited = new boolean[n][m];

        for (int i = 0; i < n; i++){
            String[] tmp = br.readLine().split("");

            for (int j = 0; j < m; j++)
                graph[i][j] = Integer.parseInt(tmp[j]);
        }

        bfs(0, 0);
        System.out.println(graph[n - 1][m - 1]);
    }
}