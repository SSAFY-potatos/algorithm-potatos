import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1743_음식물피하기_조성우 {

    public static boolean[][] visited;
    public static boolean[][] graph;
    public static int N, M, K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        int r, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph[r][c] = true;
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (graph[i][j] && !visited[i][j]) {
                    res = Math.max(dfs(j, i, 1), res);
                }
            }
        }
        System.out.println(res);
    }

    public static int dfs(int cur_c, int cur_r, int dep) {
        visited[cur_r][cur_c] = true;

        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { -1, 0, 1, 0 };

        int mv_c, mv_r;
        for (int idxDir = 0; idxDir < 4; idxDir++) {
            mv_c = cur_c + dx[idxDir];
            mv_r = cur_r + dy[idxDir];

            if (mv_c > 0 && mv_c <= M && mv_r > 0 && mv_r <= N) {
                if (graph[mv_r][mv_c] && !visited[mv_r][mv_c]) {
                    dep += dfs(mv_c, mv_r, 1);
                }
            }
        }
        return dep;
    }

}
