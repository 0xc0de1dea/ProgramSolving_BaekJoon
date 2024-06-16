/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static long[][][] dp;

    public static long nCr(int n, int r){
        long res = 1;

        for (int i = n - r + 1; i <= n; i++){
            res *= i;
            res /= i - n + r;
        }

        return res;
    }

    public static long dp(int level, int r, int b, int g){
        if (level > n) return 1;

        if (dp[r][b][g] != -1) return dp[r][b][g];

        long sum = 0;

        for (int i = 0; i <= level; i++){
            for (int j = 0; j <= level; j++){
                for (int k = 0; k <= level; k++){
                    if (i == 0 && j == 0 && k == 0) continue;
                    if (r < i || b < j || g < k) continue;

                    if ((i == level && j == 0 && k == 0) || (j == level && i == 0 && k == 0) || (k == level && i == 0 && j == 0)){
                        sum += dp(level + 1, r - i, b - j, g - k);
                    }

                    if ((i == j && k == 0 && i + j == level) || (i == k && j == 0 && i + k == level) || (j == k && i == 0 && j + k == level)){
                        sum += nCr(level, level >> 1) * dp(level + 1, r - i, b - j, g - k);
                    }

                    if (i == j && j == k && i + j + k == level){
                        sum += nCr(level, level / 3) * nCr(level - level / 3, level / 3) * dp(level + 1, r - i, b - j, g - k);
                    }
                }
            }
        }

        return dp[r][b][g] = sum;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        int red = in.nextInt();
        int blue = in.nextInt();
        int green = in.nextInt();
        dp = new long[101][101][101];

        for (int j = 0; j < 101; j++){
            for (int k = 0; k < 101; k++){
                for (int l = 0; l < 101; l++){
                    dp[j][k][l] = -1;
                }
            }
        }

        long res = dp(1, red, blue, green);

        System.out.println(res);
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