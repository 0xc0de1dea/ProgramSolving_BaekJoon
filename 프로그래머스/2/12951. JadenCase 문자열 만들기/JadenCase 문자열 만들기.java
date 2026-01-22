class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean flag = true;

        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' '){
                sb.append(' ');
                flag = true;
            } else {
                if (flag){
                    sb.append(Character.toUpperCase(s.charAt(i)));
                    flag = false;
                } else {
                    sb.append(Character.toLowerCase(s.charAt(i)));
                }
            }
        }

        return sb.toString();
    }
}