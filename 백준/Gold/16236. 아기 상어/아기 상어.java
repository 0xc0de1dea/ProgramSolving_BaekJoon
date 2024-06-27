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

class Dist implements Comparable<Dist> {
    int x, y;
    int time;

    public Dist(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Dist o) {
        if (this.time != o.time){
            return this.time - o.time;
        } else {
            if (this.x != o.x){
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }
}

public class Main {
    static int n;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int bfs(Axis shark, int bodySize){
        Queue<Axis> q = new LinkedList<>();
        q.add(new Axis(shark.x, shark.y));

        int[][] isVisited = new int[n][n];
        isVisited[shark.x][shark.y] = 1;

        while (!q.isEmpty()){
            int qSize = q.size();

            while (qSize-- > 0){
                Axis cur = q.poll();
    
                for (int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
    
                    if (0 <= nx && nx < n && 0 <= ny && ny < n){
                        if (0 <= map[nx][ny] && map[nx][ny] <= bodySize && map[nx][ny] != 9){
                            if (isVisited[nx][ny] == 0){
                                isVisited[nx][ny] = isVisited[cur.x][cur.y] + 1;
                                q.add(new Axis(nx, ny));
                            }
                        }
                    }
                }
            }
        }

        //printVisit(isVisited);

        PriorityQueue<Dist> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (0 < map[i][j] && map[i][j] < bodySize && map[i][j] != 9){
                    if (isVisited[i][j] > 0){
                        pq.add(new Dist(i, j, isVisited[i][j] - 1));
                    }
                }
            }
        }

        if (pq.size() > 0){
            Dist fish = pq.poll();
            map[shark.x][shark.y] = 0;
            map[fish.x][fish.y] = 9;
            return fish.time;
        }

        return 0;
    }

    public static int solve(){
        int bodySize = 2;
        int eat = 0;
        int time = 0;

        while (true){
            Axis shark = null;

            jump : for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (map[i][j] == 9){
                        shark = new Axis(i, j);
                        break jump;
                    }
                }
            }

            int res = bfs(shark, bodySize);

            if (res == 0) break;

            time += res;
            eat++;

            if (eat >= bodySize){
                eat -= bodySize;
                bodySize++;
            }

            //print(time, bodySize, eat);
        }

        return time;
    }

    public static void printVisit(int[][] isVisited){
        System.out.println("Visit");
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%2d", isVisited[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void print(int time, int size, int eat){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%2d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println(time + " " + size + " " + eat);
        System.out.println();
    }
    
    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        map = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = in.nextInt();
            }
        }

        int res = solve();

        System.out.println(res);
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