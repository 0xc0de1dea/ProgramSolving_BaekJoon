import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] word = null;
        
        jump : while (!(word = br.readLine().split(""))[0].equals("0")){
            for (int l = 0, r = word.length - 1; l <= r; l++, r--){
                if (!word[l].equals(word[r])){
                    sb.append("no").append('\n');
                    continue jump;
                }
            }

            sb.append("yes").append('\n');
        }

        System.out.println(sb);
    }
}