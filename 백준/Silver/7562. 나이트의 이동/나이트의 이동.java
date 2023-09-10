import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int l;
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[][] chessboard;
    static boolean[][] isVisited;
    static int[] dirR = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] dirC = new int[] { 1, 2, 2, 1, -1, -2, -2, -1 };

    public static int bfs(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { r, c });
        isVisited[r][c] = true;
        int cnt = 0;

        while (q.size() != 0){
            r = q.peek()[0];
            c = q.peek()[1];
            q.remove();

            if (r == end[0] && c == end[1])
                break;

            for (int i = 0; i < 8; i++){
                int nr = r + dirR[i];
                int nc = c + dirC[i];

                if (nr < 0 || nr >= l || nc < 0 || nc >= l)
                    continue;

                if (!isVisited[nr][nc]){
                    q.add(new int[] { nr, nc });
                    isVisited[nr][nc] = true;
                    chessboard[nr][nc] = chessboard[r][c] + 1;
                    cnt = chessboard[nr][nc];

                    if (nr == end[0] && nc == end[1])
                        return cnt;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        start = new int[2];
        end = new int[2];
        StringTokenizer st;
        
        while (t-- > 0){
            l = Integer.parseInt(br.readLine());
            chessboard = new int[l][l];
            isVisited = new boolean[l][l];
            st = new StringTokenizer(br.readLine());
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());
            sb.append(bfs(start[0], start[1])).append('\n');
        }

        System.out.println(sb);
    }
}