import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        int max = 0;

        for (int i = 0; i < citations.length; i++){
            if (citations[i] >= citations.length - i){
                max = citations.length - i;
                break;
            }
        }

        return max;
    }
}