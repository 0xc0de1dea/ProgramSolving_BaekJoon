import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static String[] ineq_signs;
    static boolean[] isVisited = new boolean[10];
    static long min = Long.MAX_VALUE;
    static long max = 0;

    public static void backtracking(int depth, long num, int prevNum){
        long tmp = num * (long)Math.pow(10, k - depth);

        if (tmp > min && tmp < max) return;

        if (depth == k){
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }

        int start = 0;
        int end = 9;

        if (ineq_signs[depth].equals("<")) start = prevNum + 1;
        else end = prevNum - 1;

        for (int i = start; i <= end; i++){
            if (isVisited[i]) continue;

            isVisited[i] = true;
            backtracking(depth + 1, num * 10 + i, i);
            isVisited[i] = false;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        ineq_signs = new String[k];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++){
            ineq_signs[i] = st.nextToken();
        }

        for (int i = 0; i <= 9; i++){
            isVisited[i] = true;
            backtracking(0, i, i);
            isVisited[i] = false;
        }

        String ansMin = Long.toString(min);
        String ansMax = Long.toString(max);

        if (ansMax.length() == k){
            System.out.println("0" + ansMax);
        }
        else{
            System.out.println(ansMax);
        }

        if (ansMin.length() == k){
            System.out.println("0" + ansMin);
        }
        else{
            System.out.println(ansMin);
        }
    }
}