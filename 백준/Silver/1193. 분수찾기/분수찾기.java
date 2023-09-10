import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int x = 1;
        
        while (true){
            if (n <= x * (x + 1) / 2)
                break;

            x++;
        }

        int a, b = 0;
        int diff = x * (x + 1) / 2 - n;

        if (x % 2 == 0){
            a = x - diff;
            b = 1 + diff;
        }
        else{
            a = 1 + diff;
            b = x - diff;
        }

        System.out.println(a + "/" + b);
    }
}