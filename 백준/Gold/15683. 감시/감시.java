import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class CCTV {
    int x, y;
    int type;

    public CCTV(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

public class Main {
    static int n, m;
    static int[][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
    static int[] choice;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void set(int[][] clone, CCTV cctv, int dir){
        int x = cctv.x;
        int y = cctv.y;
        int sz = 1;

        while (true){
            int nx = x + dx[dir] * sz;
            int ny = y + dy[dir] * sz;

            if (0 <= nx && nx < n && 0 <= ny && ny < m){
                if (map[nx][ny] == 0){
                    clone[nx][ny] = 9;
                } else if (map[nx][ny] == 6){
                    break;
                }
            } else {
                break;
            }

            sz++;
        }
    }

    public static void print(int[][] clone){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                System.out.printf("%2d", clone[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int search(){
        int[][] clone = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                clone[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < cctvs.size(); i++){
            CCTV cctv = cctvs.get(i);
            int dir = choice[i];

            if (cctv.type == 1){
                set(clone, cctv, dir);
            } else if (cctv.type == 2){
                set(clone, cctv, dir);
                set(clone, cctv, (dir + 2) % 4);
            } else if (cctv.type == 3){
                set(clone, cctv, dir);
                set(clone, cctv, (dir + 1) % 4);
            } else if (cctv.type == 4){
                set(clone, cctv, dir);
                set(clone, cctv, (dir + 1) % 4);
                set(clone, cctv, (dir + 2) % 4);
            } else if (cctv.type == 5){
                set(clone, cctv, dir);
                set(clone, cctv, (dir + 1) % 4);
                set(clone, cctv, (dir + 2) % 4);
                set(clone, cctv, (dir + 3) % 4);
            }
        }

        int cnt = 0;
        //print(clone);

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (clone[i][j] == 0){
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static int backtracking(int depth){
        if (depth == cctvs.size()){
            return search();
        }

        int min = 0x7f7f7f7f;

        for (int i = 0; i < 4; i++){
            choice[depth] = i;
            min = Math.min(min, backtracking(depth + 1));
        }

        return min;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                map[i][j] = in.nextInt();

                if (1 <= map[i][j] && map[i][j] <= 5){
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        choice = new int[cctvs.size()];

        int min = backtracking(0);

        System.out.println(min);
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