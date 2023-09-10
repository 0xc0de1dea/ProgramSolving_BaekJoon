import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] basket = new int[n + 1];

        for (int i = 1; i <= n; i++)
            basket[i] = i;

        while (m-- > 0){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            ArrayList<Integer> a = new ArrayList<>();

            for (int x = k; x <= j; x++)
                a.add(basket[x]);

            for (int y = i; y < k; y++)
                a.add(basket[y]);

            int cur = 0;

            for (int z = i; z <= j; z++){
                basket[z] = a.get(cur);
                cur++;
            }
        }

        for (int i = 1; i <= n; i++)
            sb.append(basket[i]).append(' ');

        System.out.println(sb);
    }
}