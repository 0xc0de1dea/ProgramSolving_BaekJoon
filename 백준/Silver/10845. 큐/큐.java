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
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deq = new LinkedList<>();
        
        while (n-- > 0){
            st = new StringTokenizer(br.readLine());
            String orderType = st.nextToken();
            int num = 0;
            int size = deq.size();

            switch (orderType) {
                case "push":
                    num = Integer.parseInt(st.nextToken());
                    deq.addLast(num);
                    break;
            
                case "pop":
                    if (size == 0)
                        sb.append(-1).append('\n');
                    else
                        sb.append(deq.removeFirst()).append('\n');
                    break;

                case "size":
                    sb.append(size).append('\n');
                    break;

                case "empty":
                    if (size == 0)
                        sb.append(1).append('\n');
                    else
                        sb.append(0).append('\n');
                    break;
                
                case "front":
                    if (size == 0)
                        sb.append(-1).append('\n');
                    else
                        sb.append(deq.peekFirst()).append('\n');
                    break;

                case "back":
                    if (size == 0)
                        sb.append(-1).append('\n');
                    else
                        sb.append(deq.peekLast()).append('\n');
                    break;

                default:
                    break;
            }
        }

        System.out.println(sb);
    }
}