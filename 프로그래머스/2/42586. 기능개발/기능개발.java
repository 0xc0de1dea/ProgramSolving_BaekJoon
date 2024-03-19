import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        Deque<Integer> deque = new LinkedList<>();
        int prevDay = -1;
        
        for (int i = 0; i < progresses.length; i++){
            int day = (int)Math.ceil((double)(100 - progresses[i]) / speeds[i]);
            
            if (prevDay >= day){
                deque.push(deque.pop() + 1);
            } else {
                deque.push(1);
                prevDay = day;
            }
        }

        answer = new int[deque.size()];
        int idx = 0;

        while (!deque.isEmpty()){
            answer[idx++] = deque.pollLast();
        }
        
        return answer;
    }
}