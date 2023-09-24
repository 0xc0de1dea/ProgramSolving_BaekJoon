/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

//import java.io.FileInputStream;

class Point {
    double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static double[] abilities = new double[8];
    static int[] order = new int[8];
    static boolean[] isVisited = new boolean[8];
    static int counting = 40320;
    static final double COS45 = Math.sqrt(2) / 2;
    static double[] dirX = new double[] { 0, 0, 1, 1, 0, 0, -1, -1 };
    static double[] dirY = new double[] { 1, 1, 0, 0, -1, -1, 0, 0 };

    static double ccw(Point p1, Point p2, Point p3){
        double crossProduct = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
        return crossProduct;
    }

    static Point rotate45(Point p0){
        Point p1 = new Point(COS45 * p0.x + COS45 * p0.y, -COS45 * p0.x + COS45 * p0.y);
        return p1;
    }

    static boolean check(){
        Point[] t = new Point[8];
        
        for (int i = 0; i < 8; i++){
            t[i] = new Point(dirX[i] * abilities[order[i]], dirY[i] * abilities[order[i]]);
            if ((i & 1) == 1) t[i] = rotate45(t[i]);
        }
        for (int i = 0; i < 8; i++){
            if (ccw(t[i], t[(i + 1) % 8], t[(i + 2) % 8]) > 0) return false;
        }

        return true;
    }

    static void permutation(int depth){
        if (depth == 8){
            counting -= check() ? 0 : 1;
            return;
        }

        for (int i = 0; i < 8; i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                order[depth] = i;
                permutation(depth + 1);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] argu) throws Exception {
        //System.setIn(new FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) abilities[i] = in.nextDouble();

        permutation(0);

        System.out.print(counting);
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
        while (isAlphabet(c = read()));
        return sb.toString();
    }

    char nextChar() throws Exception {
        char ch = ' ';
        byte c;
        while ((c = read()) <= 32);
        do ch = (char)c;
        while (isAlphabet(c = read()));
        return ch;
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