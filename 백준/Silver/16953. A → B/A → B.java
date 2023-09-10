import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int cnt = 1;

        while (true){
            if (a == b){
                System.out.println(cnt);
                break;
            }
            else if (a > b){
                System.out.println(-1);
                break;
            }

            if (b % 2 == 0){
                b /= 2;
                cnt++;
            }
            else if (b % 10 == 1){
                b /= 10;
                cnt++;
            }
            else {
                System.out.println(-1);
                break;
            }
        }
    }
}