import java.util.ArrayList;
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
    static int n;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int bfs(int[][] boundary, int x, int y, int sx, int sy, int ex, int ey, int id){
        Queue<Axis> queue = new LinkedList<>();
        queue.add(new Axis(x, y));

        int sum = boundary[x][y] == 0 ? map[x][y] : 0;
        boundary[x][y] = id;

        while (!queue.isEmpty()){
            Axis cur = queue.poll();

            for (int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (sx <= nx && nx <= ex && sy <= ny && ny <= ey){
                    if (boundary[nx][ny] == 0){
                        boundary[nx][ny] = id;
                        sum += map[nx][ny];
                        queue.add(new Axis(nx, ny));
                    }
                }
            }
        }

        return sum;
    }

    public static void print(int[][] boundary){
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                System.out.printf("%2d", boundary[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int bruthforce(){
        int min = 0x7f7f7f7f;

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                jump1 : for (int d1 = 1; ; d1++){
                    jump2 : for (int d2 = 1; ; d2++){
                        if (j - d1 < 1) break jump1;

                        if (i + d1 + d2 > n || j + d2 > n) break jump2;

                        int[][] boundary = new int[n + 1][n + 1];
                        int fiveSum = 0;

                        for (int k = 0; k <= d1; k++){
                            boundary[i + k][j - k] = 5;
                            boundary[i + d2 + k][j + d2 - k] = 5;
                            fiveSum += map[i + k][j - k] + map[i + d2 + k][j + d2 - k];
                        }

                        for (int k = 0; k <= d2; k++){
                            boundary[i + k][j + k] = 5;
                            boundary[i + d1 + k][j - d1 + k] = 5;
                            fiveSum += map[i + k][j + k] + map[i + d1 + k][j - d1 + k];
                        }

                        if (d1 == 1){
                            for (int k = 0; k < d2; k++){
                                boundary[i + 1 + k][j + k] = 5;
                                fiveSum += map[i + 1 + k][j + k];
                            }
                        } else if (d2 == 1){
                            for (int k = 0; k < d1; k++){
                                boundary[i + 1 + k][j - k] = 5;
                                fiveSum += map[i + 1 + k][j - k];
                            }
                        }

                        ///print(boundary);

                        fiveSum -= map[i][j] + map[i + d1][j - d1] + map[i + d2][j + d2] + map[i + d1 + d2][j - d1 + d2];

                        ArrayList<Integer> list = new ArrayList<>();

                        list.add(fiveSum + bfs(boundary, i + 1, j, 1, 1, n, n, 5));
                        list.add(bfs(boundary, 1, 1, 1, 1, i + d1 - 1, j, 1));
                        list.add(bfs(boundary, 1, n, 1, j, i + d2, n, 2));
                        list.add(bfs(boundary, n, 1, i + d1, 1, n, j - d1 + d2 - 1, 3));
                        list.add(bfs(boundary, n, n, i + d2 + 1, j - d1 + d2, n, n, 4));

                        //System.out.println(list);
                        list.sort(null);
                        //print(boundary);

                        //System.out.println(i + " " + j + " " + d1 + " " + d2);

                        min = Math.min(min, Math.abs(list.get(0) - list.get(4)));
                    }
                }
            }
        }

        return min;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                map[i][j] = in.nextInt();
            }
        }

        int min = bruthforce();

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