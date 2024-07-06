import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Axis {
    int x, y;

    public Axis(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n, m, d;
    static int[][] map;
    static int[] archer;
    static int ans = 0;

    public static boolean mapCheck(int[][] clone){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (clone[i][j] == 1){
                    return true;
                }
            }
        }

        return false;
    }

    public static Axis find(int[][] clone, int src){
        int archerX = n;
        int archerY = src;

        int distMax = 0x7f7f7f7f;
        int resX = -1;
        int resY = -1;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (clone[i][j] == 1){
                    int dist = Math.abs(archerX - i) + Math.abs(archerY - j);

                    if (dist <= d){
                        if (distMax > dist){
                            distMax = dist;
                            resX = i;
                            resY = j;
                        } else if (distMax == dist){
                            if (resY > j){
                                resX = i;
                                resY = j;
                            }
                        }
                    }
                }
            }
        }

        if (resX == -1 && resY == -1) return null;
        else return new Axis(resX, resY);
    }

    public static void move(int[][] clone){
        for (int i = n - 1; i >= 0; i--){
            for (int j = 0; j < m; j++){
                if (i == n - 1) clone[i][j] = 0;
                else {
                    clone[i + 1][j] = clone[i][j];
                    clone[i][j] = 0;
                }
            }
        }
    }

    public static void print(int[][] clone, Axis[] trg){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                System.out.printf("%2d", clone[i][j]);
            }
            System.out.println();
        }

        System.out.println(trg[0] == null ? null : trg[0].x + " " + trg[0].y);
        System.out.println(trg[1] == null ? null : trg[1].x + " " + trg[1].y);
        System.out.println(trg[2] == null ? null : trg[2].x + " " + trg[2].y);

        System.out.println();
    }

    public static void simulation(){
        int[][] clone = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                clone[i][j] = map[i][j];
            }
        }

        //System.out.println(archer[0] + " " + archer[1] + " " + archer[2]);
        int cnt = 0;

        while (true){
            if (!mapCheck(clone)) break;

            Axis[] trg = new Axis[3];

            for (int i = 0; i < 3; i++){
                trg[i] = find(clone, archer[i]);
            }

            for (int i = 0; i < 3; i++){
                if (trg[i] != null){
                    if (clone[trg[i].x][trg[i].y] == 1){
                        clone[trg[i].x][trg[i].y] = 0;
                        cnt++;
                    }
                }
            }

            //System.out.println(cnt);
            
            move(clone);
            //print(clone, trg);
        }

        ans = Math.max(ans, cnt);
    }

    public static void backtracking(int start, int depth){
        if (depth == 3){
            simulation();
            return;
        }

        for (int i = start; i < m; i++){
            archer[depth] = i;
            backtracking(i + 1, depth + 1);
            archer[depth] = 0;
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        d = in.nextInt();
        map = new int[n][m];
        archer = new int[3];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                map[i][j] = in.nextInt();
            }
        }
        
        backtracking(0, 0);

        System.out.println(ans);
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