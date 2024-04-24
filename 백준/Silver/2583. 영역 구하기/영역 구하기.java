import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };

    public static boolean isValid(int[][] map, int x, int y){
        return 0 <= y && y < map.length && 0 <= x && x < map[0].length;
    }

    public static int bfs(int[][] map, int sx, int sy){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sx);
        queue.add(sy);
        map[sy][sx] = -1;

        int area = 1;
        
        while (!queue.isEmpty()){
            int x = queue.poll();
            int y = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValid(map, nx, ny)){
                    if (map[ny][nx] == 0){
                        map[ny][nx] = -1;
                        queue.add(nx);
                        queue.add(ny);
                        area++;
                    }
                }
            }
        }

        return area;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] map = new int[m][n];

        for (int i = 0; i < k; i++){
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt() - 1;
            int y2 = in.nextInt() - 1;

            for (int y = y1; y <= y2; y++){
                for (int x = x1; x <= x2; x++){
                    map[y][x] = 1;
                }
            }
        }

        int cnt = 0;
        ArrayList<Integer> area = new ArrayList<>();

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (map[i][j] == 0){
                    area.add(bfs(map, j, i));
                    cnt++;
                }
            }
        }

        Collections.sort(area);
        sb.append(cnt).append('\n');

        for (int item : area){
            sb.append(item).append(' ');
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