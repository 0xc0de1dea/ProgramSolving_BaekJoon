import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static ArrayList<Integer> seq = new ArrayList<>();
    static int[][][] dp;
    static int[][] cost = {{0, 2, 2, 2, 2},
                            {0, 1, 3, 4, 3},
                            {0, 3, 1, 3, 4},
                            {0, 4, 3, 1, 3},
                            {0, 3, 4, 3, 1}};

    public static int search(int n, int depth, int l, int r){
        if (depth == n){
            return 0;
        }

        if (dp[depth][l][r] > 0){
            return dp[depth][l][r];
        }

        int left = 123456789;
        int right = 123456789;

        if (right != seq.get(depth)){
            left = search(n, depth + 1, seq.get(depth), r) + cost[l][seq.get(depth)];
        }
        
        if (left != seq.get(depth)){
            right = search(n, depth + 1, l, seq.get(depth)) + cost[r][seq.get(depth)];
        }

        return dp[depth][l][r] = Math.min(left, right);
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        while (true){
            int order = in.nextInt();

            if (order == 0) break;

            seq.add(order);
        }

        dp = new int[seq.size()][5][5];

        int res = search(seq.size(), 0, 0, 0);

        System.out.print(res);
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