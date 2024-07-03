import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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

public class Main{
    static int n, m;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static boolean[][] isVisited;
    
    public static void dfs(int x, int y){
        isVisited[x][y] = true;

        for (int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m){
                if (!isVisited[nx][ny]){
                    if (map[nx][ny] >= 1){
                        map[nx][ny]++;
                    } else if (map[nx][ny] == 0){
                        dfs(nx, ny);
                    }
                }
            }
        }
    }

    public static void melt(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (map[i][j] >= 3){
                    map[i][j] = 0;
                } else if (map[i][j] > 0){
                    map[i][j] = 1;
                }
            }
        }
    }

    public static void print(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                System.out.printf("%2d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
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
            }
        }

        int time = 0;

        while (true){
            boolean flag = false;

            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    if (map[i][j] == 1){
                        flag = true;
                        break;
                    }
                }
            }

            if (flag){
                isVisited = new boolean[n][m];
                dfs(0, 0);
                melt();
                //print();
                time++;
            } else break;
        }

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