import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    private int L;
    private int R;
    private int C;
    private int mt;

    public Node(int l, int r, int c, int mt) {
        L = l;
        R = r;
        C = c;
        this.mt = mt;
    }

    public int getL() {
        return L;
    }

    public int getR() {
        return R;
    }

    public int getC() {
        return C;
    }

    public int getMt() {
        return mt;
    }
}

public class Main {
    static int[] dl = {0,0,0,0,1,-1};
    static int[] dr = {1,-1,0,0,0,0};
    static int[] dc = {0,0,1,-1,0,0};

    static int bfs(int L,int R,int C,Node n,char[][][] graph, boolean[][][] visited){
        Queue<Node> queue = new LinkedList<>();
        queue.add(n);
        visited[n.getL()][n.getR()][n.getC()] = true;

        while(queue.size()!=0){
            Node v = queue.poll();
            if(graph[v.getL()][v.getR()][v.getC()]=='E'){
                return v.getMt();
            }

            for(int i=0;i<6;i++){
                int il = dl[i]+v.getL();
                int ir = dr[i]+v.getR();
                int ic = dc[i]+v.getC();

                if(il>=0 && il<L && ir>=0 && ir<R &&ic >=0 && ic<C){
                    if(graph[il][ir][ic] != '#' && !visited[il][ir][ic]){
                        queue.add(new Node(il,ir,ic,v.getMt()+1));
                        visited[il][ir][ic] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int L;
        int R;
        int C;
        Node S= null;

        while(true){
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L==0 && R==0 && C==0){
                break;
            }

            char[][][] graph = new char[L][R][C];
            boolean[][][] visited = new boolean[L][R][C];

            for(int i=0;i<L;i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < C; k++) {
                        graph[i][j][k] = s.charAt(k);
                        if (s.charAt(k) == 'S') {
                            S = new Node(i, j, k, 0);
                        }
                    }
                }
                br.readLine();
            }

            int answer = bfs(L,R,C,S,graph,visited);
            if(answer== -1){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in "+answer+" minute(s).");
            }
        }
    }
}