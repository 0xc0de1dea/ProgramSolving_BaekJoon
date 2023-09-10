import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int m, n;
    static int[][] box;
    static boolean[][] isVisited;
    static int[] dirR = new int[] { 0, 1, 0, -1 };
    static int[] dirC = new int[] { 1, 0, -1, 0 };

    public static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        int day = -1;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (box[i][j] == 1){
                    q.add(new int[] { i, j });
                    isVisited[i][j] = true;
                }
            }
        }

        while (q.size() != 0){
            Queue<int[]> q2 = new LinkedList<>();

            while (q.size() != 0){
                q2.add(q.remove());
            }

            while(q2.size() != 0){
                int[] curQ = q2.remove();
                int curR = curQ[0];
                int curC = curQ[1];

                for (int i = 0; i < 4; i++){
                    int nextR = curR + dirR[i];
                    int nextC = curC + dirC[i];

                    if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m)
                        continue;

                    if (box[nextR][nextC] != -1 && !isVisited[nextR][nextC]){
                        q.add(new int[] { nextR, nextC });
                        isVisited[nextR][nextC] = true;
                    }
                }
            }

            day++;
        }

        jump : for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (!isVisited[i][j] && box[i][j] != -1){
                    day = -1;
                    break jump;
                }
            }
        }
                    
        return day;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        box = new int[n][m];
        isVisited = new boolean[n][m];
        
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++)
                box[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs());
    }
}