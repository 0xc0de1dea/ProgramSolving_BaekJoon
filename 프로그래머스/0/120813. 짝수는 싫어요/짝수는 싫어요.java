import java.util.stream.IntStream;

class Solution {
    public int[] solution(int n) {
        return IntStream.range(1, n + 1).filter(x -> x % 2 == 1).toArray();
    }
}