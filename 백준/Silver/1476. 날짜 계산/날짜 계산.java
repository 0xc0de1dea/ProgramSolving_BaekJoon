import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int a = 1;
        int b = 1;
        int c = 1;
        int date = 1;

        while (true){
            if (a == e && b == s && c == m){
                break;
            }

            a += 1;
            b += 1;
            c += 1;
            date += 1;
            //a = b = c = date += 1;
            
            if (a == 16)
                a = 1;
            if (b == 29)
                b = 1;
            if (c == 20)
                c = 1;
        }

        System.out.println(date);
    }
}