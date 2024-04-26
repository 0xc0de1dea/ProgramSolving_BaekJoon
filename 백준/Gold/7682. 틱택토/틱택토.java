/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int[] x = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2 };
    static int[] y = { 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 0, 0, 1, 1, 1, 2, 2, 2, 0, 1, 2, 2, 1, 0 };

    public static int bingoCnt(char[][] tictactoe, char type){
        int cnt = 0;

        for (int i = 0; i < 8; i++){
            boolean flag = true;

            for (int j = 0; j < 3; j++){
                if (tictactoe[x[i * 3 + j]][y[i * 3 + j]] != type){
                    flag = false;
                    break;
                }
            }

            if (flag) cnt++;
        }

        return cnt;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        while (true){
            String tictactoe = in.nextString();
            
            if (tictactoe.equals("end")) break;

            char[][] board = new char[3][3];
            int xCnt = 0;
            int oCnt = 0;
            
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    board[i][j] = tictactoe.charAt(i * 3 + j);

                    if (board[i][j] == 'X') xCnt++;
                    else if (board[i][j] == 'O') oCnt++;
                }
            }

            int xBingo = bingoCnt(board, 'X');
            int oBingo = bingoCnt(board, 'O');

            if (xCnt == oCnt + 1 && xBingo >= 1 && oBingo == 0){
                sb.append("valid").append('\n');
            } else if (xCnt == oCnt && xBingo == 0 && oBingo == 1){
                sb.append("valid").append('\n');
            } else if (xCnt == 5 && oCnt == 4 && xBingo == 0 && oBingo == 0){
                sb.append("valid").append('\n');
            } else {
                sb.append("invalid").append('\n');
            }
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