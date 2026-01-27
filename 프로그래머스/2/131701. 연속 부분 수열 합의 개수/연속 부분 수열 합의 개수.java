import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> hs = new HashSet<>();

        int[] prefixSum = new int[elements.length << 1 + 1];

        for (int i = 0; i < prefixSum.length - 1; i++){
            prefixSum[i + 1] = prefixSum[i] + elements[i % elements.length];
        }

        for (int i = 1; i <= elements.length; i++){
            for (int j = i; j < prefixSum.length; j++){
                int sum = prefixSum[j] - prefixSum[j - i];
                hs.add(sum);
            }
        }

        return hs.size();
    }
}