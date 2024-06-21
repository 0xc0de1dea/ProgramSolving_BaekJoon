/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static int[][] hate;
    static boolean[] isVisited;
    static StringBuilder ans = new StringBuilder();

    public static boolean dfs(int depth, int cnt){
        if (depth == n + 1){
            ans.append(cnt).append('\n');

            for (int i = 1; i <= n; i++){
                if (isVisited[i]){
                    ans.append(i).append(' ');
                }
            }

            ans.append('\n');
            ans.append(n - cnt).append('\n');

            for (int i = 1; i <= n; i++){
                if (!isVisited[i]){
                    ans.append(i).append(' ');
                }
            }

            ans.append('\n');

            return true;
        }

        boolean flagBlue = true;
        boolean flagWhite = true;

        for (int i = 1; i < depth; i++){
            if (isVisited[i] && (hate[i][depth] == 1 || hate[depth][i] == 1)){
                flagBlue = false;
            }

            if (!isVisited[i] && (hate[i][depth] == 1 || hate[depth][i] == 1)){
                flagWhite = false;
            }
        }

        if (flagBlue){
            isVisited[depth] = true;

            if (dfs(depth + 1, cnt + 1)){
                return true;
            }
        }

        if (flagWhite){
            isVisited[depth] = false;

            if (dfs(depth + 1, cnt)){
                return true;
            }
        }

        return false;
    }

    public static void main(String args[]) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        n = in.nextInt();
        hate = new int[n + 1][n + 1];
        isVisited = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++){
            int cnt = in.nextInt();

            while (cnt-- > 0){
                int trg = in.nextInt();
                hate[i][trg] = 1;
                hate[trg][i] = 1;
            }
        }

        dfs(1, 0);

        System.out.print(ans);
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
        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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