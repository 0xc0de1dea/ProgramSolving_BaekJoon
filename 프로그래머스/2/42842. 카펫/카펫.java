class Solution {
    public int[] solution(int brown, int yellow) {
        int b = (brown - 4) / 2;
        int sqrt = (int)Math.sqrt(b * b - 4 * yellow);

        int x = (b + sqrt) / 2;
        int y = (b - sqrt) / 2;

        return new int[] {x + 2, y + 2};
    }
}