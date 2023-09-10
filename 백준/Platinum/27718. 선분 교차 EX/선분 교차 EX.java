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

        if (crossProduct > 0) return 1;
        else if (crossProduct < 0) return -1;
        else return 0;
    }

    public static boolean isMeet(Line l1, Line l2){
        boolean check = false;

        if (l1.p1.x == l2.p1.x && l1.p1.y == l2.p1.y){
            check = true;
        }
        else if (l1.p1.x == l2.p2.x && l1.p1.y == l2.p2.y){
            check = true;
        }
        else if (l1.p2.x == l2.p1.x && l1.p2.y == l2.p1.y){
            check = true;
        }
        else if (l1.p2.x == l2.p2.x && l1.p2.y == l2.p2.y){
            check = true;
        }

        return check;
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
            if (ccw(l1.p1, l1.p2, l2.p1) + ccw(l1.p1, l1.p2, l2.p2) == 0){
                if (compareDir(l1.p1, l1.p2) < 0){
                    swapPoint(l1);
                }

                if (compareDir(l2.p1, l2.p2) < 0){
                    swapPoint(l2);
                }

                int check = compareDir(l2.p1, l1.p2) + compareDir(l1.p1, l2.p2);

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
                result = 1;
            }
            else {
                result = 0;
            }
        }

        return result;
    }

    public static void main(String[] argu) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        int n = in.nextInt();
        Line[] lines = new Line[n];

        for (int i = 0; i < n; i++){
            lines[i] = new Line(new Point(in.nextInt(), in.nextInt()), new Point(in.nextInt(), in.nextInt()));
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                sb.append(lineSegmentIntersection(lines[i], lines[j]));
            }
            sb.append('\n');
        }

        System.out.print(sb);
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