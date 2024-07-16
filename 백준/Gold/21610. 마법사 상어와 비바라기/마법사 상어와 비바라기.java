/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] isCloud;
    static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };

    public static void init(){
        isCloud[n - 2][0] = isCloud[n - 2][1] = isCloud[n - 1][0] = isCloud[n - 1][1] = true;
    }

    public static void move(int d, int s){
        boolean[][] clone = new boolean[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (isCloud[i][j]){
                    int nx = (i + s * dx[d] + s * n) % n;
                    int ny = (j + s * dy[d] + s * n) % n;
                    clone[nx][ny] = isCloud[i][j];
                }
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                isCloud[i][j] = clone[i][j];
            }
        }
    }

    public static void raining(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (isCloud[i][j]){
                    map[i][j]++;
                }
            }
        }
    }

    public static void waterCopy(){
        int[][] clone = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                clone[i][j] = map[i][j];

                if (isCloud[i][j]){
                    for (int k = 0; k < 8; k++){
                        if (k % 2 == 1){
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (0 <= nx && nx < n && 0 <= ny && ny < n){
                                if (map[nx][ny] > 0){
                                    clone[i][j]++;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = clone[i][j];
            }
        }
    }

    public static void createCloud(){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (!isCloud[i][j]){
                    if (map[i][j] >= 2){
                        isCloud[i][j] = true;
                        map[i][j] -= 2;
                    }
                } else isCloud[i][j] = false;
            }
        }
    }

    public static int calculateWater() {
        int res = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                res += map[i][j];
            }
        }

        return res;
    }

    public static void print(){
        System.out.println();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%3d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        map = new int[n][n];
        isCloud = new boolean[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = in.nextInt();
            }
        }

        init();

        for (int i = 0; i < m; i++){
            int d = in.nextInt() - 1;
            int s = in.nextInt();
            move(d, s);
            raining();
            waterCopy();
            createCloud();
            //print();
        }

        int res = calculateWater();

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