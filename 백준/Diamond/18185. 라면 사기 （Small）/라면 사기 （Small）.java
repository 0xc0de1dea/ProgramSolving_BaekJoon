import java.util.Arrays;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    public static int buy1(int[] ramen, int cur){
        return 3 * ramen[cur];
    }

    public static int buy2(int[] ramen, int cur){
        int min = Math.min(ramen[cur], ramen[cur + 1]);
        ramen[cur] -= min;
        ramen[cur + 1] -= min;

        return 5 * min;
    }

    public static int buy3(int[] ramen, int cur){
        int min = Math.min(ramen[cur], Math.min(ramen[cur + 1], ramen[cur + 2]));
        ramen[cur] -= min;
        ramen[cur + 1] -= min;
        ramen[cur + 2] -= min;

        return 7 * min;
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int[] ramen = new int[n + 3];

        for (int i = 1; i <= n; i++){
            ramen[i] = in.nextInt();
        }

        int cost = 0;

        for (int i = 1; i <= n; i++){
            if (ramen[i] == 0) continue;

            if (ramen[i + 1] > ramen[i + 2]){
                int min = Math.min(ramen[i], ramen[i + 1] - ramen[i + 2]);
                ramen[i] -= min;
                ramen[i + 1] -= min;
                cost += 5 * min;
                cost += buy3(ramen, i);
                cost += buy1(ramen, i);
            } else {
                cost += buy3(ramen, i);
                cost += buy2(ramen, i);
                cost += buy1(ramen, i);
            }
        }

        System.out.println(cost);
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