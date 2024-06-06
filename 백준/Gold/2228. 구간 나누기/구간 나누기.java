import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

 public class Main {
    static int n, m;
    static int[] numbers;
    static int[][] dp;
    static final int INF = -0x3f3f3f3f;

    public static int dp(int nn, int mm){
        if (nn > n || mm > m){
            if (mm > m) return 0;
            else return INF;
        }

        if (dp[nn][mm] != INF) return dp[nn][mm];

        dp[nn][mm] = INF;

        for (int i = nn; i <= n; i++){
            for (int j = 2; j <= Math.max(n, 2); j++){
                dp[nn][mm] = Math.max(dp[nn][mm], numbers[i] - numbers[nn - 1] + dp(i + j, mm + 1));
                // if (nn == 0){
                //     dp[nn][mm] = Math.max(dp[nn][mm], numbers[i] + dp(i + j, mm + 1));
                // } else {
                //     if (j >= 2){
                //     }
                // }
            }
        }

        return dp[nn][mm];
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        numbers = new int[n + 1];
        dp = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++){
            numbers[i] = numbers[i - 1] + in.nextInt();
        }

        for (int i = 1; i <= m; i++){
            dp[0][i] = INF;
        }

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                dp[i][j] = dp[i - 1][j];

                if (j == 1){
                    dp[i][j] = Math.max(dp[i][j], numbers[i]);
                }

                for (int k = 2; k <= i; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[k - 2][j - 1] + numbers[i] - numbers[k - 1]);
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}