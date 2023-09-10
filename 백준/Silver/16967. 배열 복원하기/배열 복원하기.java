import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[][] arrB = new int[h + x][w + y];

        for (int i = 0; i < h + x; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < w + y; j++){
                arrB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                if (i >= x && j >= y){
                    arrB[i][j] -= arrB[i - x][j - y];
                    sb.append(arrB[i][j]).append(' ');
                }
                else {
                    sb.append(arrB[i][j]).append(' ');
                }
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}