import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {};
        Arrays.sort(arr);
        answer = Arrays.stream(arr).filter(x -> x % divisor == 0).toArray();
        answer = answer.length == 0 ? new int[]{-1} : answer;

        return answer;
    }
}