import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Point {
    public int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n;
    static Point[] point;
    static boolean[] isVisited;
    static double min;

    public static void nCm(int depth, int start){
        if (depth == n / 2){
            Point vector = new Point(0, 0);

            for (int i = 0; i < n; i++){
                if (isVisited[i]){
                    vector.x += point[i].x;
                    vector.y += point[i].y;
                }
                else {
                    vector.x -= point[i].x;
                    vector.y -= point[i].y;
                }
            }

            double len = Math.sqrt(Math.pow(vector.x, 2) + Math.pow(vector.y, 2));
            min = Math.min(min, len);
            return;
        }

        for (int i = start; i < n; i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                nCm(depth + 1, i + 1);
                isVisited[i] = false;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            n = Integer.parseInt(br.readLine());
            point = new Point[n];
            isVisited = new boolean[n];
            min = Double.MAX_VALUE;

            for (int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                point[i] = new Point(x, y);
            }

            nCm(0, 0);
            sb.append(min).append('\n');
        }

        System.out.println(sb);
    }
}