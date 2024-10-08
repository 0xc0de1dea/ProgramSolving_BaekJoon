import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Axis {
    int x, y;
    int jump;

    public Axis(int x, int y, int jump){
        this.x = x;
        this.y = y;
        this.jump = jump;
    }
}

public class Main{
    static int k;
    static int w, h;
    static int[][] map;
    static int[] dxM = { 0, 1, 0, -1 };
    static int[] dyM = { 1, 0, -1, 0 };
    static int[] dxH = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] dyH = { 1, 2, 2, 1, -1, -2, -2, -1 };

    public static int bfs(int sx, int sy){
        Queue<Axis> queue = new LinkedList<>();
        queue.add(new Axis(sx, sy, 0));

        boolean[][][] isVisited = new boolean[h][w][k + 1];
        isVisited[sx][sy][0] = true;

        int cnt = 0;

        while (!queue.isEmpty()){
            int qSize = queue.size();

            while (qSize-- > 0){
                Axis cur = queue.poll();

                if (cur.x == h - 1 && cur.y == w - 1){
                    return cnt;
                }

                if (cur.jump < k){
                    for (int i = 0; i < 8; i++){
                        int nx = cur.x + dxH[i];
                        int ny = cur.y + dyH[i];

                        if (0 <= nx && nx < h && 0 <= ny && ny < w){
                            if (!isVisited[nx][ny][cur.jump + 1] && map[nx][ny] == 0){
                                isVisited[nx][ny][cur.jump + 1] = true;
                                queue.add(new Axis(nx, ny, cur.jump + 1));
                            }
                        }
                    }
                }

                for (int i = 0; i < 4; i++){
                    int nx = cur.x + dxM[i];
                    int ny = cur.y + dyM[i];

                    if (0 <= nx && nx < h && 0 <= ny && ny < w){
                        if (!isVisited[nx][ny][cur.jump] && map[nx][ny] == 0){
                            isVisited[nx][ny][cur.jump] = true;
                            queue.add(new Axis(nx, ny, cur.jump));
                        }
                    }
                }
            }

            cnt++;
        }

        return -1;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        k = in.nextInt();
        w = in.nextInt();
        h = in.nextInt();
        map = new int[h][w];

        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                map[i][j] = in.nextInt();
            }
        }

        int cnt = bfs(0, 0);

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
        while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
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