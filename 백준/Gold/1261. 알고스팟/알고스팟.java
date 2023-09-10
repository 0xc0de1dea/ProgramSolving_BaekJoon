import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int inf = 10041004;
        int[] dirRow = new int[] { 0, 1, 0, -1 };
        int[] dirCol = new int[] { 1, 0, -1, 0 };

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] maze = new int[n][m];

        for (int i = 0; i < n; i++){
            String[] tmp = br.readLine().split("");

            for (int j = 0; j < m; j++){
                maze[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        int[][] broken = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m ; j++){
                broken[i][j] = inf;
            }
        }

        broken[0][0] = 0;
        boolean[][] isVisited = new boolean[n][m];
        Deque<int[]> deq = new LinkedList<>();
        deq.addFirst(new int[] { 0, 0 });

        while (deq.size() != 0){
            int curRow = deq.peekFirst()[0];
            int curCol = deq.pollFirst()[1];
            isVisited[curRow][curCol] = true;
            
            for (int i = 0; i < 4; i++){
                int nextRow = curRow + dirRow[i];
                int nextCol = curCol + dirCol[i];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
                
                int cost = maze[nextRow][nextCol] == 1 ? 1 : 0;

                if (!isVisited[nextRow][nextCol]){
                    if (broken[curRow][curCol] + cost < broken[nextRow][nextCol]){
                        broken[nextRow][nextCol] = broken[curRow][curCol] + cost;
                        
                        if (cost == 0)
                            deq.addFirst(new int[] { nextRow, nextCol });
                        else
                            deq.addLast(new int[] { nextRow, nextCol });
                    }
                }
            }
        }

        System.out.println(broken[n - 1][m - 1]);
    }
}