import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    public int x, y;

    public Edge(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Edge target){
        return this.x - target.x;
    }
}

public class Main {
    public static int lowerBound(int[] arr, int s, int e, int target){
        while (s != e){
            int m = (s + e) / 2;

            if (arr[m] < target) s = m + 1;
            else e = m;
        }
        return e;
    }

    public static boolean binarySearch(int[] arr, int s, int e, int target){
        while (s <= e){
            int m = (s + e) / 2;

            if (arr[m] < target) s = m + 1;
            else if (arr[m] > target) e = m - 1;
            else return true;
        }
        return false;
    }

    public static void trace(ArrayList<Edge> edge, ArrayList<Integer> idxArr, int[] copyArr, int len){
        int size = idxArr.size();

        while (len > 0){
            size--;

            if (idxArr.get(size) == len){
                copyArr[len--] = edge.get(size).y;
            }
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        ArrayList<Edge> edge = new ArrayList<>();
        int[] lis = new int[n + 1];
        ArrayList<Integer> idxArr = new ArrayList<>();
        int len = 0;

        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge.add(new Edge(a, b));
        }

        Collections.sort(edge);
        
        for (int i = 0; i < n; i++){
            if (edge.get(i).y > lis[len]){
                lis[++len] = edge.get(i).y;
                idxArr.add(len);
            }
            else {
                int idx = lowerBound(lis, 1, len, edge.get(i).y);
                lis[idx] = edge.get(i).y;
                idxArr.add(idx);
            }
        }
        
        sb.append(n - len).append('\n');
        trace(edge, idxArr, lis, len);

        int[] ansArr = new int[n - len];
        int idx = 0;

        for (int i = 0; i < n; i++){
            boolean check = binarySearch(lis, 1, len, edge.get(i).y);

            if (!check){
                ansArr[idx++] = edge.get(i).x;
            }                
        }

        Arrays.sort(ansArr);

        for (int e : ansArr){
            sb.append(e).append('\n');
        }

        System.out.println(sb);
    }
}