import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static int[][] arr;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        arr = new int[m][n];

        stk = new StringTokenizer(br.readLine());
        int truthNum = Integer.parseInt(stk.nextToken());
        boolean isTruth = true;
        int[] truthMan = new int[truthNum];
        int mainTruth = 0;

        if (truthNum == 0) {
            isTruth = false;
        } else {
            truthMan[0] = Integer.parseInt(stk.nextToken());
            mainTruth = truthMan[0];
            rank[mainTruth] = 123456789;
            union(mainTruth, truthMan[0]);
        }

        for (int i = 1; i < truthNum; i++) {
            truthMan[i] = Integer.parseInt(stk.nextToken());
            union(mainTruth, truthMan[i]);
        }

        StringBuilder sb = new StringBuilder();
        int answer = 0;
        if (isTruth) {
            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int partyNum = Integer.parseInt(stk.nextToken());
                boolean isTruthKnown = false;

                for (int j = 0; j < partyNum; j++) {
                    arr[i][j] = Integer.parseInt(stk.nextToken());
                    if (parent[arr[i][j]] == mainTruth) {
                        isTruthKnown = true;
                    }
                }

                int idx = 0;
                if (isTruthKnown) {
                    while (idx < n && arr[i][idx] != 0) {
                        union(0, arr[i][idx]);
                        idx++;
                    }
                } else {
                    while (idx < n && arr[i][idx + 1] != 0) {
                        union(arr[i][0], arr[i][idx + 1]);
                        idx++;
                    }
                }
            }
        } else {
            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                int partyNum = Integer.parseInt(stk.nextToken());
                for (int j = 0; j < partyNum; j++) {
                    arr[i][j] = Integer.parseInt(stk.nextToken());
                }
            }
            answer = m;
            sb.append(answer);
        }

        if (isTruth) {
            answer = m;
            
            for (int[] i : arr) {
                boolean check = true;

                for (int j : i) {
                    if (j == 0) break;
                    if (find(j) == mainTruth) {
                        check = false;
                        break;
                    }
                }

                if (!check) {
                    answer--;
                }

            }
            sb.append(answer);
        }

        System.out.println(sb);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (rank[a] >= rank[b]) parent[b] = a;
            else parent[a] = b;

            if (rank[a] == rank[b]) rank[a]++;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }
}