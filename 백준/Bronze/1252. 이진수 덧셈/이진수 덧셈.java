/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        String a = in.nextString();
        String b = in.nextString();
        int max = Math.max(a.length(), b.length());

        int[] binA = new int[max];
        int[] binB = new int[max];
        int[] binC = new int[max + 1];
        int carry = 0;

        for (int i = a.length() - 1; i >= 0; i--) binA[a.length() - 1 - i] = a.charAt(i) - '0';
        for (int i = b.length() - 1; i >= 0; i--) binB[b.length() - 1 - i] = b.charAt(i) - '0';
        for (int i = 0; i < max; i++){
            int tot = binA[i] + binB[i] + carry;
            
            if (tot == 1){
                binC[i] = 1;
                carry = 0;
            }
            else if (tot == 2){
                binC[i] = 0;
                carry = 1;
            }
            else if (tot == 3){
                binC[i] = 1;
                carry = 1;
            }
        }
        if (carry == 1) binC[max] = 1;
        for (int i = max; i > 0; i--){
            if (binC[i] == 1) break;
            max--;
        }
        for (int i = max; i >= 0; i--) sb.append(binC[i]);

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
        while ((c = read()) <= 32);
        do sb.appendCodePoint(c);
        while ((c = read()) > 32);
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) <= 32);
        return (char)c;
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