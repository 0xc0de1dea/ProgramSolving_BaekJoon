import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;

        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            int subMax = arr[i];

            for (int j = 0; j < i; j++){
                if (arr[i] > arr[j]){
                    subMax = Math.max(subMax, arr[i] + dp[j]);
                }
            }

            dp[i] = subMax;
            max = Math.max(max, subMax);
        }

        sb.append(max);
        System.out.println(sb);
    }
}