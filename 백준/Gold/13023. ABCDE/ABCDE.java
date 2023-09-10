import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static Queue<Integer>[] relationship;
    static boolean[] isVisited;
    static boolean isFound;

    public static void dfs(int depth, Queue<Integer> q){
        if (depth == 5){
            isFound = true;
            return;
        }

        if (isFound) return;

        while (!q.isEmpty()){
            int nextNode = q.poll();
            
            if (!isVisited[nextNode]){
                isVisited[nextNode] = true;
                Queue<Integer> cloneQ = new LinkedList<>();
                Iterator iter = relationship[nextNode].iterator();

                while (iter.hasNext())
                    cloneQ.add((int)iter.next());

                dfs(depth + 1, cloneQ);
                isVisited[nextNode] = false;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        relationship = new Queue[n];
        
        for (int i = 0; i < n; i++)
            relationship[i] = new LinkedList<>();

        isVisited = new boolean[n];

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relationship[a].add(b);
            relationship[b].add(a);
        }

        for (int i = 0; i < n; i++){
            if (relationship[i].size() != 0){
                isVisited[i] = true;
                Queue<Integer> cloneQ = new LinkedList<>();
                Iterator iter = relationship[i].iterator();

                while (iter.hasNext())
                    cloneQ.add((int)iter.next());

                dfs(1, cloneQ);
                isVisited[i] = false;
            }
        }

        if (isFound)
            System.out.println(1);
        else
            System.out.println(0);
    }
}