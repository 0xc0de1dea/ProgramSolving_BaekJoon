import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static ArrayList<ArrayList<Integer>> edges;
    static boolean[] isVisited;

    public static int dfs(int start){
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        isVisited[start] = true;
        int cnt = 0;

        while (!stack.isEmpty()){
            int cur = stack.pop();
            
            if (start != cur) cnt++;

            for (int next : edges.get(cur)){
                if (!isVisited[next]){
                    stack.push(next);
                    isVisited[next] = true;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        double win = in.nextDouble();
        double lose = in.nextDouble();
        double draw = in.nextDouble();

        double[][] dp = new double[21][3001];
        dp[0][2000] = 1;

        for (int i = 1; i <= 20; i++){
            for (int j = 1050; j <= 2950; j += 50){
                dp[i][j - 50] += dp[i - 1][j] * lose;
                dp[i][j + 50] += dp[i - 1][j] * win;
                dp[i][j] += dp[i - 1][j] * draw;
            }
        }

        double bronze = 0, silver = 0, gold = 0, platinum = 0, diamond = 0;

        for (int i = 1000; i < 1500; i++) bronze += dp[20][i];
        for (int i = 1500; i < 2000; i++) silver += dp[20][i];
        for (int i = 2000; i < 2500; i++) gold += dp[20][i];
        for (int i = 2500; i < 3000; i++) platinum += dp[20][i];
        for (int i = 3000; i <= 3000; i++) diamond += dp[20][i];

        System.out.printf("%.8f\n", bronze);
        System.out.printf("%.8f\n", silver);
        System.out.printf("%.8f\n", gold);
        System.out.printf("%.8f\n", platinum);
        System.out.printf("%.8f", diamond);
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