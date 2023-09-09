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
        int k = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int num = 0;

        for (int i = 1; i <= n; i++){
            if (n % i == 0){
                num = i;
                cnt++;
            }

            if (cnt == k)
                break;
        }

        System.out.println(cnt == k ? num : 0);
    }
}