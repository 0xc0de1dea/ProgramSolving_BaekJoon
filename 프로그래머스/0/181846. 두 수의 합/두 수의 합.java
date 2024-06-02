class Solution {
    public String solution(String a, String b) {
        String answer = "";

        char[] aArr = new char[a.length()];
        char[] bArr = new char[b.length()];

        for (int i = 0; i < a.length(); i++){
            aArr[i] = a.charAt(a.length() - 1 - i);
        }

        for (int i = 0; i < b.length(); i++){
            bArr[i] = b.charAt(b.length() - 1- i);
        }

        StringBuilder sb = new StringBuilder();
        int ptr = 0;
        int carry = 0;

        while (ptr < a.length() && ptr < b.length()){;
            int add = (aArr[ptr] - '0') + (bArr[ptr] - '0') + carry;
            sb.append(add % 10);
            carry = add / 10;
            ptr++;
        }

        while (ptr < a.length()){
            int add = (aArr[ptr] - '0') + carry;
            sb.append(add % 10);
            carry = add / 10;
            ptr++;
        }

        while (ptr < b.length()){
            int add = (bArr[ptr] - '0') + carry;
            sb.append(add % 10);
            carry = add / 10;
            ptr++;
        }

        if (carry > 0) sb.append(carry);

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("582", "734"));
    }
}