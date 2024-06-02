class Solution {
    public int solution(int chicken) {
        int sum = 0;
        int coupon = 0;

        while (chicken > 0){
            coupon += chicken;
            sum += coupon / 10;
            chicken = coupon / 10;
            coupon %= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(1999));
    }
}