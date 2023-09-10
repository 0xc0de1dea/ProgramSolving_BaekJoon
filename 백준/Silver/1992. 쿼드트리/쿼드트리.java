/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static void rec(int[][] paper, int size, int x, int y){
        int totSum = paper[x + size - 1][y + size - 1] - paper[x + size - 1][y - 1] - paper[x - 1][y + size - 1] + paper[x - 1][y - 1];
        
        if (totSum == size * size) sb.append(1);
        else if (totSum == 0) sb.append(0);
        else {
            int half = size >> 1;
            sb.append("(");
            rec(paper, half, x, y);
            rec(paper, half, x, y + half);
            rec(paper, half, x + half, y);
            rec(paper, half, x + half, y + half);
            sb.append(")");
        }
    }

    static public void main(String[] args) throws Exception{
        Reader in = new Reader();
        
        int n = in.nextInt();
        int[][] paper = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= n; j++){
                int t;
                while(((t = in.read()) <= 32));
                paper[i][j] = (t - 48) + paper[i - 1][j] + paper[i][j - 1] - paper[i - 1][j - 1];
            }
        }

        rec(paper, n, 1, 1);
        System.out.println(sb);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32); //{ if (size < 0) return -1; }
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
        while ((c = read()) <= 32);
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
        return 96 < c && c < 123;
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}