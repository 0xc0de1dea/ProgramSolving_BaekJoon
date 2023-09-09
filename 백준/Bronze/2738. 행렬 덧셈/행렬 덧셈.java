import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++)
                matrix[i][j] += Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++)
                sb.append(matrix[i][j]).append(' ');

            sb.append('\n');
        }
        
        System.out.println(sb);
    }
}