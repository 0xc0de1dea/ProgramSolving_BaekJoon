import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<int[]> sequence = new ArrayList<>();
    static int[] saved;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();

    public static void backtracking(int depth){
        if (depth == m){
            int size = saved.length;

            for (int i = 0; i < size; i++){
                sb.append(saved[i]).append(' ');
            }

            sb.append('\n');
            return;
        }

        int size = sequence.size();
        
        for (int i = 0; i < size; i++){
            if (sequence.get(i)[1] > 0){
                isVisited[i] = true;
                sequence.get(i)[1]--;
                saved[depth] = sequence.get(i)[0];
                backtracking(depth + 1);
                sequence.get(i)[1]++;
                isVisited[i] = false;
            }
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
                if (sequence.get(j)[0] == tmp){
                    sequence.get(j)[1]++;
                    continue jump;
                }
            }

            sequence.add(new int[] { tmp, 1 });
        }
        
        saved = new int[m];
        isVisited = new boolean[sequence.size()];
        Collections.sort(sequence, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });
        backtracking(0);
        System.out.println(sb);
    }
}