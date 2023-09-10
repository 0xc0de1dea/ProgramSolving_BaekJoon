import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        int maxIdx = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());

            for (int j = 0; j < i; j++){
                if (arr[i] > arr[j]){
                    if (dp[i] < dp[j] + 1){
                        dp[i] = dp[j] + 1;

                        if (max < dp[i]){
                        max = dp[i];
                        maxIdx = i;
                        }
                    }
                }
            }
        }

        int tracking = max;

        while (tracking >= 0){
            if (dp[maxIdx] == tracking){
                stack.push(arr[maxIdx]);
                tracking--;
            }

            maxIdx--;
        }

        sb.append(max + 1).append('\n');

        while (!stack.empty())
            sb.append(stack.pop()).append(' ');

        System.out.println(sb);
    }
}