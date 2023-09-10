import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] data;
    static int max;

    public static void backtracking(int day, int subMax){
        if (day > n){
            max = Math.max(max, subMax);
            return;
        }

        for (int i = day; i <= n; i++){
            if (i + data[i][0] - 1 > n)
                backtracking(i + data[i][0], subMax);
            else
                backtracking(i + data[i][0], subMax + data[i][1]);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        data = new int[n + 1][2];
        StringTokenizer st;

        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }
        
        backtracking(1, 0);
        System.out.println(max);
    }
}