class Solution {
    public int[] solution(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);

        int[] ans = new int[2];

        while (!sb.toString().equals("1")){
            ans[0]++;

            for (int i = 0; i < sb.length(); ){
                if (sb.charAt(i) == '0'){
                    ans[1]++;
                    sb.deleteCharAt(i);
                } else {
                    i++;
                }
            }

            String binary = Integer.toBinaryString(sb.length());
            sb.setLength(0);
            sb.append(binary);
        }

        return ans;
    }
}