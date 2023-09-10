import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[][] gearWheel = new int[t + 1][8];
        int[][] contactLoc = new int[t + 1][2];
        
        for (int i = 1; i <= t; i++){
            String[] input = br.readLine().split("");

            for (int j = 0; j < 8; j++){
                gearWheel[i][j] = Integer.parseInt(input[j]);
            }

            contactLoc[i][0] = 2;
            contactLoc[i][1] = 6;
        }
        
        int k = Integer.parseInt(br.readLine());
        int[][] rotData = new int[k + 1][2];

        for (int i = 1; i <= k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 2; j++){
                rotData[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= k; i++){
            int[] saveRot = new int[t + 1];
            saveRot[rotData[i][0]] = rotData[i][1];

            for (int l = rotData[i][0] - 1; l > 0; l--){
                if (gearWheel[l + 1][contactLoc[l + 1][1]] != gearWheel[l][contactLoc[l][0]]){
                    saveRot[l] = -1 * saveRot[l + 1];
                }
                else {
                    break;
                }
            }

            for (int r = rotData[i][0] + 1; r <= t; r++){
                if (gearWheel[r - 1][contactLoc[r - 1][0]] != gearWheel[r][contactLoc[r][1]]){
                    saveRot[r] = -1 * saveRot[r - 1];
                }
                else {
                    break;
                }
            }

            for (int l = rotData[i][0]; l > 0; l--){
                if (saveRot[l] != 0){
                    contactLoc[l][0] = (contactLoc[l][0] - saveRot[l] + 8) % 8;
                    contactLoc[l][1] = (contactLoc[l][1] - saveRot[l] + 8) % 8;
                }
                else {
                    break;
                }
            }

            for (int r = rotData[i][0] + 1; r <= t; r++){
                if (saveRot[r] != 0){
                    contactLoc[r][0] = (contactLoc[r][0] - saveRot[r] + 8) % 8;
                    contactLoc[r][1] = (contactLoc[r][1] - saveRot[r] + 8) % 8;
                }
                else {
                    break;
                }
            }
        }

        int cnt = 0;

        for (int i = 1; i <= t; i++){
            if (gearWheel[i][(contactLoc[i][1] + 2) % 8] == 1){
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}