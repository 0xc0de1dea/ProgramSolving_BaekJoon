/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static int[][] rotate(int[][] board, int angle){
        final int HALF_ROW = board.length >> 1;
        final int HALF_COL = board.length >> 1;

        int[] rotRow = new int[] { -1, -1, -1, 0, 1, 1, 1, 0 };
        int[] rotCol = new int[] { -1, 0, 1, 1, 1, 0, -1, -1 };

        int[][] res = new int[board.length][board.length];

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++){
                res[i][j] = board[i][j];
            }
        }

        for (int i = 1; i <= HALF_ROW; i++){
            int dist = i;
            int skip = angle / 45;

            for (int j = 0; j < 8; j++){
                if (angle >= 0){
                    res[HALF_ROW + (dist * (rotRow[(j + skip) % 8]))][HALF_COL + (dist * (rotCol[(j + skip) % 8]))] = board[HALF_ROW + (dist * rotRow[j])][HALF_COL + (dist * rotCol[j])];
                } else {
                    res[HALF_ROW + (dist * (rotRow[(j + skip + 8) % 8]))][HALF_COL + (dist * (rotCol[(j + skip + 8) % 8]))] = board[HALF_ROW + (dist * rotRow[j])][HALF_COL + (dist * rotCol[j])];
                }
            }
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();
        
        int c = in.nextInt();
        int r = in.nextInt();
        int k = in.nextInt();

        if (k > c * r){
            System.out.print(0);
            return;
        }

        int[][] board = new int[r][c];

        int startRow = 0;
        int startCol = 0;
        int endRow = r - 1;
        int endCol = c - 1;

        int i = -1;
        int j = 0;

        int cnt = 0;

        jump : while (startRow <= endRow && startCol <= endCol){
            while (i < endRow){
                i++;
                ++cnt;
                
                if (cnt == k){
                    sb.append(j + 1).append(' ').append(i + 1);
                    break jump;
                }
            }

            startCol++;

            while (j < endCol){
                j++;
                ++cnt;

                if (cnt == k){
                    sb.append(j + 1).append(' ').append(i + 1);
                    break jump;
                }
            }

            endRow--;

            while (i > startRow){
                i--;
                ++cnt;

                if (cnt == k){
                    sb.append(j + 1).append(' ').append(i + 1);
                    break jump;
                }
            }

            endCol--;

            while (j > startCol){
                j--;
                ++cnt;

                if (cnt == k){
                    sb.append(j + 1).append(' ').append(i + 1);
                    break jump;
                }
            }

            startRow++;
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