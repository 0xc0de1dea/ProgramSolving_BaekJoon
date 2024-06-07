import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Paper {
    int x, y;

    public Paper(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n;
    static Paper[] papers;
    static int[][][] dp;
    static boolean[] isVisited;

    public static int dp(int depth, int idx, int state){
        if (depth > n) return 0;

        if (dp[depth][idx][state] > 0) return dp[depth][idx][state];

        int max = 0;

        for (int i = 1; i <= n; i++){
            if (!isVisited[i]){
                if (papers[idx].x >= papers[i].x && papers[idx].y >= papers[i].y){
                    isVisited[i] = true;
                    max = Math.max(max, 1 + dp(depth + 1, i, 0));
                    isVisited[i] = false;
                }

                if (papers[idx].x >= papers[i].y && papers[idx].y >= papers[i].x){
                    isVisited[i] = true;
                    max = Math.max(max, 1 + dp(depth + 1, i, 1));
                    isVisited[i] = false;
                }
            }
        }

        return dp[depth][idx][state] = max;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        n = in.nextInt();
        papers = new Paper[n + 1];
        dp = new int[101][101][2];
        isVisited = new boolean[n + 1];

        papers[0] = new Paper(1000, 1000);

        for (int i = 1; i <= n; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            papers[i] = new Paper(x, y);
        }

        System.out.println(dp(0, 0, 0));
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