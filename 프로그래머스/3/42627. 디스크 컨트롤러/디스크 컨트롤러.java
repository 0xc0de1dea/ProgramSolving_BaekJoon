import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> startQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<int[]> timeQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });

        for (int[] item1D : jobs){
            startQueue.add(item1D);
        }

        int elapsedTime = 0;
        int endTime = startQueue.peek()[0];

        while (!startQueue.isEmpty()){
            while (true){
                if (!startQueue.isEmpty() && startQueue.peek()[0] <= endTime){
                    timeQueue.add(startQueue.poll());
                } else {
                    break;
                }
            }

            if (!timeQueue.isEmpty()){
                int[] cur = timeQueue.poll();
                elapsedTime += endTime - cur[0] + cur[1];
                endTime += cur[1];
            }
            else {
                int[] cur = startQueue.poll();
                elapsedTime += cur[1];
                endTime = cur[0] + cur[1];
            }

            while (!timeQueue.isEmpty()){
                startQueue.add(timeQueue.poll());
            }
        }

        return elapsedTime / jobs.length;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        System.out.println(sol.solution(new int[][] {{1,4},{7,9},{1000,3}}));
    }
}