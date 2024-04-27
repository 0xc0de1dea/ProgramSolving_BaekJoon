import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Point {
    int x, y;
    int broken;

    public Point(int x, int y , int broken){
        this.x = x;
        this.y = y;
        this.broken = broken;
    }
}

public class Main {
    static int n;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int[][] bfs(int[][] map, int sx, int sy){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(sx, sy, 0));

        int[][] cost = new int[n + 2][n + 2];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        cost[sx][sy] = 0;
        
        while (!queue.isEmpty()){
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nCost = cur.broken;

                if (map[nx][ny] >= 0){
                    if (map[nx][ny] == 0){
                        nCost++;
                    }

                    if (cost[nx][ny] > nCost){
                        cost[nx][ny] = nCost;
                        queue.add(new Point(nx, ny, nCost));
                    }
                }
            }
        }

        return cost;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        int[][] map = new int[n + 2][n + 2];

        for (int i = 1; i <= n; i++){
            char[] rooms = in.nextString().toCharArray();

            for (int j = 1; j <= n; j++){
                map[i][j] = rooms[j - 1] - '0';
            }
        }

        for (int i = 0; i <= n + 1; i++){
            map[i][0] = map[i][n + 1] = map[0][i] = map[n + 1][i] = -1;
        }

        int[][] cost = bfs(map, 1, 1);
        System.out.print(cost[n][n]);
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