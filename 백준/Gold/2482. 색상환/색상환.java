/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        final int MOD = (int)1e9 + 3;
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] dp = new int[k + 1][n + 1];
        int[][] one = new int[k + 1][n + 1];
        one[1][0] = 1;
        
        for (int i = 1; i <= k; i++){
            for (int j = 1; j <= n; j++){
                if (i == 1){
                    dp[i][j] = dp[i][j - 1] + 1;
                    one[i][j] = one[i][j - 1];
                    continue;
                }

                if (i > 1 && j < 3) continue;

                if (j < n){
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j - 2]) % MOD;
                    one[i][j] = (one[i][j - 1] + one[i - 1][j - 2]) % MOD;
                } else if (j == n){
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j - 2]) % MOD;
                    dp[i][j] = (dp[i][j] - one[i - 1][j - 2] + MOD) % MOD;
                }
            }
        }
    
        int sum = dp[k][n];

        System.out.println(sum);
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