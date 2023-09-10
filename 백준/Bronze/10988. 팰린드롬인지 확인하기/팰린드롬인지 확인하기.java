import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split("");
        boolean check = true;

        int i = 0;
        int j = input.length - 1;

        while (i <= j){
            if (!input[i++].equals(input[j--])){
                check = false;
                break;
            }
        }

        System.out.println(check == true ? 1 : 0);
    }
}