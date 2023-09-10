import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        long n = Long.parseLong(br.readLine());
        long sum = 0;

        for (long i = n - 2; i >= 1; i--)
            sum += i * (i + 1) / 2;

        sb.append(sum).append('\n').append(3);
        System.out.println(sb);
    }
}