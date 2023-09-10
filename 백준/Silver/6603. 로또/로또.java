import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] data;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();

    public static void permutation(int depth, int[] tmp, int prev){
        if (depth == 6){
            for (int e : tmp)
                sb.append(e + " ");

            sb.append("\n");
            return;
        }

        for (int i = prev + 1; i < n; i++){
            if (isVisited[i]) continue;

            isVisited[i] = true;
            tmp[depth] = data[i];
            permutation(depth + 1, tmp, i);
            isVisited[i] = false;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] section;

        while (true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            data = new int[n];
            isVisited = new boolean[n];
            section = new int[6];
            int tokenCnt = st.countTokens();

            for (int i = 0; i < tokenCnt; i++){
                data[i] = Integer.parseInt(st.nextToken());
            }

            permutation(0, section, -1);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}