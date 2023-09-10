import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        int num = Integer.parseInt(n);
        int len = n.length();
        int sub = (int)Math.pow(10, len - 1);
        int digit = 0;
        digit += (num - (sub - 1)) * len;

        while (sub > 1){
            num = sub - 1;
            sub /= 10;
            len--;
            digit += (num - (sub - 1)) * len;
        }

        System.out.println(digit);
    }
}