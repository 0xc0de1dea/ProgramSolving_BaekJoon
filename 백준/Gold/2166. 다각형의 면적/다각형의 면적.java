import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
    public double x, y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Point[] pos = new Point[n];

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            pos[i] = new Point(x, y);
        }

        double sum = 0;

        for (int i = 1; i < n; i++){
            sum += (pos[i].x - pos[i - 1].x) * ((pos[i].y + pos[i - 1].y) / 2);
        }
        sum += (pos[0].x - pos[n - 1].x) * ((pos[0].y + pos[n - 1].y) / 2);
        
        if (sum < 0) sum = -sum;
        
        System.out.println(String.format("%.1f", sum));
    }
}