import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        
        if (m != 0)
            st = new StringTokenizer(br.readLine());
        boolean[] brokenButton = new boolean[10];

        for (int i = 0; i < m; i ++){
            int tmp = Integer.parseInt(st.nextToken());
            brokenButton[tmp] = true;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 1000000; i++){
            int onlyPlusMinus = Math.abs(n - 100);
            int numButton = 0;
            int plusMinusButton = 0xFFFFF;
            int copy_i = i;

            if (copy_i == 0){
                if (!brokenButton[0])
                    numButton = 1;
                else
                    numButton = 0xFFFFF;
            }
            while (copy_i > 0){
                if (brokenButton[copy_i % 10]){
                    numButton = 0xFFFFF;
                    break;
                }
                copy_i /= 10;
                numButton += 1;
            }
            if (numButton != 0xFFFFF)
                plusMinusButton = Math.abs(n - i);
            
            min = Math.min(min, Math.min(onlyPlusMinus, numButton + plusMinusButton));
        }

        System.out.println(min);
    }
}