/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n, q;
    static int size;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static boolean[][] isVisited;

    public static int pow(int a, int b){
        int ret = 1;

        while (b > 0){
            if ((b & 1) == 1) ret *= a;

            a *= a;
            b >>= 1;
        }

        return ret;
    }

    public static void rotate(int l){
        int[][] clone = new int[size][size];
        int gridSize = pow(2, l);
        double delta = pow(2, l - 1) - 0.5;

        if (l == 0) delta = 0;

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                double centerRow = i / gridSize * gridSize + delta;
                double centerCol = j / gridSize * gridSize + delta;

                int nRow = (int)((j - centerCol) + centerRow);
                int nCol = (int)(-(i - centerRow) + centerCol);

                clone[nRow][nCol] = map[i][j];
            }
        }

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                map[i][j] = clone[i][j];
            }
        }
    }

    public static void melt(){
        int[][] clone = new int[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                int cnt = 0;

                for (int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (0 <= nx && nx < size && 0 <= ny && ny < size){
                        if (map[nx][ny] > 0){
                            cnt++;
                        }
                    }
                }

                if (cnt >= 3){
                    clone[i][j] = map[i][j];
                } else {
                    clone[i][j] = map[i][j] - 1;
                }
            }
        }

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                map[i][j] = clone[i][j];
            }
        }
    }

    public static int dfs(int x, int y){
        isVisited[x][y] = true;

        int cnt = 1;
        boolean flag = false;

        for (int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < size && 0 <= ny && ny < size){
                if (!isVisited[nx][ny] && map[nx][ny] > 0){
                    cnt += dfs(nx, ny);
                    flag = true;
                }
            }
        }

        if (!flag) return 1;

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        q = in.nextInt();
        size = pow(2, n);
        map = new int[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                map[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < q; i++){
            int l = in.nextInt();
            rotate(l);
            melt();
        }

        int cnt = 0;
        int max = 0;
        isVisited = new boolean[size][size];
        
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (map[i][j] > 0) cnt += map[i][j];
                
                if (!isVisited[i][j] && map[i][j] > 0){
                    int res = dfs(i, j);
                    max = Math.max(max, res);
                }
            }
        }

        System.out.println(cnt);
        System.out.println(max);
        //print();
    }

    public static void print(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.printf("%2d", map[i][j]);
            }
            System.out.println();
        }
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