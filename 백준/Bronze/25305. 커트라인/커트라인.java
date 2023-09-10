import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] scores = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++)
            scores[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(scores);

        System.out.println(scores[n - k]);
    }
}