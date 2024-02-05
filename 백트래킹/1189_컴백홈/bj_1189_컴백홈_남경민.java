import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class ComeBackHome{
    int x;
    int y;
    int d;

    public ComeBackHome() {
    }

    public ComeBackHome(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class Main {
    static int R,C,K;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int cnt=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = s.charAt(j);
            }
        }

        DFS(new ComeBackHome(R-1,0,1));
        System.out.println(cnt);

    }
    private static void DFS(ComeBackHome n){
        visited[n.x][n.y] = true;

        if(n.x == 0 && n.y == C-1 && n.d == K){
            cnt ++;
        }

        for (int i = 0; i < 4; i++) {
            int xi = n.x + dx[i];
            int yi = n.y + dy[i];

            if(xi< 0 || xi >=R || yi <0 || yi>=C) continue;
            if(visited[xi][yi] || graph[xi][yi] == 'T') continue;
//            if(n.d + 1 > K) continue; // depth가 K 넘으면 안들어가게 함

            DFS(new ComeBackHome(xi,yi,n.d+1));
            visited[xi][yi] = false;
        }
    }
}
