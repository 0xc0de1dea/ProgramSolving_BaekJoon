import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static char[] seq;
    static int[] counting;
    static char[] ans;
    static boolean[][][][][] dp;

    public static boolean dp(int idx, int b, int c){
        if (idx >= seq.length) return true;

        if (b < 0) b = 0;
        if (c < 0) c = 0;

        if (dp[idx][b][c][counting[1]][counting[2]]) return false;

        dp[idx][b][c][counting[1]][counting[2]] = true;

        if (counting[0] > 0){
            counting[0]--;
            boolean res = dp(idx + 1, b - 1, c - 1);
            counting[0]++;

            if (res){
                ans[idx] = 'A';
                return true;
            }
        }
        
        if (b <= 0){
            if (counting[1] > 0){
                counting[1]--;
                boolean res = dp(idx + 1, 1, c - 1);
                counting[1]++;
                
                if (res){
                    ans[idx] = 'B';
                    return true;
                }
            }
        }
        
        if (c <= 0){
            if (counting[2] > 0){
                counting[2]--;
                boolean res = dp(idx + 1, b - 1, 2);
                counting[2]++;

                if (res){
                    ans[idx] = 'C';
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        seq = in.nextString().toCharArray();
        counting = new int[3];
        dp = new boolean[51][2][3][51][51];
        ans = new char[seq.length];

        for (int i = 0; i < seq.length; i++){
            counting[seq[i] - 'A']++;
        }

        // for (int i = 0; i < 51; i++){
        //     for (int j = 0; j < 2; j++){
        //         for (int k = 0; k < 3; k++){
        //             for (int p = 0; p < 51; p++){
        //                 for (int q = 0; q < 51; q++){
        //                     dp[i][j][k][p][q] = -1;
        //                 }
        //             }
        //         }
        //     }
        // }

        boolean res = dp(0, 0, 0);
        
        if (res){
            for (char c : ans){
                sb.append(c);
            }

            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
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