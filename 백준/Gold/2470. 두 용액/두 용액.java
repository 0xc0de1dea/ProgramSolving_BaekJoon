import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] liquid = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquid);

        int[] ans = new int[3];
        ans[0] = Integer.MAX_VALUE;
        ans[1] = liquid[0];
        ans[2] = liquid[n - 1];
        int l = 0, r = n - 1;

        while (r > l){
            int diff = liquid[r] + liquid[l];
            int abs = Math.abs(diff);

            if (ans[0] > abs){
                ans[0] = abs;
                ans[1] = liquid[l];
                ans[2] = liquid[r];
            }

            if (diff > 0) r--;
            else l++;
        }

        System.out.println(ans[1] + " " + ans[2]);
    }
}