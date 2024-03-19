import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class RGB{
    int x;
    int y;

    public RGB() {
    }

    public RGB(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int N;
    static char[][] graph;
    static boolean[][] visited,visited2;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        visited = new boolean[N][N];
        visited2 = new boolean[N][N];
        int cnt = 0;
        int cnt2 = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]){
                    DFS(new RGB(i,j));
                    cnt++;
                }
                if(!visited2[i][j]){
                    DFS2(new RGB(i,j));
                    cnt2++;
                }
            }
        }
        System.out.print(cnt+" "+cnt2);
    }

    private static void DFS(RGB n){
        visited[n.x][n.y] = true;
        for (int i = 0; i < 4; i++) {
            int xi = n.x + dx[i];
            int yi = n.y + dy[i];
            if(xi<0 || xi>=N || yi<0 || yi >=N){
                continue;
            }
            if(visited[xi][yi] || graph[n.x][n.y]!=graph[xi][yi] ){
                continue;
            }
            DFS(new RGB(xi,yi));
        }
    }
    // 적록색약 DFS
    private static void DFS2(RGB n){
        visited2[n.x][n.y] = true;
        for (int i = 0; i < 4; i++) {
            int xi = n.x + dx[i];
            int yi = n.y + dy[i];
            if(xi<0 || xi>=N || yi<0 || yi >=N){
                continue;
            }
            if(visited2[xi][yi]){
                continue;
            }
            if(graph[n.x][n.y] == 'B' ){
                if(graph[n.x][n.y] != graph[xi][yi]) continue;
            }else{
                if(graph[xi][yi] == 'B') continue;
            }

            DFS2(new RGB(xi,yi));
        }
    }
}
