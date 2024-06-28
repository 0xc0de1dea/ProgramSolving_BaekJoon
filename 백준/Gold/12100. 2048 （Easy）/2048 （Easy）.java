/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;

    public static int[][] clone(int[][] map){
        int[][] clone = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                clone[i][j] = map[i][j];
            }
        }

        return clone;
    }

    public static void moveUp(int[][] clone){
        boolean[][] isVisited = new boolean[n][n];

        for (int i = 1; i < n; i++){
            for (int j = 0; j < n; j++){
                if (clone[i][j] == 0) continue;

                int ptr = i;
                
                while (ptr > 0 && clone[--ptr][j] == 0);

                if ((!isVisited[ptr][j] && clone[ptr][j] == clone[i][j])){
                    clone[ptr][j] += clone[i][j];
                    isVisited[ptr][j] = true;
                } else if (clone[ptr][j] == 0) {
                    clone[ptr][j] = clone[i][j];
                } else {
                    clone[++ptr][j] = clone[i][j];
                }

                if (ptr != i) clone[i][j] = 0;
            }
        }
    }

    public static void moveDn(int[][] clone){
        boolean[][] isVisited = new boolean[n][n];

        for (int i = n - 2; i >= 0; i--){
            for (int j = 0; j < n; j++){
                if (clone[i][j] == 0) continue;

                int ptr = i;
                
                while (ptr < n - 1 && clone[++ptr][j] == 0);

                if ((!isVisited[ptr][j] && clone[ptr][j] == clone[i][j])){
                    clone[ptr][j] += clone[i][j];
                    isVisited[ptr][j] = true;
                } else if (clone[ptr][j] == 0) {
                    clone[ptr][j] = clone[i][j];
                } else {
                    clone[--ptr][j] = clone[i][j];
                }

                if (ptr != i) clone[i][j] = 0;
            }
        }
    }

    public static void moveLeft(int[][] clone){
        boolean[][] isVisited = new boolean[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 1; j < n; j++){
                if (clone[i][j] == 0) continue;

                int ptr = j;
                
                while (ptr > 0 && clone[i][--ptr] == 0);

                if ((!isVisited[i][ptr] && clone[i][ptr] == clone[i][j])){
                    clone[i][ptr] += clone[i][j];
                    isVisited[i][ptr] = true;
                } else if (clone[i][ptr] == 0) {
                    clone[i][ptr] = clone[i][j];
                } else {
                    clone[i][++ptr] = clone[i][j];
                }

                if (ptr != j) clone[i][j] = 0;
            }
        }
    }

    public static void moveRight(int[][] clone){
        boolean[][] isVisited = new boolean[n][n];

        for (int i = 0; i < n; i++){
            for (int j = n - 2; j >= 0; j--){
                if (clone[i][j] == 0) continue;

                int ptr = j;
                
                while (ptr < n - 1 && clone[i][++ptr] == 0);

                if ((!isVisited[i][ptr] && clone[i][ptr] == clone[i][j])){
                    clone[i][ptr] += clone[i][j];
                    isVisited[i][ptr] = true;
                } else if (clone[i][ptr] == 0) {
                    clone[i][ptr] = clone[i][j];
                } else {
                    clone[i][--ptr] = clone[i][j];
                }

                if (ptr != j) clone[i][j] = 0;
            }
        }
    }

    public static void print(int[][] map, int depth){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%2d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println(depth);
        System.out.println();
    }

    public static int backtracking(int[][] map, int depth){
        if (depth == 5){
            int max = 0;

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    max = Math.max(max, map[i][j]);
                }
            }

            return max;
        }

        int max = 0;

        int[][] clone = clone(map);
        moveUp(clone);
        max = Math.max(max, backtracking(clone, depth + 1));

        clone = clone(map);
        moveDn(clone);
        max = Math.max(max, backtracking(clone, depth + 1));

        clone = clone(map);
        moveLeft(clone);
        max = Math.max(max, backtracking(clone, depth + 1));

        clone = clone(map);
        moveRight(clone);
        max = Math.max(max, backtracking(clone, depth + 1));

        return max;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                map[i][j] = in.nextInt();
            }
        }

        int max = backtracking(map, 0);

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