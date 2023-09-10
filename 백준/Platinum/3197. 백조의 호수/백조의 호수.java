import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.LinkedList;

// 좌표 정보
class Node{
    int row, col;

    Node(int _row, int _col){
        this.row = _row;
        this.col = _col;
    }
}

public class Main{
    static LinkedList<Node> srch = new LinkedList<>(); // Breadth First Search를 위한 연결리스트
    static LinkedList<Node> h2o = new LinkedList<>(); // 물좌표에 해당하는 연결리스트
    static LinkedList<Node> swanLoc = new LinkedList<>(); // 백조 좌표 정보
    static char[][] graph; // 그래프 구조정보 ('X'는 빙산 '.'는 물 'L'는 백조)
    static boolean[][] isVisited; // 해당 노드를 방문하였는가
    static int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 사방위
    static int r, c; // 그래프의 width와 height크기

    // 연결 확인
    private static boolean isConnect(LinkedList<Node> waitingQueue){
        Iterator<Node> i = srch.listIterator();

        while (!srch.isEmpty()){
            Node n = srch.pop();

            if (n.row == swanLoc.peek().row && n.col == swanLoc.peek().col){
                return true;
            }

            for (int a = 0; a < dir.length; a++){
                int newRow = n.row + dir[a][0];
                int newCol = n.col + dir[a][1];

                if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c){
                    if (!isVisited[newRow][newCol]){
                        isVisited[newRow][newCol] = true;
                        
                        if (graph[newRow][newCol] == 'X'){
                            waitingQueue.add(new Node(newRow, newCol));
                        }
                        else{
                            srch.add(new Node(newRow, newCol));
                        }
                    }
                }
            }
        }
        return false;
    }

    // 빙산 녹이기
    private static void meltIce(){
        int sizeConstant = h2o.size();

        for (int i = 0; i < sizeConstant; i++){
            Node n = h2o.pop();

            for (int a = 0; a < dir.length; a++){
                int newRow = n.row + dir[a][0];
                int newCol = n.col + dir[a][1];

                if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c){
                    if (graph[newRow][newCol] == 'X'){
                        graph[newRow][newCol] = '.';
                        h2o.add(new Node(newRow, newCol));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        graph = new char[r][c];
        isVisited = new boolean[r][c];

        int cntDay = 0; // 걸린 일 수
        boolean isConnect = false; // 연결체크

        // Initializing
        for (int i = 0; i < r; i++){
            char[] tmp = br.readLine().toCharArray();

            for (int j = 0; j < c; j++){
                graph[i][j] = tmp[j];

                if (graph[i][j] == 'L')
                    swanLoc.add(new Node(i, j));
                if (graph[i][j] != 'X')
                    h2o.add(new Node(i, j));
            }
        }

        //System.out.println(); 테스트용1

        // Breadth First Search의 시작점 설정
        srch.add(swanLoc.peek());
        isVisited[swanLoc.element().row][swanLoc.pop().col] = true;

        // 계산
        outerLoop: while (true){
            LinkedList<Node> waitingQueue = new LinkedList<>();
            isConnect = Main.isConnect(waitingQueue);

            if (isConnect) break outerLoop;

            meltIce();
            srch = waitingQueue;
            cntDay++;

            /*for (int i = 0; i < r; i++){
                for (int j = 0; j < c; j++){
                    System.out.print(graph[i][j]);
                }
                System.out.println();
            }
            System.out.println(); 테스트용 2*/
        }
        System.out.print(cntDay);
    }
}