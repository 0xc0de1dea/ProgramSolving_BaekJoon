import java.util.HashMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static HashMap<String, Integer> hm = new HashMap<>();
    static int[] dx = { 0, 0, -1, 1, 1, 1, -1, -1 };
    static int[] dy = { 1, -1, 0, 0, 1, -1, 1, -1 };

    public static boolean isValid(int x, int y){
        return 1 <= x && x <= 8 && 1 <= y && y <= 8;
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        char[] startKing = in.nextString().toCharArray();
        char[] startStone = in.nextString().toCharArray();

        int ky = startKing[0] - 'A' + 1;
        int kx = startKing[1] - '0';
        int sy = startStone[0] - 'A' + 1;
        int sx = startStone[1] - '0';

        hm.put("R", 0);
        hm.put("L", 1);
        hm.put("B", 2);
        hm.put("T", 3);
        hm.put("RT", 4);
        hm.put("LT", 5);
        hm.put("RB", 6);
        hm.put("LB", 7);

        int n = in.nextInt();

        for (int i = 0; i < n; i++){
            String order = in.nextString();
            int nkx = kx + dx[hm.get(order)];
            int nky = ky + dy[hm.get(order)];
            int nsx = sx + dx[hm.get(order)];
            int nsy = sy + dy[hm.get(order)];

            if (isValid(nkx, nky)){
                if (nkx == sx && nky == sy){
                    if (isValid(nsx, nsy)){
                        kx = nkx; ky = nky;
                        sx = nsx; sy = nsy;
                    }
                } else {
                    kx = nkx; ky = nky;
                }
            }
        }

        System.out.printf("%c%d\n", (char)(ky + 'A' - 1), kx);
        System.out.printf("%c%d", (char)(sy + 'A' - 1), sx);
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