import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void operatorOne(int[][] oriMat, int[][] trgMat, int sx, int sy, int ex, int ey){
        for (int i = sy; i <= ey; i++){
            for (int j = sx; j <= ex; j++){
                trgMat[ey - (i - sy)][j] = oriMat[i][j];
            }
        }
    }

    public static void operatorTwo(int[][] oriMat, int[][] trgMat, int sx, int sy, int ex, int ey){
        for (int i = sy; i <= ey; i++){
            for (int j = sx; j <= ex; j++){
                trgMat[i][ex - (j - sx)] = oriMat[i][j];
            }
        }
    }

    public static void operatorThree(int[][] oriMat, int[][] trgMat, int sx, int sy, int ex, int ey){
        double x0 = (sx + ex) / (double)2;
        double y0 = (sy + ey) / (double)2;
        int x2 = 0;
        int y2 = 0;

        for (int y1 = sy; y1 <= ey; y1++){
            for (int x1 = sx; x1 <= ex; x1++){
                x2 = (int)(y0 - y1 + x0);
                y2 = (int)(x1 - x0 + y0);
                trgMat[y2][x2] = oriMat[y1][x1];
            }
        }
    }

    public static void operatorFour(int[][] oriMat, int[][] trgMat, int sx, int sy, int ex, int ey){
        double x0 = (sx + ex) / (double)2;
        double y0 = (sy + ey) / (double)2;
        int x2 = 0;
        int y2 = 0;

        for (int y1 = sy; y1 <= ey; y1++){
            for (int x1 = sx; x1 <= ex; x1++){
                x2 = (int)(y1 - y0 + x0);
                y2 = (int)(x0 - x1 + y0);
                trgMat[y2][x2] = oriMat[y1][x1];
            }
        }
    }

    public static void operatorFive(int[][] oriMat, int[][] trgMat, int l, int sx, int sy, int ex, int ey){
        int groupTotal = (1 << n) / (1 << l) - 1;
        int groupY = sy / (1 << l);

        for (int y = sy; y <= ey; y++){
            for (int x = sx; x <= ex; x++){
                trgMat[((groupTotal - groupY) * (1 << l)) + (y - sy)][x] = oriMat[y][x];
            }
        }
    }

    public static void operatorSix(int[][] oriMat, int[][] trgMat, int l, int sx, int sy, int ex, int ey){
        int groupTotal = (1 << n) / (1 << l) - 1;
        int groupX = sx / (1 << l);
        
        for (int y = sy; y <= ey; y++){
            for (int x = sx; x <= ex; x++){
                trgMat[y][((groupTotal - groupX) * (1 << l)) + (x - sx)] = oriMat[y][x];
            }
        }
    }

    public static void operatorSeven(int[][] oriMat, int[][] trgMat, int l, int sx, int sy, int ex, int ey){
        int groupTotal = (1 << n) / (1 << l) - 1;
        int groupX = sx / (1 << l);
        int groupY = sy / (1 << l);
        double x0 = groupTotal / (double)2;
        double y0 = x0;
        int x2 = ((int)(y0 - groupY + x0)) * (1 << l);
        int y2 = ((int)(groupX - x0 + y0)) * (1 << l);
        
        for (int y1 = sy; y1 <= ey; y1++){
            for (int x1 = sx; x1 <= ex; x1++){
                trgMat[y2 + (y1 - sy)][x2 + (x1 - sx)] = oriMat[y1][x1];
            }
        }
    }

    public static void operatorEight(int[][] oriMat, int[][] trgMat, int l, int sx, int sy, int ex, int ey){
        int groupTotal = (1 << n) / (1 << l) - 1;
        int groupX = sx / (1 << l);
        int groupY = sy / (1 << l);
        double x0 = groupTotal / (double)2;
        double y0 = x0;
        int x2 = ((int)(groupY - y0 + x0)) * (1 << l);
        int y2 = ((int)(x0 - groupX + y0)) * (1 << l);
        
        for (int y1 = sy; y1 <= ey; y1++){
            for (int x1 = sx; x1 <= ex; x1++){
                trgMat[y2 + (y1 - sy)][x2 + (x1 - sx)] = oriMat[y1][x1];
            }
        }
    }

    public static void operatorMain(int[][] oriMat, int[][] trgMat, int depth, int l, int sx, int sy, int ex, int ey, int operType){
        if (depth < n){
            int mx = (sx + ex) / 2;
            int my = (sy + ey) / 2;
            operatorMain(oriMat, trgMat, depth + 1, l, sx, sy, mx, my, operType);
            operatorMain(oriMat, trgMat, depth + 1, l, mx + 1, sy, ex, my, operType);
            operatorMain(oriMat, trgMat, depth + 1, l, sx, my + 1, mx, ey, operType);
            operatorMain(oriMat, trgMat, depth + 1, l, mx + 1, my + 1, ex, ey, operType);
        }
        else {
            switch (operType){
                case 1:
                    operatorOne(oriMat, trgMat, sx, sy, ex, ey);
                    break;

                case 2:
                    operatorTwo(oriMat, trgMat, sx, sy, ex, ey);
                    break;

                case 3:
                    operatorThree(oriMat, trgMat, sx, sy, ex, ey);
                    break;

                case 4:
                    operatorFour(oriMat, trgMat, sx, sy, ex, ey);
                    break;

                case 5:
                    operatorFive(oriMat, trgMat, l, sx, sy, ex, ey);
                    break;

                case 6:
                    operatorSix(oriMat, trgMat, l, sx, sy, ex, ey);
                    break;

                case 7:
                    operatorSeven(oriMat, trgMat, l, sx, sy, ex, ey);
                    break;

                case 8:
                    operatorEight(oriMat, trgMat, l, sx, sy, ex, ey);
                    break;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[][][] matrix = new int[2][1 << n][1 << n];

        for (int i = 0; i < 1 << n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 1 << n; j++){
                matrix[0][i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int oriptr = 0;
        int trgptr = 1;

        while (r-- > 0){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            operatorMain(matrix[oriptr], matrix[trgptr], l, l, 0, 0, (1 << n) - 1, (1 << n) - 1, k);

            int tmp = oriptr;
            oriptr = trgptr;
            trgptr = tmp; 
        }

        for (int i = 0; i < 1 << n; i++){
            for (int j = 0; j < 1 << n; j++){
                sb.append(matrix[oriptr][i][j]).append(' ');
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}