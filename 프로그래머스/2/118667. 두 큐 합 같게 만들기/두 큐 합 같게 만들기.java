class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long[] merged = new long[queue1.length + queue2.length];

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < queue1.length; i++){
            merged[i] = queue1[i];
            sum1 += queue1[i];
        }

        for (int i = 0; i < queue2.length; i++){
            merged[i + queue1.length] = queue2[i];
            sum2 += queue2[i];
        }

        if (((sum1 + sum2) & 1) == 1) return -1;

        long trg = sum1 + sum2 >> 1;

        int l1 = 0;
        int r1 = queue1.length - 1;

        int l2 = queue1.length;
        int r2 = queue1.length + queue2.length -1;

        int cnt = 0;

        while (cnt < (queue1.length + queue2.length) << 1){
            if (sum1 < trg){
                r1 = (r1 + 1) % merged.length;
                l2 = (l2 + 1) % merged.length;

                sum1 += merged[r1];
                sum2 -= merged[r1];

                cnt++;
            }

            if (sum2 < trg){
                r2 = (r2 + 1) % merged.length;
                l1 = (l1 + 1) % merged.length;

                sum2 += merged[r2];
                sum1 -= merged[r2];

                cnt++;
            }

            if (sum1 == trg && sum2 == trg){
                break;
            }
        }

        return sum1 == sum2 ? cnt : -1;
    }
}