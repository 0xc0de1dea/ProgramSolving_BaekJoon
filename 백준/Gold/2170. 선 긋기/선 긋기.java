import java.util.Arrays;

class Line implements Comparable<Line>{
    public int l, r;

    public Line(int l, int r){
        this.l = l;
        this.r = r;
    }

    @Override
    public int compareTo(Line next){
        return this.l - next.l;
    }
}

public class Main {
    static final int INF = (int) (1e9 + 1);

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++){
            lines[i] = new Line(in.nextInt(), in.nextInt());
        }

        Arrays.sort(lines);
        int l = -INF, r = -INF;
        int len = 0;

        for (int i = 0; i < n; i++){
            if (r < lines[i].l){
                len += r - l;
                l = lines[i].l;
                r = lines[i].r;
            }
            else {
                r = Math.max(r, lines[i].r);
            }
        }

        len += r - l;
        System.out.print(len);
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
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
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}