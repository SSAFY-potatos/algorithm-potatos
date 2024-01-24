import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11724_연결요소의개수_조성우 {
    public static int cnt = 0;
    public static int N, M;
    public static int[][] graph;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        int graph_i;
        int graph_j;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph_i = Integer.parseInt(st.nextToken());
            graph_j = Integer.parseInt(st.nextToken());
            graph[graph_i][graph_j] = graph[graph_j][graph_i] = 1;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i])
                cnt++;
            dfs(i);
        }
        System.out.println(cnt);
    }

    public static void dfs(int start) {
        visited[start] = true;
        for (int tarNode = 1; tarNode < N + 1; tarNode++) {
            if (graph[start][tarNode] == 1 && !visited[tarNode]) {
                dfs(tarNode);
            }
        }
    }
}
