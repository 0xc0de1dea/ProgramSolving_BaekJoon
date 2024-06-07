/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n, m;
    static char[][] map;
    static int[][] dp;
    static boolean[][] isVisited;

    public static int backtracking(int r, int c){
        if (r <= 0 || r > n || c <= 0 || c > m) return 1;

        if (dp[r][c] >= 0) return dp[r][c];

        int res = 0;

        isVisited[r][c] = true;

        if (map[r][c] == 'U'){
            if (!isVisited[r - 1][c]){
                res |= backtracking(r - 1, c);
            }
        } else if (map[r][c] == 'D'){
            if (!isVisited[r + 1][c]){
                res |= backtracking(r + 1, c);
            }
        } else if (map[r][c] == 'L'){
            if (!isVisited[r][c - 1]){
                res |= backtracking(r, c - 1);
            }
        } else if (map[r][c] == 'R'){
            if (!isVisited[r][c + 1]){
                res |= backtracking(r, c + 1);
            }
        }

        isVisited[r][c] = false;

        return dp[r][c] = res;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        n = in.nextInt();
        m = in.nextInt();
        map = new char[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        isVisited = new boolean[n + 2][m + 2];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                map[i][j] = in.nextChar();
                dp[i][j] = -1;
            }
        }

        int cnt = 0;

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= m; j++){
                cnt += backtracking(i, j);
            }
        }

        System.out.println(cnt);
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