import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static int getMax(ArrayList<Integer> list){
        int max = 0;

        for (int i = 0; i < 3; i++){
            int sum = 0;

            for (int j = 0; j < list.size(); j++){
                if (i != j){
                    sum += list.get(j);
                }
            }

            max = Math.max(max, sum);
        }

        return max;
    }

    public static int backtracking(int depth, int r, int c, int type){
        if (depth == 1 && type == 1) return map[r][c];
        if (depth == 2 && type == 1) return map[r][c];
        if (depth == 3) return map[r][c];

        if (isVisited[r][c]) return 0;

        isVisited[r][c] = true;

        int max = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m){
                if (!isVisited[nx][ny]){
                    max = Math.max(max, backtracking(depth + 1, nx, ny, 0));

                    if (depth == 1) {
                        list.add(backtracking(depth + 1, nx, ny, 1));
                    }
                }
            }
        }

        max = Math.max(max, getMax(list));

        isVisited[r][c] = false;

        return map[r][c] + max;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        map = new int[n][m];
        isVisited = new boolean[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                map[i][j] = in.nextInt();
            }
        }

        int max = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                max = Math.max(max, backtracking(0, i, j, 0));
            }
        }

        System.out.println(max);
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