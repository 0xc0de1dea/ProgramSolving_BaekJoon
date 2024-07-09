import java.util.LinkedList;
import java.util.Queue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Axis {
    int x, y;
    char type;

    public Axis(int x, int y, char type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}

public class Main {
    static char[][] map;
    static boolean[][] isVisited;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int bfs(Axis start){
        Queue<Axis> queue = new LinkedList<>();
        queue.add(start);
        isVisited[start.x][start.y] = true;

        boolean[][] check = new boolean[12][6];
        check[start.x][start.y] = true;

        int combo = 0;

        while (!queue.isEmpty()){
            int qSize = queue.size();
            combo += qSize;

            while (qSize-- > 0){
                Axis cur = queue.poll();

                for (int i = 0; i < 4; i++){
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (0 <= nx && nx < 12 && 0 <= ny && ny < 6){
                        if (!isVisited[nx][ny] && map[nx][ny] == cur.type){
                            isVisited[nx][ny] = true;
                            check[nx][ny] = true;
                            queue.add(new Axis(nx, ny, cur.type));
                        }
                    }
                }
            }
        }
        
        if (combo >= 4){
            for (int i = 0; i < 12; i++){
                for (int j = 0; j < 6; j++){
                    if (check[i][j]){
                        map[i][j] = '.';
                    }
                }
            }
        }

        return combo >= 4 ? 1 : 0;
    }

    public static void drop(){
        for (int i = 11; i > 0; i--){
            for (int j = 0; j < 6; j++){
                int k = i;

                if (map[i][j] == '.'){
                    while (map[--k][j] == '.' && k > 0);
    
                    map[i][j] = map[k][j];
                    map[k][j] = '.';
                }
            }
        }
    }

    public static void print(){
        for (int i = 0; i < 12; i++){
            for (int j = 0; j < 6; j++){
                System.out.printf("%c", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        map = new char[12][6];

        for (int i = 0; i < 12; i++){
            for (int j = 0; j < 6; j++){
                map[i][j] = in.nextChar();
            }
        }

        int combo = 0;

        while (true){
            int cnt = 0;
            isVisited = new boolean[12][6];

            for (int i = 0; i < 12; i++){
                for (int j = 0; j < 6; j++){
                    if (!isVisited[i][j] && map[i][j] != '.'){
                        cnt += bfs(new Axis(i, j, map[i][j]));
                    }
                }
            }

            if (cnt == 0) break;

            combo++;
            drop();
            //print();
        }

        System.out.println(combo);
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