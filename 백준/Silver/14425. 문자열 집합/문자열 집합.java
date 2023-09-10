import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(br.readLine(), 0);
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (map.containsKey(br.readLine())) count++;
        }
        System.out.print(count);
    }
}///////12ㅕ331298ㄱ89려ㅓㅈㄷ런러ㅣ#$!#!@#!@$