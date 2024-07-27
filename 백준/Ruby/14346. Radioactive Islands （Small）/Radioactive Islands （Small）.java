/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

import java.util.*;

public class Main {

    static final int IT = 1600;
    static final int W = 2;
    static final double H = 0.5;

    static final double MIN_Y_BOUND = -13.0;
    static final double MAX_Y_BOUND = 13.0;
    static final double X_START = -10.0;
    static final double X_END = 10.0;
    static final int PATH_LEN = (int)((X_END - X_START) / H) + 1;
    static final double M = (MAX_Y_BOUND - MIN_Y_BOUND) / (W * W * IT);  // every iteration each point can move [0, W^2] up or down in a triangular shape

    public static void main(String[] args) throws Exception {
        //System.setIn(new java.io.FileInputStream("input.in"));
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int cases = in.nextInt();
        
        for (int caseNum = 0; caseNum < cases; caseNum++) {
            sb.append("Case #" + (caseNum + 1) + ": " + radioactiveIslands(in)).append('\n');
        }

        System.out.print(sb);
    }

    private static double radioactiveIslands(Reader in) throws Exception {
        int N = in.nextInt();
        double A = in.nextDouble();
        double B = in.nextDouble();

        double[] C = new double[N];

        for (int i = 0; i < N; i++) {
            C[i] = in.nextDouble();
        }

        ArrayList<Double> regions = new ArrayList<>();
        regions.add(MIN_Y_BOUND);
        regions.add(MAX_Y_BOUND);

        for (double c : C) {
            regions.add(c);
        }

        Collections.sort(regions);

        double result = Double.POSITIVE_INFINITY;

        for (int i = 0; i < regions.size() - 1; i++) {
            int mid = (PATH_LEN - 1) / 2;
            double midY = (regions.get(i) + regions.get(i + 1)) / 2.0;
            ArrayList<double[]> path = new ArrayList<>();

            for (int j = 0; j < mid; j++) {
                path.add(new double[] { X_START + j * H, A + 1.0 * j / mid * (midY - A) });
            }
            for (int j = mid; j < PATH_LEN; j++) {
                path.add(new double[] { X_START + j * H, midY + 1.0 * (j - mid) / (PATH_LEN - 1 - mid) * (B - midY) });
            }

            result = Math.min(result, hillClimbing(C, path));
        }

        return result;
    }

    private static double hillClimbing(double[] C, ArrayList<double[]> path) {
        for (int iter = 0; iter < IT; iter++) {
            boolean isChanged = false;

            for (int i = 0; i < path.size(); i++) {
                double up = fi(C, path, i, M);
                double down = fi(C, path, i, -M);

                if (up < down) {
                    move(path, i, M);
                    isChanged = true;
                } else if (down < up) {
                    move(path, i, -M);
                    isChanged = true;
                }
            }

            if (!isChanged) {
                break;
            }
        }
        return F(C, path);
    }

    private static double fi(double[] C, ArrayList<double[]> path, int i, double m) {
        ArrayList<double[]> newPath = new ArrayList<>();

        for (int j = Math.max(0, i - W); j <= Math.min(i + W, path.size() - 1); j++) {
            newPath.add(new double[] { path.get(j)[0], path.get(j)[1] + dy(path, m, i, j) });
        }

        return F(C, newPath);
    }

    private static void move(ArrayList<double[]> path, int i, double m) {
        for (int j = Math.max(0, i - W); j <= Math.min(i + W, path.size() - 1); j++) {
            path.get(j)[1] += dy(path, m, i, j);
        }
    }

    private static double dy(ArrayList<double[]> path, double m, int i, int j) {
        if (j == 0 || j == path.size() - 1) {
            return 0.0;  // no change on begin and end
        }

        return m * (W - Math.abs(j - i));
    }

    private static double F(double[] C, ArrayList<double[]> path) {
        double dose = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            dose += f(C, path.get(i)[0], path.get(i)[1], H, path.get(i + 1)[1] - path.get(i)[1]);
        }

        return dose;
    }

    private static double f(double[] C, double x, double y, double dx, double dy) {
        return (1.0 + D(C, x, y)) * Math.sqrt(dx * dx + dy * dy);
    }

    private static double D(double[] C, double x, double y) {
        double dose = 0.0;

        for (double c : C) {
            double dSquare = x * x + (y - c) * (y - c);

            if (dSquare < Double.MIN_VALUE) {
                return Double.POSITIVE_INFINITY;
            }

            dose += 1.0 / dSquare;
        }

        return dose;
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