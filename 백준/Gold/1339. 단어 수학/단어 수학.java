import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] alphabets = new int[26];
        
        while (n-- > 0){
            String input = br.readLine();

            for (int i = 0; i < input.length(); i++){
                int idx = input.charAt(i) - 65;
                alphabets[idx] += (int)Math.pow(10, input.length() - 1 - i);
            }
        }

        Integer[] alphabetsI = Arrays.stream(alphabets).boxed().toArray(Integer[]::new);
        Arrays.sort(alphabetsI, Comparator.reverseOrder());
        int sum = 0;
        int id = 9;

        for (int i = 0; i < 10; i++){
            sum += id * alphabetsI[i];
            id--;
        }

        System.out.println(sum);
    }  
}