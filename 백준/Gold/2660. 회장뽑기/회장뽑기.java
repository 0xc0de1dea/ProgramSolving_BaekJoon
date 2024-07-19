import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static int[][] dist;
    static final int INF = 0x3f3f3f3f;

    public static void floydWarshall(){
        for (int k = 1; k <= n; k++){
            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                if (i != j){
                    dist[i][j] = INF;
                }
            }
        }

        while (true){
            int a = in.nextInt();
            int b = in.nextInt();

            if (a == -1 && b == -1) break;

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        floydWarshall();

        int[] score = new int[n + 1];

        for (int i = 1; i <= n; i++){
            int max = 0;

            for (int j = 1; j <= n; j++){
                max = Math.max(max, dist[i][j]);
            }

            score[i] = max;
        }

        int minScore = INF;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i <= n; i++){
            minScore = Math.min(minScore, score[i]);
        }

        for (int i = 1; i <= n; i++){
            if (minScore == score[i]){
                list.add(i);
            }
        }

        sb.append(minScore).append(' ').append(list.size()).append('\n');

        for (int candidate : list){
            sb.append(candidate).append(' ');
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