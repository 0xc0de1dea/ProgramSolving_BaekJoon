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
        int k = Integer.parseInt(st.nextToken());
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int elaspedTime = 0;
        boolean check = false;

        while (m-- > 0){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            min = Math.min(min, t);
            sum += r;
            elaspedTime++;

            if (sum > k && !check){
                sb.append(elaspedTime).append(' ').append(min);
                check = true;
            }
        }

        if (check){
            System.out.println(sb);
        }
        else {
            System.out.println(-1);
        }
    }
}