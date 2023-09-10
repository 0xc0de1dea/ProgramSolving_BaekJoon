import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[26];
        int prev = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            prev = s.charAt(0) - 'a';
            if (s.length() >2) {
                for (int j = 0; j < s.length(); j++) {
                    int now = s.charAt(j);
                    if (prev != now) {
                        if (arr[s.charAt(j) - 'a'] == 0) {
                            arr[s.charAt(j) - 'a'] = 1;
                            prev = now;
                        } else {
                            count--;
                            break;
                        }
                    }
                }
            }
            count++;
            arr = new int[26];
        }
        System.out.println(count);
    }
}
//콩 도와주기