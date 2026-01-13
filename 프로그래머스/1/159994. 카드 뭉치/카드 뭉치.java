class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int left = 0;
        int right = 0;

        for (String s : goal){
            if (s.equals(cards1[left])){
                if (left < cards1.length - 1) left++;

                continue;
            }

            if (s.equals(cards2[right])){
                if (right < cards2.length - 1) right++;

                continue;
            }

            return "No";
        }

        return "Yes";
    }
}