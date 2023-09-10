import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        int[] ans = new int[3];
        ans[0] = Math.abs(liquid[n - 1] + liquid[0]);
        ans[1] = liquid[0];
        ans[2] = liquid[n - 1];
        int l = 0, r = n - 1;

        while (r - l > 1){
            if (Math.abs(liquid[r] + liquid[l + 1]) < Math.abs(liquid[r - 1] + liquid[l])){
                if (Math.abs(liquid[r] + liquid[++l]) < ans[0]){
                    ans[0] = Math.abs(liquid[r] + liquid[l]);
                    ans[1] = liquid[l];
                    ans[2] = liquid[r];
                }
            }
            else {
                if (Math.abs(liquid[--r] + liquid[l]) < ans[0]){
                    ans[0] = Math.abs(liquid[r] + liquid[l]);
                    ans[1] = liquid[l];
                    ans[2] = liquid[r];
                }
            }
        }

        if (l - 1 >= 0){
            if (Math.abs(liquid[l - 1] + liquid[l]) < ans[0]){
                ans[1] = liquid[l - 1];
                ans[2] = liquid[l];
            }
        }

        if (r + 1 < n){
            if (Math.abs(liquid[r] + liquid[r + 1]) < ans[0]){
                ans[1] = liquid[r];
                ans[2] = liquid[r + 1];
            }
        }

        System.out.println(ans[1] + " " + ans[2]);
    }
}