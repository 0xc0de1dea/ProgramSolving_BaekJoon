/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

public class Main {
    static int r, c;
    static int[][] board;
    static int[][] isVisited;
    static int max = 1;
    static int[] dr = new int[] { 0, 1, 0, -1 };
    static int[] dc = new int[] { 1, 0, -1, 0 };

    public static void dfs(int depth, int curR, int curC, int bit){
        isVisited[curR][curC] = bit;

        for (int i = 0; i < 4; i++){
            int nr = curR + dr[i];
            int nc = curC + dc[i];
            int nbit = 1 << board[nr][nc];

            if ((bit & nbit) == 0 && (bit | nbit) != isVisited[nr][nc]){
                dfs(depth + 1, nr, nc, bit | nbit);
            }
            else {
                max = Math.max(max, depth);
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        r = in.nextInt();
        c = in.nextInt();
        board = new int[r + 2][c + 2];
        isVisited = new int[r + 1][c + 1];

        for (int i = 1; i <= r; i++){
            byte t;
            while((t = in.read()) <= 32);
            for (int j = 1; j <= c; j++){
                board[i][j] = t - 65;
                t = in.read();
            }
        }
        for (int i = 0; i <= r + 1; i++) board[i][0] = board[i][c + 1] = board[1][1];
        for (int i = 0; i <= c + 1; i++) board[0][i] = board[r + 1][i] = board[1][1];

        dfs(1, 1, 1, 1 << board[1][1]);
        System.out.print(max);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
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
        while ((c = read()) <= 32);
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
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}