import java.util.*;
 
public class Main {
 
    static int n;
    static List<Integer> list = new ArrayList<>(); //LIS를 담을 리스트
    static Node[] node;
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        n = scan.nextInt();
        
        Map<Integer, Integer> map = new HashMap<>();
        int[] origin_sw = new int[n];
        for(int i = 0; i < n; i++) { 
            int sw = scan.nextInt();
            origin_sw[i] = sw;
            map.put(sw, i);
        } 
 
        int[] bulb = new int[n];
        for(int i = 0; i < n; i++) { 
            bulb[i] = map.get(scan.nextInt());
        } 
 
        node = new Node[n];
        for(int i = 0; i < n; i++) { 
            if(list.size() == 0 || list.get(list.size() - 1) < bulb[i]) {
                list.add(bulb[i]);
                node[i] = new Node(bulb[i], list.size() - 1); // sw와 연결된 전구의 LIS위치
            } else {
                int idx = binarySearch(bulb[i]);
                list.set(idx, bulb[i]);
                node[i] = new Node(bulb[i], idx);
            }
        } 
 
        int idx = list.size() - 1;
        for(int i = n - 1; i >= 0; i--) {
            if(node[i].idx == idx) {
                list.set(idx--, origin_sw[node[i].num]);
            }
        }
        System.out.println(list.size());
 
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + " ");
        }
        System.out.println(sb.toString());
    }
 
    public static int binarySearch(int num) {
        int l = 0;
        int r = list.size() - 1;
        
        while(l <= r) {
            int m = (l + r) / 2;
 
            if(list.get(m) < num) {
                l = m + 1;
            } else{
                r = m - 1;
            }
        }
        return l;
    }
 
    public static class Node {
        int num;
        int idx;
 
        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
}