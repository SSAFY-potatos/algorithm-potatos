import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class RC {
    private int r;
    private int c;
    private int d;

    public RC(int r, int c,int d) {
        super();
        this.r = r;
        this.c = c;
        this.d = d;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getD(){
        return d;
    }
}
public class Main {
    static int[] dx = {-2,-2,-1,-1,1,1,2,2};
    static int[] dy = {-1,1,-2,2,-2,2,-1,1};

    static int bfs(RC n,int r,int c, int I, boolean[][] graph) {
        Queue<RC> queue = new LinkedList<>();
        queue.add(n);
        graph[n.getR()][n.getC()] = true;

        while(queue.size()!=0){
            RC v = queue.poll();
            if(v.getR() == r && v.getC()==c) {
                return v.getD();
            }
            for(int i=0;i<8;i++) {
                int xi = dx[i] + v.getR();
                int yi = dy[i] + v.getC();

                if(xi>=0 && xi<I && yi>=0 && yi<I) {
                    if(graph[xi][yi] == false) {
                        queue.add(new RC(xi,yi,v.getD()+1));
                        graph[xi][yi] =true;
                    }
                }
            }
        }
        return n.getD();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =null;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++) {
            int I = Integer.parseInt(br.readLine());
            boolean[][] graph = new boolean[I][I];

            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            System.out.println(bfs(new RC(sr,sc,0),er,ec,I,graph));
        }


    }
}