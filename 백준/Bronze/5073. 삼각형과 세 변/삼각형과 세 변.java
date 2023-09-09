import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true){
            int max = 0;
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            max = Math.max(max, a);

            if (a == 0){
                break;
            }

            int b = Integer.parseInt(st.nextToken());
            max = Math.max(max, b);
            int c = Integer.parseInt(st.nextToken());
            max = Math.max(max, c);

            if (2 * max >= a + b + c){
                sb.append("Invalid").append('\n');
                continue;
            }

            if (a == b && b == c){
                sb.append("Equilateral").append('\n');
            }
            else if (a == b || b == c || a == c){
                sb.append("Isosceles").append('\n');
            }
            else {
                sb.append("Scalene").append('\n');
            }
        }

        System.out.println(sb);
    }
}