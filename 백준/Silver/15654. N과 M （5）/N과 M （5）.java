import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] sequence;
    static boolean[] isVisit;
    static ArrayList curSequence = new ArrayList<>();

    public static void nCr(int n, int r){
        if (r == 0){
            for (int i = 0; i < curSequence.size(); i++){
                sb.append(curSequence.get(i) + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < n; i++){
            if (isVisit[i]) continue;

            isVisit[i] = true;
            curSequence.add(sequence[i]);
            nCr(n, r - 1);
            curSequence.remove(curSequence.size() - 1);
            isVisit[i] = false;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        sequence = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        isVisit = new boolean[n];
        Arrays.sort(sequence);
        nCr(n, m);
        System.out.println(sb);
    }
}