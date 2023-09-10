import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int m = Integer.parseInt(br.readLine());
        int set = 0;
        String operator = null;
        int x = 0;

        while (m-- > 0){
            st = new StringTokenizer(br.readLine());
            operator = st.nextToken();
            
            if (st.hasMoreTokens())
                x = Integer.parseInt(st.nextToken());
            
            switch (operator){
                case "add": set |= 1 << x - 1;
                            break;

                case "remove": set &= ~(1 << x - 1);
                            break;

                case "check": sb.append((((set >> x - 1) & 1) == 1 ? 1 : 0) + "\n");
                            break;
                
                case "toggle": set ^= 1 << x - 1;
                            break;

                case "all": set = (1 << 20) - 1;
                            break;

                case "empty": set = 0;
                            break;
            }
        }

        System.out.println(sb);
    }
}