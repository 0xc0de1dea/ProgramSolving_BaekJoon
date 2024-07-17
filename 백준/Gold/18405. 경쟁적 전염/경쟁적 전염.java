import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Virus {
    int x, y;
    int type;

    public Virus(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

class Main {
    static int n, k;
    static int[][] map;
    static int s;
    static int x, y;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int bfs(PriorityQueue<Virus> pq){
        Queue<Virus> queue = new LinkedList<>();
        int[][] visited = new int[n][n];

        while (!pq.isEmpty()){
            Virus virus = pq.poll();
            queue.add(virus);
            visited[virus.x][virus.y] = virus.type;
        }

        while (!queue.isEmpty() && s > 0){
            int qSize = queue.size();

            while (qSize-- > 0){
                Virus cur = queue.poll();

                for (int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (0 <= nx && nx < n && 0 <= ny && ny < n){
                        if (visited[nx][ny] == 0){
                            visited[nx][ny] = cur.type;
                            queue.add(new Virus(nx, ny, cur.type));
                        }
                    }
                }
            }

            s--;
        }

        return visited[x - 1][y - 1];
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        k = in.nextInt();
        map = new int[n][n];

        PriorityQueue<Virus> pq = new PriorityQueue<>((x, y) -> x.type - y.type);
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = in.nextInt();
                
                if (map[i][j] > 0) pq.add(new Virus(i, j, map[i][j]));
            }
        }
        
        s = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();

        int type = bfs(pq);
        
        System.out.println(type);
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