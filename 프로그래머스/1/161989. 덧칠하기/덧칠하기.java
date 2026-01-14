class Solution {
    public int solution(int n, int m, int[] section) {
        int last = section[0] + m - 1;
        int cnt = 1;

        for (int trg : section){
            if (trg <= last) continue;

            last = trg + m - 1;
            cnt++;
        }

        return cnt;
    }
}