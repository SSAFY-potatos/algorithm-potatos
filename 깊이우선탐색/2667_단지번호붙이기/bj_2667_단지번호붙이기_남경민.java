import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Apt {
    private int x;
    private int y;

    public Apt() {
        super();
    }
    public Apt(int x, int y) {
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
    static int[][] graph;
    static boolean[][] visited;
    static int N;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static int dfs(Apt n,int cnt) {
        visited[n.getX()][n.getY()] = true;
        for(int i=0;i<4;i++) {
            int xi = n.getX()+dx[i];
            int yi = n.getY()+dy[i];
            if(xi>=0 && xi<N && yi>=0 && yi<N) {
                if(graph[xi][yi]==1&&!visited[xi][yi]) {
                    cnt = dfs(new Apt(xi,yi),cnt+1);
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];
        List<Integer> Ans = new ArrayList<>();
        int cnt =0;

        for(int i=0;i<N;i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(graph[i][j] ==1 && !visited[i][j]) {
                    int ans = dfs(new Apt(i,j),1);
                    Ans.add(ans);
                }
            }
        }
        System.out.println(Ans.size());
        Collections.sort(Ans);
        for(int a:Ans) System.out.println(a);
    }
}
