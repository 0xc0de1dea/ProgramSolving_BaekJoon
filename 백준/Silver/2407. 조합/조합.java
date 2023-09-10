import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BigInteger[][] pascal = new BigInteger[101][101];

    public static BigInteger pascalTriangle(int n, int m){
        if (n == 0 || m == 0){
            return pascal[n][m] = new BigInteger("1");
        }

        if (pascal[n][m] != null){
            return pascal[n][m];
        }

        return pascal[n][m] = pascalTriangle(n, m - 1).add(pascalTriangle(n - 1, m));
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        System.out.println(pascalTriangle(n - m, m));
    }
}