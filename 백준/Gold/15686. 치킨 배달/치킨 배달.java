import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
 
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int m;
    static ArrayList<Point> chicken = new ArrayList<>();
    static ArrayList<Point> house = new ArrayList<>();
    static int[][] dist = new int[13][100];
    static boolean[] isVisited;
    static int min = Integer.MAX_VALUE;

    public static void backtracking(int depth, int start){
        if (depth == m){
            int sum = 0;
            int size = house.size();
            int size2 = chicken.size();
    
            for (int i = 0; i < size; i++){
                int subMin = Integer.MAX_VALUE;
    
                for (int j = 0; j < size2; j++){
                    if (isVisited[j]){
                        subMin = Math.min(subMin, dist[j][i]);
                    }
                }
    
                sum += subMin;
            }
    
            min = Math.min(min, sum);
            return;
        }

        int size = chicken.size();

        for (int i = start; i < size; i++){
            isVisited[i] = true;
            backtracking(depth + 1, i + 1);
            isVisited[i] = false;
        }
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                int check = Integer.parseInt(st.nextToken());

                if (check == 1){
                    house.add(new Point(i, j));
                }
                else if (check == 2){
                    chicken.add(new Point(i, j));
                }
            }
        }

        isVisited = new boolean[chicken.size()];
        int chickenLen = chicken.size();
        int houseLen = house.size();

        for (int i = 0; i < chickenLen; i++){
            for (int j = 0; j < houseLen; j++){
                dist[i][j] = Math.abs(house.get(j).x - chicken.get(i).x) + Math.abs(house.get(j).y - chicken.get(i).y);
            }
        }

        backtracking(0, 0);
        System.out.println(min);
    }
}