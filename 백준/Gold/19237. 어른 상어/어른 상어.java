import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Shark {
    public int id;
    public int r, c;
    public int dir;

    public Shark(int id, int r, int c, int dir){
        this.id = id;
        this.r = r;
        this.c = c;
        this.dir = dir;
    }
}

public class Main {
    static int n, m, k;
    static int[][][] map;
    static ArrayList<Shark> shark = new ArrayList<>();
    static int[][][] priority;
    static int[] dr = new int[] { -1, 1, 0, 0 };
    static int[] dc = new int[] { 0, 0, -1, 1 };

    public static int binarySearch(int target) {
		int l = 0;
		int r = shark.size() - 1;
		int mid = 0;
		while (l <= r) {
			mid = (l + r) / 2;
			if (shark.get(mid).id == target)
				return mid;
			// l mid target r
			if (shark.get(mid).id < target)
				l = mid + 1;
			else
				r = mid - 1;
		}
		return mid;
	}

    public static int simulation(){
        int elapsedTime = 0;
        boolean check = false;
        int limit = 1001;

        while (limit-- > 0){
            int size = shark.size();

            if (size == 1){
                check = true;
                break;
            }

            for (int i = 0; i < size; i++){
                Shark s = shark.get(i);
                int nr = 0;
                int nc = 0;
                boolean isMoved = false;

                for (int j = 0; j < 4; j++){
                    nr = s.r + dr[priority[s.id][s.dir][j]];
                    nc = s.c + dc[priority[s.id][s.dir][j]];

                    if (map[nr][nc][0] != -1 && map[nr][nc][1] == 0){
                        map[s.r][s.c][0] = 0;
                        map[s.r][s.c][1] = s.id;
                        map[s.r][s.c][2] = k;
                        s.r = nr;
                        s.c = nc;
                        s.dir = priority[s.id][s.dir][j];
                        isMoved = true;
                        break;
                    }
                }

                if (!isMoved){
                    for (int j = 0; j < 4; j++){
                        nr = s.r + dr[priority[s.id][s.dir][j]];
                        nc = s.c + dc[priority[s.id][s.dir][j]];

                        if (map[nr][nc][1] == s.id){
                            map[s.r][s.c][0] = 0;
                            map[s.r][s.c][1] = s.id;
                            map[s.r][s.c][2] = k;
                            s.r = nr;
                            s.c = nc;
                            s.dir = priority[s.id][s.dir][j];
                            break;
                        }
                    }
                }
            }

            for (int i = 1; i <= n; i++){
                for (int j = 1; j <= n; j++){
                    if (map[i][j][2] > 1){
                        map[i][j][2]--;
                    }
                    else if (map[i][j][2] == 1){
                        map[i][j][1] = map[i][j][2] = 0;
                    }
                }
            }

            for (int i = 0; i < shark.size(); ){
                Shark s = shark.get(i);

                if (map[s.r][s.c][0] == 0){
                    map[s.r][s.c][0] = s.id;
                    i++;
                }
                else if (map[s.r][s.c][0] < s.id){
                    shark.remove(i);
                }
                else {
                    int idx = binarySearch(map[s.r][s.c][0]);
                    shark.remove(idx);

                    if (idx > i){
                        i++;
                    }
                }

                map[s.r][s.c][1] = s.id;
                map[s.r][s.c][2] = k;
            }

            elapsedTime++;
        }

        return check == true ? elapsedTime : -1;
    }

    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n + 2][n + 2][3];
        priority = new int[m + 1][4][4];

        for (int i = 0; i <= n + 1; i++){
            map[0][i][0] = map[i][0][0] = map[i][n + 1][0] = map[n + 1][i][0] = -1;
        }

        for (int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++){
                map[i][j][0] = Integer.parseInt(st.nextToken());

                if (map[i][j][0] > 0){
                    shark.add(new Shark(map[i][j][0], i, j, 0));
                }
            }
        }

        Collections.sort(shark, new Comparator<Shark>() {
            @Override
            public int compare(Shark s1, Shark s2){
                return s1.id - s2.id;
            }
        });

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++){
            int dir = Integer.parseInt(st.nextToken()) - 1;
            shark.get(i).dir = dir;
        }

        for (int id = 1; id <= m; id++){
            for (int i = 0; i < 4; i++){
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 4; j++){
                    int dir = Integer.parseInt(st.nextToken()) - 1;
                    priority[id][i][j] = dir;
                }
            }
        }

        int elapsedTime = simulation();
        System.out.println(elapsedTime);
    }
}