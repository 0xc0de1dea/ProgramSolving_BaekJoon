import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Axis {
    int r, c;
    int type;

    public Axis(int r, int c, int type){
        this.r = r;
        this.c = c;
        this.type = type;
    }
}

public class Main {
    static int r, c;
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };

    public static int bfs(int sr, int sc){
        Queue<Axis> queue = new LinkedList<>();

        isVisited[sr][sc] = true;

        for (int i = 1; i <= r; i++){
            for (int j = 1; j <= c; j++){
                if (map[i][j] == '*'){
                    queue.add(new Axis(i, j, 1));
                    isVisited[i][j] = true;
                }
            }
        }

        queue.add(new Axis(sr, sc, 0));

        int time = 0;

        while (!queue.isEmpty()){
            int qSize = queue.size();
     
            while (qSize-- > 0){
                Axis cur = queue.poll();
                
                if (cur.type == 0 && map[cur.r][cur.c] == 'D'){
                    return time;
                }

                for (int i = 0; i < 4; i++){
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
    
                    if (cur.type == 0){
                        if (!isVisited[nr][nc] && map[nr][nc] != 'X'){
                            isVisited[nr][nc] = true;
                            queue.add(new Axis(nr, nc, 0));
                        }
                    } else if (cur.type == 1){
                        if (!isVisited[nr][nc] && map[nr][nc] != 'X' && map[nr][nc] != 'D'){
                            isVisited[nr][nc] = true;
                            queue.add(new Axis(nr, nc, 1));
                        }
                    }
                }
            }

            time++;
        }

        return -1;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        r = in.nextInt();
        c = in.nextInt();
        map = new char[r + 2][c + 2];
        isVisited = new boolean[r + 2][c + 2];

        for (int i = 0; i <= r + 1; i++){
            map[i][0] = map[i][c + 1] = '*';
            isVisited[i][0] = isVisited[i][c + 1] = true;
        }

        for (int i = 0; i <= c + 1; i++){
            map[0][i] = map[r + 1][i] = '*';
            isVisited[0][i] = isVisited[r + 1][i] = true;
        }

        int sr = 0;
        int sc = 0;

        for (int i = 1; i <= r; i++){
            for (int j = 1; j <= c; j++){
                map[i][j] = in.nextChar();

                if (map[i][j] == 'S'){
                    sr = i;
                    sc = j;
                }
            }
        }

        int time = bfs(sr, sc);

        // for (int i = 1; i <= r; i++){
        //     for (int j = 1; j <= c; j++){
        //         System.out.printf("%2d", isVisited[i][j] ? 1 : 0);
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        System.out.println(time == -1 ? "KAKTUS" : time);
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