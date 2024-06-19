/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int r, c;
    static int[][] apple;
    static int[][] banana;
    static int[][] dp;
    static int[] dr = { 0, 1, 1 };
    static int[] dc = { 1, 1, 0 };

    public static int dp(int rr, int cc){
        if (rr == r && cc == c) return 0;

        if (dp[rr][cc] != -1) return dp[rr][cc];

        int max = 0;

        for (int i = 0; i < 3; i++){
            int nr = rr + dr[i];
            int nc = cc + dc[i];

            if (0 < nr && nr <= r && 0 < nc && nc <= c){
                int appleSum = apple[r][cc] - apple[r][cc - 1] - apple[rr][cc] + apple[rr][cc - 1];
                int bananaSum = banana[rr][c] - banana[rr - 1][c] - banana[rr][cc] + banana[rr - 1][cc];
    
                if (i == 0){
                    max = Math.max(max, appleSum + dp(nr, nc));
                } else if (i == 1){
                    max = Math.max(max, appleSum + bananaSum + dp(nr, nc));
                } else if (i == 2){
                    max = Math.max(max, bananaSum + dp(nr, nc));
                }
            }
        }

        return dp[rr][cc] = max;
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        r = in.nextInt();
        c = in.nextInt();
        apple = new int[r + 1][c + 1];
        banana = new int[r + 1][c + 1];
        dp = new int[r + 1][c + 1];

        for (int i = 1; i <= r; i++){
            for (int j = 1; j <= c; j++){
                String str = in.nextString();
                char type = str.charAt(0);
                int num = 0;

                for (int k = 1; k < str.length(); k++){
                    num = (num << 3) + (num << 1) + (str.charAt(k) - '0');
                }
                
                if (type == 'A'){
                    apple[i][j] = num + apple[i][j - 1] + apple[i - 1][j] - apple[i - 1][j - 1];
                    banana[i][j] = banana[i][j - 1] + banana[i - 1][j] - banana[i - 1][j - 1];
                } else if (type == 'B'){
                    apple[i][j] = apple[i][j - 1] + apple[i - 1][j] - apple[i - 1][j - 1];
                    banana[i][j] = num + banana[i][j - 1] + banana[i - 1][j] - banana[i - 1][j - 1];
                }

                dp[i][j] = -1;
            }
        }

        System.out.println(dp(1, 1));
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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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