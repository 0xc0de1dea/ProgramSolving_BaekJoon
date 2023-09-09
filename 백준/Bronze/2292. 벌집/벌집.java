import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.StringBuilder;
public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        double x= Integer.parseInt(br.readLine());
        if (x!=1.0d){
            double ans = ((Math.sqrt(3)*Math.sqrt((4*x)-1))-3)/6;
            sb.append((int)(Math.ceil(ans))+1);
        }
        else sb.append(1);
        System.out.println(sb);
    }
}