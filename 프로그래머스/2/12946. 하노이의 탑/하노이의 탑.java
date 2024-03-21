import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public ArrayList<int[]> hanoi(int start, int end, int mid, int n){
        if (n == 1){
            return new ArrayList<>(Arrays.asList(new int[]{start, end}));
        }

        ArrayList<int[]> ret = hanoi(start, mid, end, n - 1);
        ret.add(new int[] {start, end});
        ret.addAll(hanoi(mid, end, start, n - 1));

        return ret;
    }

    public int[][] solution(int n) {
        ArrayList<int[]> ret = hanoi(1, 3, 2, n);

        return ret.stream().toArray(int[][]::new);
    }
}