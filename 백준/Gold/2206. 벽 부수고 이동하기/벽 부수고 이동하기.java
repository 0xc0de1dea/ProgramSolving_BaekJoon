/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[][][] map = new int[1_002][1_002][2];
    static int[] dx = new int[] { 0, 1, 0, -1 };
    static int[] dy = new int[] { 1, 0, -1, 0 };

    static void bfs(int sx, int sy){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { sx, sy, 0 });
        map[sx][sy][0] = map[sx][sy][1] = 1;

        while (q.size() > 0){
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (cur[2] == 0){
                    if (map[nx][ny][0] == -1){
                        q.add(new int[] { nx, ny, 1 });
                        map[nx][ny][1] = map[cur[0]][cur[1]][0] + 1;
                    }
                    else if (map[nx][ny][0] == 0){
                        q.add(new int[] { nx, ny, 0 });
                        map[nx][ny][0] = map[cur[0]][cur[1]][0] + 1;
                    }
                }
                else {
                    if (map[nx][ny][1] == 0){
                        q.add(new int[] { nx, ny, 1 });
                        map[nx][ny][1] = map[cur[0]][cur[1]][1] + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        
        for (int i = 1; i <= n; i++){
            int t;
            while ((t = in.read() - 48) < 0);
            for (int j = 1; j <= m; j++){
                map[i][j][0] = map[i][j][1] = t > 0 ? -1 : 0;
                t = in.read() - 48;
            }
        }
        for (int i = 0; i <= n + 1; i++) map[i][0][0] = map[i][0][1] = map[i][m + 1][0] = map[i][m + 1][1] = -2;
        for (int i = 0; i <= m + 1; i++) map[0][i][0] = map[0][i][1] = map[n + 1][i][0] = map[n + 1][i][1] = -2;
        
        bfs(1, 1);
        int min = -1;

        if (map[n][m][1] > 0) min = map[n][m][1];
        else if (map[n][m][0] > 0) min = map[n][m][0];

        System.out.print(min);
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