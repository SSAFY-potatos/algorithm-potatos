import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
    private int x;
    private int y;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Node(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    public static void dfs(Node n,int[][] graph, boolean[][] visited,int N,int M) {
        visited[n.getX()][n.getY()] = true;
        for(int i=0;i<4;i++) {
            int xi = dx[i] + n.getX();
            int yi = dy[i] + n.getY();

            if(xi>=0 && xi<N&& yi>=0&&yi<M) {
                if(graph[xi][yi] ==1 && visited[xi][yi] == false) {
                    dfs(new Node(xi, yi), graph, visited, N, M);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int t=0;t<T;t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int cnt =0;

            int[][] graph = new int[N][M];
            boolean[][] visitied = new boolean[N][M];

            for(int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[y][x] = 1;
            }

            for(int j=0;j<N;j++) {
                for(int k=0;k<M;k++) {
                    if(visitied[j][k] == false && graph[j][k] ==1) {
                        dfs(new Node(j, k), graph, visitied,N,M);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);

        }
    }
}
