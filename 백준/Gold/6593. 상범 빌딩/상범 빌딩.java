import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Axis {
    int h, x, y;

    public Axis(int h, int x, int y){
        this.h = h;
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int l, r, c;
    static char[][][] map;
    static int[] dh = { 0, 0, 0, 0, 1, -1 };
    static int[] dx = { 0, 1, 0, -1, 0, 0 };
    static int[] dy = { 1, 0, -1, 0, 0, 0 };

    public static int bfs(Axis s, Axis e){
        Queue<Axis> queue = new LinkedList<>();
        queue.add(s);

        boolean[][][] isVisited = new boolean[l][r][c];
        isVisited[s.h][s.x][s.y] = true;

        int time = 0;

        while (!queue.isEmpty()){
            int qSize = queue.size();

            while (qSize-- > 0){
                Axis cur = queue.poll();

                if (cur.h == e.h && cur.x == e.x && cur.y == e.y) return time;

                for (int i = 0; i < 6; i++){
                    int nh = cur.h + dh[i];
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (0 <= nh && nh < l && 0 <= nx && nx < r && 0 <= ny && ny < c){
                        if (!isVisited[nh][nx][ny] && (map[nh][nx][ny] == '.' || map[nh][nx][ny] == 'E')){
                            isVisited[nh][nx][ny] = true;
                            queue.add(new Axis(nh, nx, ny));
                        }
                    }
                }
            }

            time++;
        }

        //print(isVisited);

        return -1;
    }

    public static void print(boolean[][][] isVisited){
        for (int i = 0; i < l; i++){
            for (int j = 0; j < r; j++){
                for (int k = 0; k < c; k++){
                    System.out.printf("%d", isVisited[i][j][k] ? 1 : 0);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        while (true){
            l = in.nextInt();
            r = in.nextInt();
            c = in.nextInt();

            if (l == 0 && r == 0 && c == 0) break;

            map = new char[l][r][c];

            int sh = 0, sx = 0, sy = 0;
            int eh = 0, ex = 0, ey = 0;

            for (int i = 0; i < l; i++){
                for (int j = 0; j < r; j++){
                    for (int k = 0; k < c; k++){
                        map[i][j][k] = in.nextChar();

                        if (map[i][j][k] == 'S'){
                            sh = i;
                            sx = j;
                            sy = k;
                        } else if (map[i][j][k] == 'E'){
                            eh = i;
                            ex = j;
                            ey = k;
                        }
                    }
                }
            }

            int res = bfs(new Axis(sh, sx, sy), new Axis(eh, ex, ey));
            sb.append(res == -1 ? "Trapped!" : String.format("Escaped in %d minute(s).", res)).append('\n');
        }

        System.out.print(sb);
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