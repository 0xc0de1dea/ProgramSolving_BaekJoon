/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static boolean flag = false;

    public static boolean chk(char[][] board, int r, int c){
        for (int i = r - 1; i >= 0; i--){
            if (board[i][c] == 'O' || board[i][c] == 'T'){
                break;
            }

            if (board[i][c] == 'S'){
                return true;
            }
        }

        for (int i = r + 1; i < n; i++){
            if (board[i][c] == 'O' || board[i][c] == 'T'){
                break;
            }

            if (board[i][c] == 'S'){
                return true;
            }
        }

        for (int i = c - 1; i >= 0; i--){
            if (board[r][i] == 'O' || board[r][i] == 'T'){
                break;
            }

            if (board[r][i] == 'S'){
                return true;
            }
        }

        for (int i = c + 1; i < n; i++){
            if (board[r][i] == 'O' || board[r][i] == 'T'){
                break;
            }

            if (board[r][i] == 'S'){
                return true;
            }
        }

        return false;
    }

    public static void backtracking(char[][] board, int depth, int put){
        if (depth == 3){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (board[i][j] == 'T'){
                        if (chk(board, i, j)){
                            return;
                        }
                    }
                }
            }

            flag = true;
            return;
        }

        for (int i = put + 1; i < n * n; i++){
            if (flag) break;

            int r = i / n;
            int c = i % n;

            if (board[r][c] == 'S' || board[r][c] == 'T') continue;

            board[r][c] = 'O';
            backtracking(board, depth + 1, i);
            board[r][c] = 'X';
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                board[i][j] = in.nextChar();
            }
        }

        backtracking(board, 0, -1);

        System.out.println(flag ? "YES" : "NO");
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