import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][][] blockShapes = {
            {{1,1,1,1}},

            {{1}
            ,{1}
            ,{1}
            ,{1}},

            {{1,1}
            ,{1,1}},

            {{1,0}
            ,{1,0}
            ,{1,1}},

            {{1,1,1}
            ,{1,0,0}},

            {{1,1}
            ,{0,1}
            ,{0,1}},

            {{0,0,1}
            ,{1,1,1}},

            {{0,1}
            ,{0,1}
            ,{1,1}},

            {{1,0,0}
            ,{1,1,1}},

            {{1,1}
            ,{1,0}
            ,{1,0}},

            {{1,1,1}
            ,{0,0,1}},

            {{1,0}
            ,{1,1}
            ,{0,1}},

            {{0,1,1}
            ,{1,1,0}},

            {{0,1}
            ,{1,1}
            ,{1,0}},

            {{1,1,0}
            ,{0,1,1}},

            {{1,1,1}
            ,{0,1,0}},

            {{0,1}
            ,{1,1}
            ,{0,1}},

            {{0,1,0}
            ,{1,1,1}},

            {{1,0}
            ,{1,1}
            ,{1,0}},
        };

        int maxNum = 0;

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for  (int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                maxNum = Math.max(maxNum, map[i][j]);
            }
        }

        int maxSum = 0;
        int r_size = 0;
        int c_size = 0;
        int subTotal = 0;
        int cnt = 0;

        for (int r = 0; r < n; r++){
            for (int c = 0; c < m; c++){
                for (int i = 0; i < 19; i++){
                    subTotal = 0;
                    cnt = 0;
                    r_size = blockShapes[i].length;
                    c_size = blockShapes[i][0].length;
                    
                    if (r + r_size > n || c + c_size > m) continue;

                    jump : for (int r2 = 0; r2 < r_size; r2++){
                        for (int c2 = 0; c2 < c_size; c2++){
                            if (blockShapes[i][r2][c2] == 1){
                                subTotal += map[r + r2][c + c2];
                                cnt++;

                                if ((subTotal + maxNum*(4 - cnt)) <= maxSum) break jump;
                            }
                        }
                    }
                    
                    maxSum = Math.max(maxSum, subTotal);
                }
            }
        }

        System.out.println(maxSum);
    }
}