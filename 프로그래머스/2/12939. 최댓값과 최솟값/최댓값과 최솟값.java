class Solution {
    public String solution(String s) {
        final int INF = 0x7f7f7f7f;
        String[] split = s.split(" ");
        int min = INF;
        int max = -INF;

        for (String n : split){
            int num = Integer.parseInt(n);

            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return min + " " + max;
    }
}