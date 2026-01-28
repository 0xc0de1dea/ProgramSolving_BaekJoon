class Solution {
    public int[] solution(int n, long left, long right) {
        long leftRow = left / n;
        long leftCol = left % n;
        long rightRow = right / n;
        long rightCol = right % n;

        int[] ans = new int[(int)(right - left + 1)];
        int idx = 0;

        while (!(leftRow == rightRow && leftCol == rightCol)){
            ans[idx++] = (int)Math.max(leftRow, leftCol) + 1;
            leftCol++;

            if (leftCol >= n){
                leftCol = 0;
                leftRow++;
            }
        }

        ans[idx] = (int)Math.max(rightRow, rightCol) + 1;

        return ans;
    }
}