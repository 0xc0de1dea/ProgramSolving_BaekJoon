import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] graph;
    static int uniqnum = 0;
    static ArrayList<Integer> houseSize = new ArrayList<>();
    static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static int bfs(int row, int col){
        Queue<Integer> queue = new LinkedList<>();
        queue.add((row << 5) + col);
        graph[row][col] = uniqnum;
        int cnt = 1;

        while (queue.size() != 0){
            int cur = queue.remove();
            int curCol = cur & 31;
            int curRow = (cur >> 5) & 31;

            for (int i = 0; i < 4; i++){
                int newRow = curRow + dir[i][0];
                int newCol = curCol + dir[i][1];

                if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n)
                    continue;

                if (graph[newRow][newCol] == -1){
                    queue.add((newRow << 5) + newCol);
                    graph[newRow][newCol] = uniqnum;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for (int i = 0; i < n; i++){
            String[] input = br.readLine().split("");

            for (int j = 0; j < n; j++){
                int tmp = Integer.parseInt(input[j]);

                if (tmp == 0)
                    graph[i][j] = 0;
                else
                    graph[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (graph[i][j] == -1){
                    uniqnum++;
                    houseSize.add(bfs(i, j));
                }
            }
        }

        sb.append(uniqnum).append('\n');
        houseSize.sort(Comparator.naturalOrder());

        for (int e : houseSize)
            sb.append(e).append('\n');

        System.out.println(sb);
    }
}