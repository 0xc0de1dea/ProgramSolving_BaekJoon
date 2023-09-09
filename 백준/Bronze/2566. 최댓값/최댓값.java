import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int max = Integer.MIN_VALUE;
        int loc = 0;
        
        for (int i = 0; i < 9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < 9; j++){
                int tmp = Integer.parseInt(st.nextToken());

                if (max < tmp){
                    max = tmp;
                    loc = i * 10 + j;
                }
            }
        }

        sb.append(max).append('\n').append(loc / 10 + 1).append(' ').append(loc % 10 + 1);
        System.out.println(sb);
    }
}