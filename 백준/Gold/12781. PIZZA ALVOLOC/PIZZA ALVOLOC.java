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
    public static int ccw(Point p1, Point p2, Point p3){
        long crossProduct = (long)(p2.x - p1.x) * (p3.y - p1.y) - (long)(p3.x - p1.x) * (p2.y - p1.y);

        if (crossProduct > 0){
            return 1;
        }
        else if (crossProduct < 0){
            return -1;
        }
        else {
            return 0;
        }
    }

    public static int compareDir(Point p1, Point p2){
        int check = 0;

        if (p1.x == p2.x){
            if (p2.y > p1.y) check = 1;
            else if (p2.y == p1.y) check = 0;
            else check = -1;
        }
        else {
            if (p2.x > p1.x) check = 1;
            else if (p2.x == p1.x) check = 0;
            else check = -1;
        }

        return check;
    }

    public static void swapPoint(Line l){
        Point tmp = l.p1;
        l.p1 = l.p2;
        l.p2 = tmp;
    }

    public static int lineSegmentIntersection(Line l1, Line l2){
        int result = 0;

        int cross12 = ccw(l1.p1, l1.p2, l2.p1) * ccw(l1.p1, l1.p2, l2.p2);
        int cross21 = ccw(l2.p1, l2.p2, l1.p1) * ccw(l2.p1, l2.p2, l1.p2);

        if (cross12 == 0 && cross21 == 0){
            if (compareDir(l1.p1, l1.p2) < 0){
                swapPoint(l1);
            }

            if (compareDir(l2.p1, l2.p2) < 0){
                swapPoint(l2);
            }

            int check = compareDir(l2.p1, l1.p2) + compareDir(l1.p1, l2.p2);

            if (ccw(l1.p1, l1.p2, l2.p1) + ccw(l1.p1, l1.p2, l2.p2) == 0){
                if (check == 2){
                    result = 3;
                }
                else if (check == 1){
                    result = 1;
                }
                else {
                    result = 0;
                }
            }
            else {
                result = 1;
            }
        }
        else {
            int check = cross12 + cross21;

            if (check == -2){
                result = 2;
            }
            else if (check == -1){
                if (cross12 == 0){
                    result = 1;
                }
                else {
                    result = 2;
                }
            }
            else {
                result = 0;
            }
        }

        return result;
    }

    static final double INF = 1234567890;

    public static double getSlope(Line l){
        if (l.p2.x == l.p1.x){
            return INF;
        }
        else {
            return (double)(l.p2.y - l.p1.y) / (l.p2.x - l.p1.x);
        }
    }

    public static double[] getMeetPoint(Line l1, Line l2, int type){
        double x = 0, y = 0;
        double sl1 = getSlope(l1);
        double sl2 = getSlope(l2);

        if (type == 1 || type == 2){
            if (sl1 == INF){
                x = l1.p1.x;
                y = sl2 * (x - l2.p1.x) + l2.p1.y;
            }
            else if (sl2 == INF){
                x = l2.p1.x;
                y = sl1 * (x - l1.p1.x) + l1.p1.y;
            }
            else {
                x = (sl1 * l1.p1.x - sl2 * l2.p1.x + l2.p1.y - l1.p1.y) / (sl1 - sl2);
                y = sl1 * (x - l1.p1.x) + l1.p1.y;
            }
        }
        else if (type == 3){
            if (l1.p1.x == l2.p1.x && l1.p1.y == l2.p1.y || l1.p1.x == l2.p2.x && l1.p1.y == l2.p2.y){
                x = l1.p1.x;
                y = l1.p1.y;
            }
            else if (l1.p2.x == l2.p1.x && l1.p2.y == l2.p1.y || l1.p2.x == l2.p2.x && l1.p2.y == l2.p2.y){
                x = l1.p2.x;
                y = l1.p2.y;
            }
        }

        return new double[] { x, y };
    }

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        Line[] lines = new Line[2];
        
        for (int i = 0; i < 2; i++){
            lines[i] = new Line(new Point(in.nextInt(), in.nextInt()), new Point(in.nextInt(), in.nextInt()));
        }

        System.out.print(lineSegmentIntersection(lines[0], lines[1]) == 2 ? 1 : 0);
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