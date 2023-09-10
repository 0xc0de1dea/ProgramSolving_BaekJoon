import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] dx = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };
        int[] dy = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[][] tree = new ArrayList[n][n];
        int[][] soil = new int[n][n];
        int[][] supply = new int[n][n];
        int[][] saved = new int[n][n];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                tree[i][j] = new ArrayList<>();
            }
        }
        
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++){
                soil[i][j] = 5;
                supply[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            tree[r][c].add(age);
        }

        while(k-- > 0){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    tree[i][j].sort(Comparator.naturalOrder());

                    for (int l = 0; l < tree[i][j].size(); ){
                        if (soil[i][j] >= tree[i][j].get(l)){
                            soil[i][j] -= tree[i][j].get(l);
                            tree[i][j].set(l, tree[i][j].get(l) + 1);
                            l++;
                        }
                        else {
                            saved[i][j] += tree[i][j].get(l) / 2;
                            tree[i][j].remove(l);
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    soil[i][j] += saved[i][j];
                    saved[i][j] = 0;
                }
            }

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    for (int p = 0; p < tree[i][j].size(); p++){
                        if (tree[i][j].get(p) % 5 == 0){
                            for (int q = 0; q < 8; q++){
                                int nx = j + dx[q];
                                int ny = i + dy[q];

                                if (nx >= 0 && nx < n && ny >= 0 && ny < n){
                                    tree[ny][nx].add(1);
                                }
                            }
                        }

                    }
                }
            }

            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    soil[i][j] += supply[i][j];
                }
            }
        }

        int cnt = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                cnt += tree[i][j].size();
            }
        }

        System.out.println(cnt);
    }  
}