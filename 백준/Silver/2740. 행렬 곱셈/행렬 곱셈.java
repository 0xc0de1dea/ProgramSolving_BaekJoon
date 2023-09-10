/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

public class Main {
    static int[][] matrix_mul(int[][] matA, int[][] matB){
        int n = matA.length, m = matB.length, l = matB[0].length;
        int[][] ret = new int[n][l];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < l; j++){
                for (int k = 0; k < m; k++){
                    ret[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }

        return ret;
    }

    static public void main(String[] args) throws Exception{
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrixA = new int[n][m];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) matrixA[i][j] = in.nextInt();

        m = in.nextInt();
        int k = in.nextInt();
        int[][] matrixB = new int[m][k];
        for (int i = 0; i < m; i++) for (int j = 0; j < k; j++) matrixB[i][j] = in.nextInt();

        int[][] matrixC = matrix_mul(matrixA, matrixB);
        for (int i = 0; i < n; i++){
            for (int j = 0; j < k; j++){
                sb.append(matrixC[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
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