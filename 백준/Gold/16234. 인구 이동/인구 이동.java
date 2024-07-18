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
    static int n, l, r;
    static int[][] country;
    static int[][] isVisited;
    static int[] visited;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void bfs(int sx, int sy, int id){
        Queue<Axis> queue = new LinkedList<>();
        queue.add(new Axis(sx, sy));

        isVisited[sx][sy] = id;

        int sum = country[sx][sy];
        int cnt = 1;

        while (!queue.isEmpty()){
            Axis cur = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n){
                    if (isVisited[nx][ny] == 0){
                        int diff = Math.abs(country[cur.x][cur.y] - country[nx][ny]);

                        if (l <= diff && diff <= r){
                            isVisited[nx][ny] = id;
                            sum += country[nx][ny];
                            cnt++;
                            queue.add(new Axis(nx, ny));
                        }
                    }
                }
            }
        }

        visited[id] = sum / cnt;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        l = in.nextInt();
        r = in.nextInt();
        country = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                country[i][j] = in.nextInt();
            }
        }

        int time = 0;

        while (true){
            int id = 1;
            isVisited = new int[n][n];
            visited = new int[n * n + 1];

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (isVisited[i][j] == 0){
                        bfs(i, j, id++);
                    }
                }
            }

            if (id == n * n + 1) break;

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (isVisited[i][j] > 0){
                        country[i][j] = visited[isVisited[i][j]];
                    }
                }
            }

            time++;
        }

        System.out.print(time);
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