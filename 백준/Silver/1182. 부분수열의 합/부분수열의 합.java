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
        int[] seq = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int subSum = 0;
        int cnt = 0;

        for (int i = 1; i < 1 << n; i++){
            subSum = 0;

            for (int j = 0; j < n; j++){
                if ((i & 1 << j) > 0){
                    subSum += seq[j];
                }
            }

            if (subSum == s){
                cnt++;
            }
        }

        System.out.println(cnt);
    }  
}