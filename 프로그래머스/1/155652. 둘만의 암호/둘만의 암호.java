class Solution {
    public boolean chk(String s, String skip){
        return s.matches(".*[" + skip + "].*");
    }

    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()){
            int n = c - 'a';

            for (int i = 0; i < index; i++){
                n++;
                n %= 26;

                while (chk(String.valueOf((char)(n + 'a')), skip)){
                    n++;
                    n %= 26;
                }

                n %= 26;
            }

            sb.append((char)(n + 'a'));
        }

        return sb.toString();
    }
}