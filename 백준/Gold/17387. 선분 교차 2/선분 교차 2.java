import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

    public static boolean compareDir(Point p1, Point p2){
        boolean check = false;

        if (p1.x == p2.x){
            if (p2.y >= p1.y) check = true;
            else check = false;
        }
        else {
            if (p2.x >= p1.x) check = true;
            else check = false;
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
            if (!compareDir(l1.p1, l1.p2)){
                swapPoint(l1);
            }

            if (!compareDir(l2.p1, l2.p2)){
                swapPoint(l2);
            }

            if (compareDir(l2.p1, l1.p2) && compareDir(l1.p1, l2.p2)){
                result = 1;
            }
            else {
                result = 0;
            }
        }
        else {
            result = (cross12 != 1 && cross21 != 1) ? 1 : 0;
        }

        return result;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] input = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Line line1 = new Line(new Point(input[0], input[1]), new Point(input[2], input[3]));
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        Line line2 = new Line(new Point(input[0], input[1]), new Point(input[2], input[3]));

        System.out.println(lineSegmentIntersection(line1, line2));
    }
}