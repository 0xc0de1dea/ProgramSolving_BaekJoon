class Solution {
    public long solution(int n) {
        final int MOD = 1_234_567;

        int[] fib = new int[2_001];
        fib[0] = 1;
        fib[1] = 1;

        for (int i = 2; i <= n; i++){
            fib[i] = (fib[i - 1] + fib[i - 2]) % MOD;
        }

        return fib[n];
    }
}