import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        long ans = 0;

        for (int i = 0; i < 5; i++){
            long tmp = Long.parseLong(st.nextToken());
            ans += tmp * tmp;
        }

        System.out.println(ans % 10);
    }
}