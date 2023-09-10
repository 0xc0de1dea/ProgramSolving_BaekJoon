import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        long ans = 1;

        while (b > 0){
            if ((b & 1) == 1) ans = ((ans % c) * (a % c)) % c;

            a = ((a % c) * (a % c)) % c;
            b = b >> 1;
        }

        System.out.println(ans);
    }
}