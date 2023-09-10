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
        int s = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= n; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int r = 1;
        int sumArr = sequence[1];
        int min = 0x7FFFFFFF;

        while (true){
            if (sumArr < s){
                r++;

                if (r > n){
                    if (min != 0x7FFFFFFF){
                        System.out.println(min);
                    }
                    else {
                        System.out.println(0);
                    }
                    break;
                }
                else {
                    sumArr += sequence[r];
                }
            }
            else {
                min = Math.min(min, r - l + 1);
                sumArr -= sequence[l];
                l++;
            }
        }
    }
}