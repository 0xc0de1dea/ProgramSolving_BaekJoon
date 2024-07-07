import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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

public class Main {
    static int n, m;
    static int[][] map;
    static ArrayList<Axis> list = new ArrayList<>();
    static int[] choice;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static final int INF = 0x7f7f7f7f;

    public static boolean check(boolean[][] isVisited){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (map[i][j] == 0 && !isVisited[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    public static int bfs(){
        Queue<Axis> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[n][n];
        
        for (int i = 0; i < choice.length; i++){
            if (choice[i] == 1){
                int x = list.get(i).x;
                int y = list.get(i).y;

                queue.add(new Axis(x, y));
                isVisited[x][y] = true;
            }
        }

        int time = 0;

        while (!queue.isEmpty()){
            int qSize = queue.size();
            
            if (check(isVisited)) break;
            
            while (qSize-- > 0){
                Axis cur = queue.poll();

                for (int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n){
                        if (!isVisited[nx][ny]){
                            if (map[nx][ny] == 0 || map[nx][ny] == 2){
                                isVisited[nx][ny] = true;
                                queue.add(new Axis(nx, ny));
                            }
                        }
                    }
                }
            }

            time++;
        }

        boolean flag = true;

        jump : for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (map[i][j] == 0){
                    if (!isVisited[i][j]){
                        flag = false;
                        break jump;
                    }
                }
            }
        }

        return !flag ? INF : time;
    }

    public static int backtracking(int depth, int start){
        if (depth == m){
            int res = bfs();
            return res;
        }

        int min = INF;

        for (int i = start; i < list.size(); i++){
            choice[i] = 1;
            min = Math.min(min, backtracking(depth + 1, i + 1));
            choice[i] = 0;
        }

        return min;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = in.nextInt();

                if (map[i][j] == 2){
                    list.add(new Axis(i, j));
                }
            }
        }

        choice = new int[list.size()];
        
        int min = backtracking(0, 0);

        System.out.println(min == INF ? -1 : min);
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