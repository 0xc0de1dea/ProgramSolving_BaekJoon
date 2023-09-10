import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] sequence;
    static int[] saved;
    static StringBuilder sb = new StringBuilder();

    public static void backtracking(int depth, int s){
        if (depth == m){
            int size = saved.length;

            for (int i = 0; i < size; i++){
                sb.append(saved[i]).append(' ');
            }

            sb.append('\n');
            return;
        }

        for (int i = s; i < n; i++){
            saved[depth] = sequence[i];
            backtracking(depth + 1, i);
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sequence = new int[n];
        saved = new int[m];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sequence);
        backtracking(0, 0);
        System.out.println(sb);
    }
}