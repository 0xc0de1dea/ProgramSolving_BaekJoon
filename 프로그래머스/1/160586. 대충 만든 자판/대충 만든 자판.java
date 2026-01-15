class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] alpha = new int[26];

        for (int i = 0; i < 26; i++){
            alpha[i] = 101;
        }

        for (String key : keymap){
            for (int i = 0; i < key.length(); i++){
                int n = key.charAt(i) - 'A';
                alpha[n] = Math.min(i + 1, alpha[n]);
            }
        }

        int[] ans = new int[targets.length];
        int idx = 0;

        for (String trg : targets){
            int sum = 0;

            for (int i = 0; i < trg.length(); i++){
                int n = trg.charAt(i) - 'A';
                sum += alpha[n];

                if (alpha[n] == 101){
                    sum = -1;
                    break;
                }
            }

            ans[idx++] = sum;
        }

        return ans;
    }
}