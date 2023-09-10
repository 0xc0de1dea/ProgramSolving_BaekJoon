import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

            if ((j - i) % 2 == 0){
                int half = (j - i) / 2;
                
                for (int a = 0; a < half; a++){
                    int tmp = basket[i + a];
                    basket[i + a] = basket[j - a];
                    basket[j - a] = tmp;
                }
            }
            else{
                int half = (j - i + 1) / 2;

                for (int a = 0; a < half; a++){
                    int tmp = basket[i + a];
                    basket[i + a] = basket[j - a];
                    basket[j - a] = tmp;
                }
            }
        }

        for (int i = 1; i <= n; i++)
            sb.append(basket[i]).append(' ');

        System.out.println(sb);
    }
}