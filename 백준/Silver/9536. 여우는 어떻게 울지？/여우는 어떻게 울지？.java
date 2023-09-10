import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0){
            StringBuilder sb = new StringBuilder();
            String[] sounds = br.readLine().split(" ");
            String[] sound = null;

            while (true){
                sound = br.readLine().split(" ");
                String type = sound[sound.length - 1];

                if (type.equals("say?")) break;

                for (int i = 0; i < sounds.length; i++){
                    if (type.equals(sounds[i])){
                        sounds[i] = null;
                    }
                }
            }
            
            for (int i = 0; i < sounds.length; i++){
                if (sounds[i] != null){
                    sb.append(sounds[i]).append(' ');
                }
            }

            System.out.println(sb);
        }
    }
}