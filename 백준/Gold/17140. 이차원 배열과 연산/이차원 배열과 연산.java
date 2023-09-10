import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[101][101];
        int[] cntArray = new int[51];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2){
                if (o1[1] != o2[1]){
                    return o1[1] - o2[1];
                }
                else {
                    return o1[0] - o2[0];
                }
            } 
        });

        for (int i = 1; i <= 3; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 3; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int lenR = 3;
        int lenC = 3;
        int cnt = 0;

        for (int i = 0; i < 101; i++){
            if (matrix[r][c] == k){
                break;
            }

            if (lenR >= lenC){
                for (int j = 1; j <= lenR; j++){
                    cntArray = new int[101];
                    int searchMax = 0;

                    for (int l = 1; l <= lenC; l++){
                        if (matrix[j][l] == 0) continue;

                        cntArray[matrix[j][l]]++;
                        searchMax = Math.max(searchMax, matrix[j][l]);
                    }

                    for (int l = 1; l <= searchMax; l++){
                        if (cntArray[l] == 0) continue;

                        pq.add(new int[] { l, cntArray[l] });
                    }

                    lenC = Math.max(lenC, pq.size() * 2);
                    int idx = 1;

                    while (idx <= 100 && pq.size() != 0){
                        matrix[j][idx++] = pq.peek()[0];
                        matrix[j][idx++] = pq.poll()[1];
                    }

                    while (idx <= lenC){
                        matrix[j][idx++] = 0;
                    }
                }
            }
            else {
                for (int j = 1; j <= lenC; j++){
                    cntArray = new int[101];
                    int searchMax = 0;

                    for (int l = 1; l <= lenR; l++){
                        if (matrix[l][j] == 0) continue;

                        cntArray[matrix[l][j]]++;
                        searchMax = Math.max(searchMax, matrix[l][j]);
                    }

                    for (int l = 1; l <= searchMax; l++){
                        if (cntArray[l] == 0) continue;

                        pq.add(new int[] { l, cntArray[l] });
                    }

                    lenR = Math.max(lenR, pq.size() * 2);

                    int idx = 1;

                    while (idx <= 100 && pq.size() != 0){
                        matrix[idx++][j] = pq.peek()[0];
                        matrix[idx++][j] = pq.poll()[1];
                    }

                    while (idx <= lenR){
                        matrix[idx++][j] = 0;
                    }
                }
            }

            pq.clear();
            cnt++;

            /*for (int x = 1; x <= 3; x++){
                for (int y = 1; y <= 10; y++){
                    sb.append(matrix[x][y]).append(' ');
                }

                sb.append('\n');
            }

            sb.append('\n');*/
        }

        if (cnt != 101){
            System.out.println(cnt);
        }
        else {
            System.out.println(-1);
        }

        //System.out.println(sb);
    }
}