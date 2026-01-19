import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        List<Integer> numList = new ArrayList<>();
        List<Integer> burger = new ArrayList<>();
        burger.add(1);
        burger.add(2);
        burger.add(3);
        burger.add(1);
        
        for(int num : ingredient) {
            numList.add(num);
        }
        int i = 0;
        int k = numList.size() - 3;
        while(i <= k){
            if(numList.get(i) == burger.get(0) && numList.get(i+1) == burger.get(1) && numList.get(i+2) == burger.get(2) && numList.get(i+3) == burger.get(3)) {
                numList.remove(i+3);
                numList.remove(i+2);
                numList.remove(i+1);
                numList.remove(i+0);
                answer++;
                k-=4;
                if(i-4 >= -1) i -= 4;
                else i = -1;
            }
            i++;
        }
        
        return answer;
    }
}