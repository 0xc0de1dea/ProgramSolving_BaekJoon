class Solution {
    public int solution(int[] priorities, int location) {
        int[] idxArr = new int[priorities.length];
        int rank = 1;
        int idx = 0;
        int lastIdx = 0;

        for (int i = 9; i >= 1; i--){
            int loopCnt = priorities.length;

            while (loopCnt-- > 0){
                if (priorities[idx] == i){
                    idxArr[idx] = rank++;
                    lastIdx = idx;
                }
                idx = (idx + 1) % priorities.length;
            }

            idx = lastIdx;
        }

        return idxArr[location];
    }
}