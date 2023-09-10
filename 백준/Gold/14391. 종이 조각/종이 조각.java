import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] paper = new int[n][m];
        int max = 0;

        for (int i = 0; i < n; i++){
            String tmp = br.readLine();

            for (int j = 0; j < m; j++){
                paper[i][j] = Integer.parseInt(tmp.substring(j, j + 1));
            }
        }

        for (int i = 0; i < 1 << n * m; i++){
            int sum = 0;
            
            for (int r = 0; r < n; r++){
                int subSum = 0;

                for (int c = 0; c < m; c++){
                    int loc = r * m + c;

                    if ((i & 1 << loc) != 0){
                        subSum *= 10;
                        subSum += paper[r][c];
                    }
                    else{
                        sum += subSum;
                        subSum = 0;
                    }
                }

                sum += subSum;
            }

            for (int c = 0; c < m; c++){
                int subSum = 0;

                for (int r = 0; r < n; r++){
                    int loc = r * m + c;

                    if ((i & 1 << loc) == 0){
                        subSum *= 10;
                        subSum += paper[r][c];
                    }
                    else{
                        sum += subSum;
                        subSum = 0;
                    }
                }

                sum += subSum;
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}