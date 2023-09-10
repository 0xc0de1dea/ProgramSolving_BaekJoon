import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int ansFront = 0;
        int ansL = 0;

        for (int a = l; a <= 100; a++){
            int ax = n - ((a - 1) * a / 2);
            
            if (ax % a == 0 && ax >= 0){
                ansFront = ax / a;
                ansL = a;
                break;
            }
        }

        if (ansL != 0){
            for (int i = 0; i < ansL; i++){
                sb.append(ansFront + i).append(' ');
            }
        }
        else {
            sb.append(-1);
        }

        System.out.println(sb);
    }
}