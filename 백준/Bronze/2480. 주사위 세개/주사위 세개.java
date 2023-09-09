import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int prize = 0;

        if (a == b && b == c){
            prize = 10000 + a * 1000;
        }
        else if (a == b || b == c || a == c){
            prize = 1000;

            if (a == b){
                prize += a * 100;
            }
            else if (a == c){
                prize += c * 100;
            }
            else {
                prize += b * 100;
            }
        }
        else {
            prize = (a > b ? (a > c ? a : c) : (b > c ? b : c)) * 100;
        }

        System.out.println(prize);
    }
}