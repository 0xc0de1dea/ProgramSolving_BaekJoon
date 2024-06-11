/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Memo {
    int nxt;
    int val;

    public Memo(int nxt, int val){
        this.nxt = nxt;
        this.val = val;
    }
}

public class Main {
    static int N, M;
    static int[] marble;
    static int[][] dp;
    static Memo[][] tracking;
    static final int INF = 0x3f3f3f3f;

    public static int dp(int n, int m){
        if (n == N){
            if (m == M){
                return 0;
            } else {
                return INF;
            }
        }

        if (dp[n][m] > 0) return dp[n][m];

        int minMax = INF;

        if (m == M - 1){
            int res = Math.max(marble[N] - marble[n], dp(N, m + 1));

            if (minMax > res){
                minMax = res;
                tracking[n][m] = new Memo(N, N - n);
            }
        } else {
            for (int i = n + 1; i <= N; i++){
                int res = Math.max(marble[i] - marble[n], dp(i, m + 1));
                
                if (minMax > res){
                    minMax = res;
                    tracking[n][m] = new Memo(i, i - n);
                }
            }
        }

        return dp[n][m] = minMax;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        N = in.nextInt();
        M = in.nextInt();
        marble = new int[N + 1];
        dp = new int[N + 1][M + 1];
        tracking = new Memo[N + 1][M + 1];

        for (int i = 1; i <= N; i++){
            marble[i] = marble[i - 1] + in.nextInt();
        }

        sb.append(dp(0, 0)).append('\n');

        int curN = 0;
        int curM = 0;

        while (curN != N){
            sb.append(tracking[curN][curM].val).append(' ');
            curN = tracking[curN][curM].nxt;
            curM++;
        }

        System.out.println(sb);
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