/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static int n;
    static int[] durability;
    static int[] weight;

    public static int bruthforce(int depth, int broken){
        if (depth == n || broken == n){
            return broken;
        }

        int max = 0;

        if (durability[depth] <= 0){
            max = Math.max(max, bruthforce(depth + 1, broken));
        } else {
            boolean flag = false;

            for (int i = 0; i < n; i++){
                if (i != depth && durability[i] > 0){
                    durability[depth] -= weight[i];
                    durability[i] -= weight[depth];
    
                    int cnt = 0;
    
                    if (durability[depth] <= 0) cnt++;
                    if (durability[i] <= 0) cnt++;
    
                    max = Math.max(max, bruthforce(depth + 1, broken + cnt));
                    flag = true;
    
                    durability[depth] += weight[i];
                    durability[i] += weight[depth];
                }
            }
    
            if (!flag) max = Math.max(max, bruthforce(depth + 1, broken));
        }

        return max;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        n = in.nextInt();
        durability = new int[n];
        weight = new int[n];

        for (int i = 0; i < n; i++){
            durability[i] = in.nextInt();
            weight[i] = in.nextInt();
        }

        int max = bruthforce(0, 0);

        System.out.println(max);
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