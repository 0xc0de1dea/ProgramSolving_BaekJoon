import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int n, m, r;
    static int[][] matrixA;
    static int[][] matrixB;
    static StringBuilder sb = new StringBuilder();

    public static void rotate(int depth, int max_r, int max_c){
        int mod = 2 * (max_r + max_c - 2 * depth);
        int round_len = mod;
        int origin_r = depth;
        int origin_c = depth;
        int change_r = origin_r;
        int change_c = origin_c;

        while (round_len-- > 0){
            int remainder = r % mod;

            while (remainder > 0){
                if (change_c == depth || (change_r == max_r && change_c != max_c)){
                    if (change_r != max_r){
                        if (remainder >= max_r - change_r){
                            remainder -= max_r - change_r;
                            change_r = max_r;
                        }
                        else {
                            change_r += remainder;
                            remainder = 0;
                        }
                    }
                    else {
                        if (remainder >= max_c - change_c){
                            remainder -= max_c - change_c;
                            change_c = max_c;
                        }
                        else {
                            change_c += remainder;
                            remainder = 0;
                        }
                    }
                }
                else {
                    if (change_r != depth){
                        if (remainder >= change_r - depth){
                            remainder -= change_r - depth;
                            change_r = depth;
                        }
                        else {
                            change_r -= remainder;
                            remainder = 0;
                        }
                    }
                    else {
                        if (remainder >= change_c - depth){
                            remainder -= change_c - depth;
                            change_c = depth;
                        }
                        else {
                            change_c -= remainder;
                            remainder = 0;
                        }
                    }
                }
            }

            matrixB[change_r][change_c] = matrixA[origin_r][origin_c];

            if (origin_c == depth || (origin_r == max_r && origin_c != max_c)){
                if (origin_r != max_r){
                    origin_r++;
                    change_r = origin_r;
                    change_c = origin_c;
                }
                else {
                    origin_c++;
                    change_r = origin_r;
                    change_c = origin_c;
                }
            }
            else {
                if (origin_r != depth){
                    origin_r--;
                    change_r = origin_r;
                    change_c = origin_c;
                }
                else {
                    origin_c--;
                    change_r = origin_r;
                    change_c = origin_c;
                }
            }
        }
    }

    public static void result(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                sb.append(matrixB[i][j]).append(' ');
            }

            sb.append('\n');
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        matrixA = new int[n][m];
        matrixB = new int[n][m];
        
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++){
                matrixA[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int roopCnt = Math.min(n / 2, m / 2);

        for (int i = 0; i < roopCnt; i++){
            rotate(i, n - 1 - i, m - 1 - i);
        }

        result();
        System.out.println(sb);
    }
}