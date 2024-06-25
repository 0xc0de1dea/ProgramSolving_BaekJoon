import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Light {
    int x, y;
    int dir;
    int cnt;

    public Light(int x, int y, int dir, int cnt){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }
}

public class Main {
    static int n;
    static char[][] map;
    static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static int bfs(int sx, int sy){
        int res = 0x7f7f7f7f;
        PriorityQueue<Light> queue = new PriorityQueue<>((x, y) -> x.cnt - y.cnt);
        boolean[][][] isVisited = new boolean[n][n][4];

        for (int i = 0; i < 4; i++){
            queue.add(new Light(sx, sy, i, 0));
            isVisited[sx][sy][i] = true;
        }

        
        while (!queue.isEmpty()){
            Light cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int d = cur.dir;
            int c = cur.cnt;

            isVisited[x][y][d] = true;

            if ((x != sx || y != sy) && map[x][y] == '#'){
                res = c;
                break;
            }

            int nx = x + dir[d][0];
            int ny = y + dir[d][1];

            if (0 <= nx && nx < n && 0 <= ny && ny < n){
                if (!isVisited[nx][ny][d]){
                    if (map[nx][ny] != '*'){
                        if (map[nx][ny] == '!'){
                            for (int i = 0; i < 2; i++){
                                int nd = i == 0 ? (d + 3) % 4 : (d + 1) % 4;
                                queue.add(new Light(nx, ny, nd, c + 1));
                            }
                        }

                        queue.add(new Light(nx, ny, d, c));
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        map = new char[n][n];

        int sx = -1, sy = -1;
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = in.nextChar();

                if (map[i][j] == '#' && sx == -1 && sy == -1){
                    sx = i;
                    sy = j;
                }
            }
        }

        int res = bfs(sx, sy);

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