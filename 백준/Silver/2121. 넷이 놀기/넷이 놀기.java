import java.util.HashMap;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

class Point {
    int x, y;
    private long cashing = 1;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Point)){
            return false;
        }

        Point other = (Point)o;

        if (!other.canEqual(this)){
            return false;
        }

        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode(){
        long hash = cashing;
        final int PRIME = 31;

        if (hash == 1){
            hash = PRIME * hash + x;
            hash = PRIME * hash + y;
            cashing = hash;
        }

        return (int)hash;
    }

    protected boolean canEqual(Object other){
        return other instanceof Point;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        Point[] point = new Point[n];
        HashMap<Point, Integer> hm = new HashMap<>();

        for (int i = 0; i < n; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            point[i] = new Point(x, y);

            Point p = new Point(x, y);
            hm.put(p, 1);
            //hm.put(p, hm.getOrDefault(hm.get(p), 0) + 1);
            
            // if (hm.containsKey(p)){
            //     hm.put(p, hm.get(p) + 1);
            // } else {
            //     hm.put(p, 1);
            // }
        }

        int cnt = 0;

        for (int i = 0; i < n; i++){
            Point p = point[i];
            
            if (hm.containsKey(new Point(p.x + a, p.y)) && hm.containsKey(new Point(p.x, p.y + b)) && hm.containsKey(new Point(p.x + a, p.y + b))){
                cnt++;
                //cnt += hm.get(new Point(p.x, p.y + b)) * hm.get(new Point(p.x + a, p.y)) * hm.get(new Point(p.x + a, p.y + b));
            }
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