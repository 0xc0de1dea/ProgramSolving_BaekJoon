/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static double a, b;
    static double[][][] dp;

    public static boolean isPrime(int n){
        int[] prime = { 2, 3, 5, 7, 11, 13, 17 };

        for (int i = 0; i < 7; i++){
            if (n == prime[i]){
                return true;
            }
        }

        return false;
    }

    public static double dp(int time, int scoreA, int scoreB){
        if (time > 18) return isPrime(scoreA) || isPrime(scoreB) ? 1 : 0;

        if (dp[time][scoreA][scoreB] != -1) return dp[time][scoreA][scoreB];

        double sum = 0;

        sum += (1 - a) * (1 - b) * dp(time + 1, scoreA, scoreB);
        sum += a * (1 - b) * dp(time + 1, scoreA + 1, scoreB);
        sum += (1 - a) * b * dp(time + 1, scoreA, scoreB + 1);
        sum += a * b * dp(time + 1, scoreA + 1, scoreB + 1);

        return dp[time][scoreA][scoreB] = sum;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        a = in.nextDouble() / 100;
        b = in.nextDouble() / 100;
        dp = new double[19][19][19];

        for (int i = 1; i <= 18; i++){
            for (int j = 0; j <= 18; j++){
                for (int k = 0; k <= 18; k++){
                    dp[i][j][k] = -1;
                }
            }
        }

        double res = dp(1, 0, 0);

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