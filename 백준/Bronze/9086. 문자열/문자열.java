import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            String input = br.readLine();
            sb.append(input.charAt(0)).append(input.charAt(input.length() - 1)).append('\n');
        }

        System.out.println(sb);
    }
}