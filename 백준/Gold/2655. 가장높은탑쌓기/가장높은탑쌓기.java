import java.util.ArrayList;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Brick {
    int area;
    int height;
    int weight;

    public Brick(int area, int height, int weight){
        this.area = area;
        this.height = height;
        this.weight = weight;
    }
}

class DP {
    int val;
    ArrayList<Integer> list;

    public DP(int val, ArrayList<Integer> list){
        this.val = val;
        this.list = list;
    }
}

public class Main {
    static int n, m;
    static Brick[] bricks;
    static DP[][] dp;
    static boolean[] isVisited;

    public static DP dp(int depth, int idx){
        if (depth >= n){
            return new DP(0, new ArrayList<>());
        }

        if (dp[depth][idx].val > 0){
            return dp[depth][idx];
        }

        DP max = new DP(0, new ArrayList<>());

        for (int i = 1; i <= n; i++){
            if (!isVisited[i]){
                if (bricks[idx].area > bricks[i].area && bricks[idx].weight > bricks[i].weight){
                    isVisited[i] = true;

                    DP res = dp(depth + 1, i);

                    ArrayList<Integer> list = new ArrayList<>();
                    for (int item : res.list){
                        list.add(item);
                    }

                    DP clone = new DP(res.val, list);

                    if (max.val < res.val + bricks[i].height){
                        max.val = res.val + bricks[i].height;
                        clone.list.add(i);
                        max.list = clone.list;
                    }

                    isVisited[i] = false;
                }
            }
        }

        return dp[depth][idx] = max;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        n = in.nextInt();
        bricks = new Brick[n + 1];
        bricks[0] = new Brick(10000, 0, 10000);
        dp = new DP[101][101];
        isVisited = new boolean[n + 1];

        for (int i = 1; i <= n; i++){
            int a = in.nextInt();
            int h = in.nextInt();
            int w = in.nextInt();
            bricks[i] = new Brick(a, h, w);
        }

        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++){
                dp[i][j] = new DP(0, new ArrayList<>());
            }
        }

        DP res = dp(0, 0);
        sb.append(res.list.size()).append('\n');

        for (int val : res.list){
            sb.append(val).append('\n');
        }

        System.out.println(sb);
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