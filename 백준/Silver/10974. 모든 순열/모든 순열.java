import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();

    public static void bruteforce(int depth, int[] tmp){
        if (depth == n){
            for (int e : tmp)
                sb.append(e + " ");

            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++){
            if (isVisited[i]) continue;

            isVisited[i] = true;
            tmp[depth] = i;
            bruteforce(depth + 1, tmp);
            isVisited[i] = false;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        isVisited = new boolean[n + 1];
        int[] tmp = new int[n];
        bruteforce(0, tmp);
        System.out.println(sb);
    }
}