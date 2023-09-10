import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class RobotCleaner{
    public int r, c;
    private int dir;

    public RobotCleaner(int r, int c, int d){
        this.r = r;
        this.c = c;
        dir = d;
    }
}

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] dr = new int[] { -1, 0, 1, 0 };
        int[] dc = new int[] { 0, 1, 0, -1 };

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[][] room = new int[n][m];
        int cnt = 0;

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < m; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            if (room[r][c] == 0){
                room[r][c] = -1;
                cnt++;
            }

            boolean check = false;

            for (int i = 0; i < 4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m){
                    if (room[nr][nc] == 0){
                        check = true;
                        break;
                    }
                }
            }

            if (!check){
                if (room[r - dr[d]][c - dc[d]] != 1){
                    r -= dr[d];
                    c -= dc[d];
                    continue;
                }
                else {
                    break;
                }
            }
            else {
                d = (d - 1 + 4) % 4;

                if (room[r + dr[d]][c + dc[d]] == 0){
                    r += dr[d];
                    c += dc[d];
                }
            }
        }

        System.out.println(cnt);
    }
}