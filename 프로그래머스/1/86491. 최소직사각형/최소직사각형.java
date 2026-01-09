class Solution {
    public int solution(int[][] sizes) {
        int row = 0;
        int col = 0;

        for (int i = 0; i < sizes.length; i++){
            int w = Math.max(sizes[i][0], sizes[i][1]);
            int h = Math.min(sizes[i][0], sizes[i][1]);

            row = Math.max(row, w);
            col = Math.max(col, h);
        }

        return row * col;
    }
}