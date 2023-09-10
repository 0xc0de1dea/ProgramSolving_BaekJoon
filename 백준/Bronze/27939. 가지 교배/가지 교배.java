import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] eggPlant = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++){
            String tmp = st.nextToken();

            if (tmp.equals("W")){
                eggPlant[i] = 1;
            }
            else {
                eggPlant[i] = 0;
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] assistant = new int[m];

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int mating = 1;

            for (int j = 0; j < k; j++){
                mating &= eggPlant[Integer.parseInt(st.nextToken())];
            }

            assistant[i] = mating;
        }

        int result = 0;

        for (int i = 0; i < m; i++){
            result += assistant[i];
        }

        System.out.println(result == 0 ? "P" : "W");
    }
}