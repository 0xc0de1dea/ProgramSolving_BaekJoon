import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        final int inf = 10041004;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] elapsedTime = new int[100001];

        for (int i = 0; i <= 100000; i++)
            elapsedTime[i] = inf;

        elapsedTime[n] = 0;
        boolean[] isVisited = new boolean[100001];
        Deque<Integer> deq = new LinkedList<>();
        deq.addFirst(n);

        while (deq.size() != 0){
            int cur = deq.pollFirst();
            isVisited[cur] = true;
            int next = 0;
            int cost = 0;
            
            for (int i = 0; i < 3; i++){
                next = cur + (i == 0 ? 1 : (i == 1 ? -1 : cur));
                cost = i == 0 ? 1 : (i == 1 ? 1 : 0);

                if (next < 0 || next > 100000) continue;
                
                if (!isVisited[next]){
                    if (elapsedTime[cur] + cost < elapsedTime[next]){
                        elapsedTime[next] = elapsedTime[cur] + cost;
                        
                        if (cost == 0)
                            deq.addFirst(next);
                        else
                            deq.addLast(next);
                    }
                }
            }
        }

        System.out.println(elapsedTime[k]);
    }
}