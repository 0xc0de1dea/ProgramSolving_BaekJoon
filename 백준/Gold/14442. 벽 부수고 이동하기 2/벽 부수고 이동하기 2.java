import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Axis {
    int x, y;
    int broken;

    public Axis(int x, int y, int broken){
        this.x = x;
        this.y = y;
        this.broken = broken;
    }
}

class Main {
    static int n, m, k;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int bfs(int sx, int sy){
        Queue<Axis> queue = new LinkedList<>();
        queue.add(new Axis(sx, sy, 0));

        boolean[][][] isVisited = new boolean[n][m][k + 1];
        isVisited[sx][sy][0] = true;

        int time = 1;

        while (!queue.isEmpty()){
            int qSize = queue.size();

            while (qSize-- > 0){
                Axis cur = queue.poll();

                if (cur.x == n - 1 && cur.y == m - 1) return time;
                
                for (int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m){
                        if (map[nx][ny] == 0){
                            if (!isVisited[nx][ny][cur.broken]){
                                isVisited[nx][ny][cur.broken] = true;
                                queue.add(new Axis(nx, ny, cur.broken));
                            }
                        } else {
                            if (cur.broken + 1 <= k && !isVisited[nx][ny][cur.broken + 1]){
                                isVisited[nx][ny][cur.broken + 1] = true;
                                queue.add(new Axis(nx, ny, cur.broken + 1));
                            }
                        }
                    }
                }
            }

            time++;
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                map[i][j] = in.nextChar() - '0';
            }
        }

        int time = bfs(0, 0);

        System.out.println(time);
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