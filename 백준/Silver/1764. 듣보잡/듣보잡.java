import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> a = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++){
            a.put(br.readLine(), 1);
        }

        int cnt = 0;

        for (int j = 0; j < m; j++){
            String input = br.readLine();

            if (a.containsKey(input)){
                cnt++;
                list.add(input);
            }
        }

        Collections.sort(list);

        for (String e : list){
            sb.append(e).append('\n');
        }

        System.out.println(cnt);
        System.out.println(sb);
    }
}