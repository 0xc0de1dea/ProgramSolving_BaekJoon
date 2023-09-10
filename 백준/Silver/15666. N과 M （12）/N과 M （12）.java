import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Integer> sequence = new ArrayList<>();
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

        int size = sequence.size();
        
        for (int i = s; i < size; i++){
            saved[depth] = sequence.get(i);
            backtracking(depth + 1, i);
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        jump : for (int i = 0; i < n; i++){
            int tmp = Integer.parseInt(st.nextToken());
            int size = sequence.size();

            for (int j = 0; j < size; j++){
                if (sequence.get(j) == tmp){
                    continue jump;
                }
            }

            sequence.add(tmp);
        }
        
        saved = new int[m];
        Collections.sort(sequence);
        backtracking(0, 0);
        System.out.println(sb);
    }
}