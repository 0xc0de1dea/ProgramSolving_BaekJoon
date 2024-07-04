/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main{
    static int n, m, h;
    static int[][] ladder;
    static final int INF = 0x7f7f7f7f;
    
    public static boolean go(){
        for (int start = 0; start < n; start++){
            int end = start;

            for (int i = 0; i < h; i++){
                if (ladder[i][end] == 1){
                    end++;
                } else if (end > 0 && ladder[i][end - 1] == 1){
                    end--;
                }
            }

            if (start != end) return false;
        }

        return true;
    }

    static int cnt = INF;

    public static void backtracking(int start, int install){
        if  (install > 3 || cnt <= install) return;

        if (go()) cnt = Math.min(cnt, install);

        for (int i = start; i < h * (n - 1); i++){
            int sh = i % h;
            int sn = i / h;

            if (ladder[sh][sn] == 0 && ladder[sh][sn + 1] == 0){
                if (sn > 0 && ladder[sh][sn - 1] == 1) continue;

                ladder[sh][sn] = 1;
                backtracking(i + 1, install + 1);
                ladder[sh][sn] = 0;
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        m = in.nextInt();
        h = in.nextInt();
        ladder = new int[h][n];

        for (int i = 0; i < m; i++){
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            ladder[a][b] = 1;
        }

        backtracking(0, 0);
        
        System.out.println(cnt == INF ? -1 : cnt);
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