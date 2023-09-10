/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][][] tomatoes;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = new int[] { 0, 0, 0, 0, 1, -1 };
    static int[] dy = new int[] { 0, 1, 0, -1, 0, 0 };
    static int[] dz = new int[] { 1, 0, -1, 0, 0, 0 };

    static int bfs(int h, int n, int m){
        while (q.size() > 0){
            int[] cur = q.poll();
            for (int i = 0; i < 6; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int nz = cur[2] + dz[i];
                if (tomatoes[nx][ny][nz] == 0){
                    q.add(new int[] { nx, ny, nz });
                    tomatoes[nx][ny][nz] = tomatoes[cur[0]][cur[1]][cur[2]] + 1;
                }
            }
        }

        int date = 0;

        jump : for (int i = 1; i <= h; i++){
            for (int j = 1; j <= n; j++){
                for (int k = 1; k <= m; k++){
                    if (tomatoes[i][j][k] == 0){
                        date = 0;
                        break jump;
                    }
                    date = Math.max(date, tomatoes[i][j][k]);
                }
            }
        }

        return date > 0 ? date - 1 : -1;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int m = in.nextInt();
        int n = in.nextInt();
        int h = in.nextInt();
        tomatoes = new int[h + 2][n + 2][m + 2];

        for (int i = 0; i <= n + 1; i++){
            for (int j = 0; j <= m + 1; j++){
                tomatoes[0][i][j] = tomatoes[h + 1][i][j] = -1;
            }
        }
        for (int i = 0; i <= h + 1; i++){
            for (int j = 0; j <= n + 1; j++){
                tomatoes[i][j][0] = tomatoes[i][j][m + 1] = -1;
            }
        }

        for (int i = 0; i <= h + 1; i++){
            for (int j = 0; j <= m + 1; j++){
                tomatoes[i][0][j] = tomatoes[i][n + 1][j] = -1;
            }
        }
        for (int i = 1; i <= h; i++){
            for (int j = 1; j <= n; j++){
                for (int k = 1; k <= m; k++){
                    tomatoes[i][j][k] = in.nextInt();
                    if (tomatoes[i][j][k] == 1){
                        q.add(new int[] { i, j, k });
                    }
                }
            }
        }

        System.out.print(bfs(h, n, m));
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