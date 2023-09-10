import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] rowSum = new int[n + 1];
        int[] colSum = new int[n + 1];
        int total = 0;
        StringTokenizer st;
        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            
            for (int j = 1; j <= n; j++){
                int tmp = Integer.parseInt(st.nextToken());
                rowSum[i] += tmp;
                colSum[j] += tmp; 
                total += tmp;
            }
        }

        for (int i = 1; i < (1 << n); i++){
            int subMin = total;
            for (int j = 0; j < n; j++){
                if ((i & (1 << j)) > 0){
                    subMin -= rowSum[j + 1] + colSum[j + 1];
                }
            }
            
            //    System.out.println(Integer.toBinaryString(i) + " " + Math.abs(total - 2 * sum) + " " + sum);
            min = Math.min(min, Math.abs(subMin));
        }

        System.out.println(min);
    }
}