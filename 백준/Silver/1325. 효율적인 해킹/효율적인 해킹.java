import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static boolean[] isVisited;
    static int[] answer;
    static List<Integer>[] lists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        isVisited = new boolean[n + 1];
        lists = new List[n + 1];
        answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            lists[from].add(to);
        }

        for (int i = 1; i <= n; i++) {
            bfs(i);
            Arrays.fill(isVisited, false);
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        int max = 0;
        for (int i = 1; i < answer.length; i++){
            if (max < answer[i]){
                max = answer[i];
                list = new ArrayList<>();
                list.add(i);
            } else if (max == answer[i]){
                list.add(i);
            }
        }
        
        for (int item : list){
            sb.append(item).append(' ');
        }

        System.out.println(sb);
    }

    public static void bfs(int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(start);
        isVisited[start] = true;

        while (!deque.isEmpty()) {
            int poll = deque.pollLast();

            for (int item : lists[poll]) {
                if (!isVisited[item]) {
                    isVisited[item] = true;
                    answer[item]++;
                    deque.addFirst(item);
                }
            }
        }

    }
}