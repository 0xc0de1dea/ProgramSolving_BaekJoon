import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeData {
    public int emoticon;
    public int clipboard;
    public int time;

    public NodeData(int emoticon, int clipboard, int time) {
        this.emoticon = emoticon;
        this.clipboard = clipboard;
        this.time = time;
    }
}

public class Main {
    public static void main(String[] argu) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int s = Integer.parseInt(br.readLine());
        boolean[][] isVisited = new boolean[2001][2001];
        
        Queue<NodeData> q = new LinkedList<>();
        q.add(new NodeData(1, 0, 0));
        isVisited[1][0] = true;

        while (q.size() != 0){
            int emo = q.peek().emoticon;
            int clip = q.peek().clipboard;
            int time = q.remove().time;

            if (emo == s){
                sb.append(time);
                break;
            }

            if (0 < emo && emo <= 1000){
                if (!isVisited[emo][emo]){
                    isVisited[emo][emo] = true;
                    q.add(new NodeData(emo, emo, time + 1));
                }

                if (!isVisited[emo + clip][clip]){
                    if (emo + clip <= 1000){
                    isVisited[emo + clip][clip] = true;
                    q.add(new NodeData(emo + clip, clip, time + 1));
                    }
                }

                if (!isVisited[emo - 1][clip]){
                    isVisited[emo - 1][clip] = true;
                    q.add(new NodeData(emo - 1, clip, time + 1));
                }
            }
        }

        System.out.println(sb);
    }
}