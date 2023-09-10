import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] seq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] isVisited = new boolean[2000001];
        int subSum = 0;

        for (int i = 0; i < n; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < 1 << n; i++){
            subSum = 0;

            for (int j = 0; j < n; j++){
                if ((i & 1 << j) > 0){
                    subSum += seq[j];
                }
            }

            isVisited[subSum] = true;
        }

        for (int i = 1; i <= 2000000; i++){
            if (!isVisited[i]){
                System.out.println(i);
                break;
            }
        }
    }  
}