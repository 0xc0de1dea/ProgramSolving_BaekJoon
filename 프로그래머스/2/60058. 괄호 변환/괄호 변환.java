import java.util.Stack;

class Solution {
    public String solution(String p) {
        return solve(p);
    }

    public String solve(String w){
        if (w.length() == 0){
            return w;
        }

        int left = 0;
        int right = 0;
        int splitIdx = 0;

        for (int i = 0; i < w.length(); i++){
            if (w.charAt(i) == '('){
                left++;
            } else {
                right++;
            }

            if (left > 0 && left == right){
                splitIdx = i;
                break;
            }
        }

        String u = w.substring(0, splitIdx + 1);
        String v = w.substring(splitIdx + 1);

        if (isCorrect(u)){
            return u + solve(v);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append('(');
            sb.append(solve(v));
            sb.append(')');

            for (int i = 1; i < u.length() - 1; i++){
                if (u.charAt(i) == '('){
                    sb.append(')');
                } else {
                    sb.append('(');
                }
            }

            return sb.toString();
        }
    }

    public boolean isCorrect(String s){
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()){
            if (c == '('){
                stack.push('(');
            } else {
                if (stack.isEmpty()){
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        
        if (!stack.isEmpty()){
            return false;
        } else {
            return true;
        }
    }
}