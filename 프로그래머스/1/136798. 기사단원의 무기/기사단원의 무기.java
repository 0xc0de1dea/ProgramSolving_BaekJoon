class Solution {
    public int getDivisor(int n){
        int cnt = 0;

        for (int i = 1; i * i <= n; i++){
            if (i * i == n) cnt++;
            else if (n % i == 0) cnt += 2;
        }

        return cnt;
    }

    public int solution(int number, int limit, int power) {
        int ans = 0;

        for (int i = 1; i <= number; i++){
            int cnt = getDivisor(i);

            if (cnt > limit) ans += power;
            else ans += cnt;
        }

        return ans;
    }
}