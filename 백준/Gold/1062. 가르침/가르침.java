/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static int k;
    static int bit = 0;
    static int max = 0;

    public static int bitCnt(int v){
        v = (v & 0x55555555) + ((v >> 1) & 0x55555555);
        v = (v & 0x33333333) + ((v >> 2) & 0x33333333);
        v = (v & 0x0f0f0f0f) + ((v >> 4) & 0x0f0f0f0f);
        v = (v & 0x00ff00ff) + ((v >> 8) & 0x00ff00ff);
        v = (v & 0x0000ffff) + ((v >> 16) & 0x0000ffff);
        return v;
    }

    public static void backtracking(int[] bitWord, int bit, int idx, int known){
        if (known == k){
            int cnt = 0;

            for (int i = 0; i < bitWord.length; i++){
                // if (bitCnt(bit) >= bitCnt(bitWord[i])){
                    int masked = bit | bitWord[i];

                    if (masked <= bit){
                        cnt++;
                    }
                // }
            }

            max = Math.max(max, cnt);
            return;
        }

        if (idx > 25){
            return;
        }

        if (idx == 0 || idx == 2 || idx == 8 || idx == 13 || idx == 19){
            backtracking(bitWord, bit |= (1 << idx), idx + 1, known + 1);
        } else {
            backtracking(bitWord, bit, idx + 1, known);
            backtracking(bitWord, bit |= (1 << idx), idx + 1, known + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        k = in.nextInt();

        int[] bitWord = new int[n];

        for (int i = 0; i < n; i++){
            char[] word = in.nextString().toCharArray();

            for (char c : word){
                bitWord[i] |= (1 << (c - 'a'));
            }
        }

        if (k < 5){
            System.out.print(0);
            return;
        }

        backtracking(bitWord, 0, 0, 0);

        System.out.print(max);
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