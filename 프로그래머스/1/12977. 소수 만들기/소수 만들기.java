class Solution {
    boolean[] isPrime = new boolean[3_000];

    public void setPrime(){
        for (int i = 2; i * i < 3_000; i++){
            if (isPrime[i]){
                for (int j = i * i; j < 3_000; j += i){
                    isPrime[j] = false;
                }
            }
        }
    }

    public int solution(int[] nums) {
        for (int i = 2; i < 3_000; i++){
            isPrime[i] = true;
        }

        setPrime();

        int cnt = 0;

        for (int i = 0; i < nums.length - 2; i++){
            for (int j = i + 1; j < nums.length - 1; j++){
                for (int k = j + 1; k < nums.length; k++){
                    int sum = nums[i] + nums[j] + nums[k];

                    if (isPrime[sum]) cnt++;
                }
            }
        }

        return cnt;
    }
}