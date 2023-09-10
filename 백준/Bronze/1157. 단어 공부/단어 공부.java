import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[26];
        String s = br.readLine().toUpperCase();

        for (int i = 0; i < s.length(); i++){
            arr[s.charAt(i) - 'A']++;
        }

        int max = 0;
        
        for (int i = 0; i < 26; i++){
            if (max < arr[i]){
                max = arr[i];
            }
        }

        int combo = 0;
        char answer = ' ';

        for (int i = 0; i < 26; i++){
            if (arr[i] == max){
                combo++;
                answer = (char)(i + 'A');
            }
        }

        if (combo > 1) System.out.println("?");
        else System.out.println(answer);
    }
}