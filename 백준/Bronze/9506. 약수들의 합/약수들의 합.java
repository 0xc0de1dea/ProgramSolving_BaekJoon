import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = 0;
        
        while ((n = Integer.parseInt(br.readLine())) != -1){
            int sum = 0;
            ArrayList<Integer> candidates = new ArrayList<>();

            for (int i = 1; i <= n / 2; i++){
                if (n % i == 0){
                    sum += i;
                    candidates.add(i);
                }
            }

            if (sum == n){
                sb.append(n).append(" = ");

                for (int i = 0; i < candidates.size() - 1; i++)
                    sb.append(candidates.get(i)).append(" + ");

                sb.append(candidates.get(candidates.size() - 1));
            }
            else {
                sb.append(n).append(" is NOT perfect.");
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}