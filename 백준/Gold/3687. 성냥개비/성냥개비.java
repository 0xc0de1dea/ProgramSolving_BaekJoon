import java.util.Arrays;
import java.util.Comparator;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Match {
    int num;
    int amount;

    public Match(int num, int amount){
        this.num = num;
        this.amount = amount;
    }
}

public class Main {
    static String[][][] maxDP;
    static String[][][] minDP;
    static int[] match;

    public static String maxDP(int depth, int rem, int num){
        if (rem < 0) return null;

        if (maxDP[depth][rem][num] != null) return maxDP[depth][rem][num];

        if (rem == 0) return "";

        String ret = null;
        String max = null;

        for (int i = 0; i <= 9; i++){
            String res = maxDP(depth + 1, rem - match[num], i);

            if (res == null) continue;
            else if (max == null) max = res;

            if (max.length() < res.length()){
                max = res;
            } else if (max.length() == res.length()){
                if (max.compareTo(res) < 0){
                    max = res;
                }
            }
        }

        if (max != null) ret = String.valueOf(num) + max;

        return maxDP[depth][rem][num] = ret;
    }

    public static String minDP(int depth, int rem, int num){
        if (rem < 0) return null;

        if (minDP[depth][rem][num] != null) return minDP[depth][rem][num];

        if (rem == 0) return "";

        String ret = null;
        String min = null;

        for (int i = 0; i <= 9; i++){
            String res = minDP(depth + 1, rem - match[num], i);

            if (res == null) continue;
            else if (min == null) min = res;

            if (min.length() > res.length()){
                min = res;
            } else if (min.length() == res.length()){
                if (min.compareTo(res) > 0){
                    min = res;
                }
            }
        }

        if (min != null) ret = String.valueOf(num) + min;

        return minDP[depth][rem][num] = ret;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int t = in.nextInt();
        match = new int[]{ 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };
        maxDP = new String[51][101][10];
        minDP = new String[51][101][10];
        
        while (t-- > 0){
            int n = in.nextInt();
            
            String min = "1234567890123456789012345678901234567890";
            String max = "";

            for (int i = 1; i <= 9; i++){
                String res = minDP(0, n, i);

                if (res == null) continue;

                if (min.length() > res.length()){
                    min = res;
                } else if(min.length() == res.length()){
                    if (min.compareTo(res) > 0){
                        min = res;
                    }
                }
            }

            for (int i = 1; i <= 9; i++){
                String res = maxDP(0, n, i);

                if (res == null) continue;

                if (max.length() < res.length()){
                    max = res;
                } else if(max.length() == res.length()){
                    if (max.compareTo(res) < 0){
                        max = res;
                    }
                }
            }

            sb.append(min).append(' ').append(max).append('\n');
        }

        System.out.println(sb);
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