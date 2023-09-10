import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static long pow(long x, int exp, int mod){
        long result = 1;

        while (exp > 0){
            if ((exp & 1) == 1) result = (result * x) % mod;

            x = (x * x) % mod;
            exp = exp >> 1;
        }

        return result;
    }

    public static long gcd(long n, long s){
        while (true){
            long r = n % s;

            if (r == 0) return s;

            n = s;
            s = r;
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int mod = 1000000007;

        int m = Integer.parseInt(br.readLine());
        long sum = 0;
        StringTokenizer st;

        while (m-- > 0){
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long s = Long.parseLong(st.nextToken());
            long gcd = gcd(n, s);
            n /= gcd;
            s /= gcd;
            sum += (s * pow(n, mod - 2, mod)) % mod;
        }

        System.out.println(sum % mod);
    }
}