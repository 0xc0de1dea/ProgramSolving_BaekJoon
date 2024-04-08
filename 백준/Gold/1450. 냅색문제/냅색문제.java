import java.util.ArrayList;
import java.util.Collections;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int c;
    static ArrayList<Integer> setA = new ArrayList<>();
    static ArrayList<Integer> setB = new ArrayList<>();

    public static void combine(int[] weight, int idx, int end, int sum, char type){
        if (sum > c) return;
        if (idx > end){
            if (type == 'A') setA.add(sum);
            else if (type == 'B') setB.add(sum);
            return;
        }

        combine(weight, idx + 1, end, sum, type);
        combine(weight, idx + 1, end, sum + weight[idx], type);
    }

    public static int upperbound(int target, int s, int e){
        while (s < e){
            int m = (s + e) / 2;

            if (setA.get(m) <= target){
                s = m + 1;
            } else {
                e = m;
            }
        }

        return s;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        c = in.nextInt();
        int[] weight = new int[n];

        for (int i = 0; i < n; i++){
            weight[i] = in.nextInt();
        }

        combine(weight, 0, n / 2 - 1, 0, 'A');
        combine(weight, n / 2, n - 1, 0, 'B');
        Collections.sort(setA);

        int cnt = 0;

        for (int i = 0; i < setB.size(); i++){
            cnt += upperbound(c - setB.get(i), 0, setA.size());
        }

        System.out.print(cnt);
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