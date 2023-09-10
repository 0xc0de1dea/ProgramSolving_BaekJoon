import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[101][101];
        int[] dx = new int[] { 1, 0, -1, 0 };
        int[] dy = new int[] { 0, -1, 0, 1 };

        for (int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            ArrayList<int[]> dragoncurve = new ArrayList<>();
            dragoncurve.add(new int[] { y, x });
            dragoncurve.add(new int[] { y + dy[d], x + dx[d] });
            grid[y][x] = grid[y + dy[d]][x + dx[d]] = i;

            for (int j = 1; j <= g; j++){
                int x0 = dragoncurve.get(dragoncurve.size() - 1)[1]; // 회전중심 x좌표
                int y0 = dragoncurve.get(dragoncurve.size() - 1)[0]; // 회전중심 y좌표
                int x1 = 0; // 타겟 회전 전 x좌표
                int y1 = 0; // 타겟 회전 전 y좌표
                int x2 = 0; // 타겟 회전 후 x좌표
                int y2 = 0; // 타겟 회전 후 y좌표

                for (int k = dragoncurve.size() - 2; k >= 0; k--){
                    x1 = dragoncurve.get(k)[1];
                    y1 = dragoncurve.get(k)[0];
                    x2 = (y0 - y1) + x0; // 상대적 회전변환행렬 x좌표식
                    y2 = (x1 - x0) + y0; // 상대적 회전변환행렬 y좌표식
                    dragoncurve.add(new int[] { y2, x2 });
                    grid[y2][x2] = i;
                }
            }
        }

        int cnt = 0;

        for (int i = 1; i <= 100; i++){
            for (int j = 1; j <= 100; j++){
                if (grid[i][j] > 0 && grid[i - 1][j] > 0 && grid[i][j - 1] > 0 && grid[i - 1][j - 1] > 0){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}