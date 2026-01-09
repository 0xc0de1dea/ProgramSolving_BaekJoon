class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        for (char c : s.toCharArray()){
            if (c == ' '){
                sb.append(' ');
                idx = 0;
            } else {
                sb.append((idx++ % 2 == 0) ? Character.toUpperCase(c) : Character.toLowerCase(c));
            }
        }

        return sb.toString();
    }
}