import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int cookingTime = Integer.parseInt(br.readLine());
        minute += cookingTime;
        hour += minute / 60;
        minute %= 60;
        hour %= 24;
        System.out.println(hour + " " + minute);
    }
}//우을증은 정말 무서운 병