import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Written by 0xc0de1dea
 * Email : 0xc0de1dea@gmail.com
 */

public class Main {
    static class Edge {
        int v;
        double cost;

        public Edge(int v, double cost){
            this.v = v;
            this.cost = cost;
        }
    }

    static class Axis {
        double x, y;

        public Axis(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    static int n;
    static ArrayList<Axis> locations;
    static double[] cost;
    static ArrayList<Edge>[] edges;
    static final double INF = Double.MAX_VALUE / 3;

    public static void dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> (int)(o1.cost - o2.cost));
        pq.add(new Edge(start, 0));
        cost[0] = 0;

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (cur.cost > cost[cur.v]) continue;

            for (Edge next : edges[cur.v]){
                double newCost = cost[cur.v] + next.cost;

                if (newCost < cost[next.v]){
                    cost[next.v] = newCost;
                    pq.add(new Edge(next.v, cost[next.v]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Reader in = new Reader();
        StringBuilder sb = new StringBuilder();

        double startX = in.nextDouble();
        double startY = in.nextDouble();
        double endX = in.nextDouble();
        double endY = in.nextDouble();

        locations = new ArrayList<>();

        locations.add(new Axis(startX, startY));
        locations.add(new Axis(endX, endY));

        n = in.nextInt();

        for (int i = 0; i < n; i++){
            double x = in.nextDouble();
            double y = in.nextDouble();

            locations.add(new Axis(x, y));
        }

        cost = new double[n + 2];
        edges = new ArrayList[n + 2];

        for (int i = 0; i < n + 2; i++){
            cost[i] = INF;
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < n + 2; i++){
            for (int j = 0; j < n + 2; j++){
                if (i == j) continue;

                double time = 0;
                double diff = Math.sqrt(Math.pow(Math.abs(locations.get(i).x - locations.get(j).x), 2) + Math.pow(Math.abs(locations.get(i).y - locations.get(j).y), 2));

                if (i == 0 || j == 0){
                    time = diff / 5;
                } else {
                    if (diff >= 50){
                        time = 2 + (diff - 50) / 5;
                    } else if (diff < 50){
                        time = Math.min(diff / 5, 2 + (50 - diff) / 5);
                    }
                }
                
                edges[i].add(new Edge(j, time));
            }
        }

        dijkstra(0);

        System.out.println(cost[1]);

        System.out.println(Arrays.toString(cost));
    }
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    String nextString() throws Exception {
        StringBuilder sb = new StringBuilder();
        byte c;
        while ((c = read()) < 32) { if (size < 0) return "endLine"; }
        do sb.appendCodePoint(c);
        while ((c = read()) >= 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
        return sb.toString();
    }

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
    int nextInt() throws Exception {
        int n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -1; }
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    long nextLong() throws Exception {
        long n = 0;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32);
        if (c == 45) { c = read(); isMinus = true; }
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = read()));
        return isMinus ? ~n + 1 : n;
    }

    double nextDouble() throws Exception {
        double n = 0, div = 1;
        byte c;
        boolean isMinus = false;
        while ((c = read()) <= 32) { if (size < 0) return -12345; }
        if (c == 45) { c = read(); isMinus = true; }
        else if (c == 46) { c = read(); }
        do n = (n * 10) + (c & 15);
        while (isNumber(c = read()));
        if (c == 46) { while (isNumber(c = read())){ n += (c - 48) / (div *= 10); }}
        return isMinus ? -n : n;
    }

    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }

    boolean isAlphabet(byte c){
        return (64 < c && c < 91) || (96 < c && c < 123);
    }

    byte read() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}