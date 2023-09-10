import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static char[] sequence;
    static ArrayList curSequence = new ArrayList<>();
    static int c, l;

    public static void backtracking(int start, int depth, int cnt){
        if (cnt >= l - 1) return;

        if (depth == l && cnt >= 1){
            for (int i = 0; i < curSequence.size(); i++){
                sb.append(curSequence.get(i));
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < c; i++){
            curSequence.add(sequence[i]);

            if (sequence[i] == 'a' || sequence[i] == 'e' || sequence[i] == 'i' ||sequence[i] == 'o' || sequence[i] == 'u')
                backtracking(i + 1, depth + 1, cnt + 1);
            else backtracking(i + 1, depth + 1, cnt);

            curSequence.remove(curSequence.size() - 1);
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sequence = new char[c];
        
        for (int i = 0; i < c; i++){
            sequence[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(sequence);
        backtracking(0, 0, 0);
        System.out.println(sb);
    }
}