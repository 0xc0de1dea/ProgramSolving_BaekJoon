import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        boolean[][] isExisted = new boolean[n][n];
        int cnt = 0;

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int c = 0; c < n; c++){
            int cur = map[0][c];

            for (int r = 1; r < n; r++){
                if (map[r][c] == cur - 1){
                    boolean check = true;

                    for (int dr = 0; dr < l; dr++){
                        isExisted[r + dr][c] = true;

                        if (r + l > n || map[r][c] != map[r + dr][c]){
                            check = false;
                            break;
                        }
                    }

                    if (check){
                        r += l - 1;
                        cur = map[r][c];
                    }
                    else {
                        cnt--;
                        //System.out.println(String.format("[세로핥기][-1] r : %d, c : %d", r, c));
                        break;
                    }
                }
                else if (map[r][c] == cur + 1){
                    boolean check = true;

                    for (int dr = 1; dr <= l; dr++){

                        if (r - l < 0 || map[r - 1][c] != map[r - dr][c] || isExisted[r - l][c]){
                            check = false;
                            break;
                        }
                    }

                    if (check){
                        cur = map[r][c];
                    }
                    else {
                        cnt--;
                        //System.out.println(String.format("[세로핥기][+1] r : %d, c : %d", r, c));
                        break;
                    }
                }
                else if (map[r][c] > cur + 1 || map[r][c] < cur - 1){
                    cnt--;
                    //System.out.println(String.format("[세로핥기][2] r : %d, c : %d", r, c));
                    break;
                }
            }

            cnt++;
        }

        isExisted = new boolean[n][n];

        for (int r = 0; r < n; r++){
            int cur = map[r][0];

            for (int c = 1; c < n; c++){
                if (map[r][c] == cur - 1){
                    boolean check = true;

                    for (int dc = 0; dc < l; dc++){
                        isExisted[r][c + dc] = true;

                        if (c + l > n || map[r][c] != map[r][c + dc]){
                            check = false;
                            break;
                        }
                    }

                    if (check){
                        c += l - 1;
                        cur = map[r][c];
                    }
                    else {
                        cnt--;
                        //System.out.println(String.format("[가로핥기][-1] r : %d, c : %d", r, c));
                        break;
                    }
                }
                else if (map[r][c] == cur + 1){
                    boolean check = true;

                    for (int dc = 1; dc <= l; dc++){

                        if (c - l < 0 || map[r][c - 1] != map[r][c - dc] || isExisted[r][c - l]){
                            check = false;
                            break;
                        }
                    }

                    if (check){
                        cur = map[r][c];
                    }
                    else {
                        cnt--;
                        //System.out.println(String.format("[가로핥기][+1] r : %d, c : %d", r, c));
                        break;
                    }
                }
                else if (map[r][c] > cur + 1 || map[r][c] < cur - 1){
                    cnt--;
                    //System.out.println(String.format("[가로핥기][2] r : %d, c : %d", r, c));
                    break;
                }
            }

            cnt++;
        }

        System.out.println(cnt);
    }
}