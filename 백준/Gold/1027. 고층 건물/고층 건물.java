class Line {
    public Point p1, p2;

    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
}

class Point {
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final double INF = 1234567890;

    public static double getSlope(Line l){
        if (l.p2.x == l.p1.x){
            return INF;
        }
        else {
            return (double)(l.p2.y - l.p1.y) / (l.p2.x - l.p1.x);
        }
    }

    public static boolean isMeet(Point p1, Point p2, Point p3){
        double slope = getSlope(new Line(p1, p3));

        if (slope * (p2.x - p1.x) + p1.y > p2.y){
            return false;
        }
        else {
            return true;
        }
    }

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        Point[] buildings = new Point[n];
        int max = 0;

        for (int i = 0; i < n; i++){
            buildings[i] = new Point(i, in.nextInt());
        }

        for (int i = 0; i < n; i++){
            int cnt = 0;

            if (i - 1 >= 0){
                cnt++;
            }

            if (i + 1 < n){
                cnt++;
            }

            for (int j = i - 2; j >= 0; j--){
                boolean check = true;

                for (int k = i - 1; k > j; k--){
                    if (isMeet(buildings[i], buildings[k], buildings[j])){
                        check = false;
                        break;
                    }
                }

                cnt += check ? 1 : 0;
            }

            for (int j = i + 2; j < n; j++){
                boolean check = true;

                for (int k = i + 1; k < j; k++){
                    if (isMeet(buildings[i], buildings[k], buildings[j])){
                        check = false;
                        break;
                    }
                }

                cnt += check ? 1 : 0;
            }

            max = Math.max(max, cnt);
        }

        System.out.print(max);
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