import java.io.*;

public class Main {
    static int N;
    static char c[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str;
        while((str = br.readLine()) != null){
            N = Integer.parseInt(str); //N

            int cnt = (int) Math.pow(3,N);
            c = new char[cnt];

            for(int i=0;i<cnt;i++) c[i] = '-';

            dfs(0,cnt);

            for(int i=0;i<cnt;i++) bw.write(c[i]);
            bw.write("\n");
            bw.flush();
        }
    }

    static void dfs(int start,int length){
        if(length < 3) {
            return;
        }

        for(int i=start+length/3;i<start+length/3*2;i++) c[i] = ' ';
        dfs(start,length/3);
        dfs(start+length/3*2,length/3);
    }
}//!@#$%*(*%$)