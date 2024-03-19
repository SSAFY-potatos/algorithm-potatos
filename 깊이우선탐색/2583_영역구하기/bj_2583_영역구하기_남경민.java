import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Paper{
    private int x;
    private int y;
    public Paper() {
        super();
    }
    public Paper(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[][] graph;
    static boolean[][] visited;
    static int N,M;

    private static int dfs(Paper n,int cnt) {
        visited[n.getX()][n.getY()] = true;
        for (int i = 0; i < dx.length; i++) {
            int xi = n.getX() + dx[i];
            int yi = n.getY() + dy[i];
            if(xi>=0 && xi<N && yi>=0 && yi<M) {
                if(!visited[xi][yi]&& graph[xi][yi]==0) {
                    cnt = dfs(new Paper(xi,yi),cnt+1);
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> Ans = new ArrayList<>();
        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j <x2 ; j++) {
                for(int k=y1;k<y2;k++) {
                    graph[j][k] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j]&& graph[i][j] ==0) {
                    int cnt = dfs(new Paper(i,j),1);
                    Ans.add(cnt);
                }
            }
        }
        System.out.println(Ans.size());
        Collections.sort(Ans);
        for(int a:Ans) System.out.print(a+" ");
    }
}
