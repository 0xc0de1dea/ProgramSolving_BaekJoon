class Solution {
    static int[] f = new int[100_001];
    static final int MOD = 1234567;

    public int fibonacci(int n){
        if (n == 0) return 0;
        else if (n == 1 || n == 2) return 1;
        
        if (f[n] != 0) return f[n];
        
        return f[n] = (fibonacci(n - 1) + fibonacci(n - 2)) % MOD;
    }

    public int solution(int n) {
        return fibonacci(n);   
    }
}