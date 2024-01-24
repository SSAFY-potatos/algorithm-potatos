import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class RC {
    private int r;
    private int c;

    public RC(int r, int c) {
        super();
        this.r = r;
        this.c = c;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}
public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int cnt =0;

    public static int dfs(RC n, int N,int M,int[][] graph, boolean[][] visited) {
        visited[n.getR()][n.getC()] = true;
        cnt++;

        for(int i=0;i<4;i++) {
            int xi = dx[i]+n.getR();
            int yi = dy[i]+n.getC();
            if(xi>=0 && xi<N&&yi>=0 &&yi<M) {
                if(graph[xi][yi]==1 && visited[xi][yi] ==false) {
                    dfs(new RC(xi,yi),N,M,graph,visited);
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer=0;

        int[][] graph = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[r-1][c-1] = 1;
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(visited[i][j]==false && graph[i][j]==1) {
                    cnt =0;
                    answer = Math.max(answer, dfs(new RC(i,j), N, M, graph, visited));
                }

            }
        }
        System.out.println(answer);
    }
}
