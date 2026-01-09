class Solution {
    public String solution(String s, int n) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()){
            if (c == ' '){
                sb.append(' ');
            } else {
                if (Character.isUpperCase(c)){
                    int val = c - 'A';
                    sb.append(upper.charAt((val + n) % 26));
                } else {
                    int val = c - 'a';
                    sb.append(lower.charAt((val + n) % 26));
                }
            }
        }

        return sb.toString();
    }
}