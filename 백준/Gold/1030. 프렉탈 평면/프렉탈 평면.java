/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

 public class Main {
    static int s, n, k, r1, r2, c1, c2;
    // static StringBuilder board = new StringBuilder();
    static int[][] board;

    public static int pow(int a, int b){
        int res = 1;

        while (b > 0){
            if ((b & 1) == 1) res *= a;

            a *= a;
            b >>= 1;
        }
        
        return res;
    }

    public static void fractal(int sr, int sc, int er, int ec, int color){
        if (sr == er && sc == ec){
            //board.append(color);
            board[sr - r1][sc - c1] = color;
            return;
        }

        int partition = (er - sr + 1) / n;
        int offset = n - k >> 1;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                int nsr = sr + i * partition;
                int nsc = sc + j * partition;
                int ner = sr + (i + 1) * partition - 1;
                int nec = sc + (j + 1) * partition - 1;

                if (nec < c1 || ner < r1 || nsc > c2 || nsr > r2) continue;

                if (offset <= i && i < n - offset && offset <= j && j < n - offset){
                    fractal(nsr, nsc, ner, nec, 1);
                } else {
                    fractal(nsr, nsc, ner, nec, color | 0);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        s = in.nextInt();
        n = in.nextInt();
        k = in.nextInt();
        r1 = in.nextInt();
        r2 = in.nextInt();
        c1 = in.nextInt();
        c2 = in.nextInt();

        int size = pow(n, s);
        board = new int[r2 - r1 + 1][c2 - c1 + 1];

        fractal(0, 0, size - 1, size - 1, 0);
        
        for (int i = 0; i <= r2 - r1; i++){
            for (int j = 0; j <= c2 - c1; j++){
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }

        // for (int i = 0; i <= r2 - r1; i++){
        //     for (int j = 0; j <= c2 - c1; j++){
        //         sb.append(board.charAt(i * (c2 - c1) + j));
        //     }
        //     sb.append('\n');
        // }

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