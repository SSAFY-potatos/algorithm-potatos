import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1012_유기농배추_조성우 {

    public static boolean[][] graph;
    public static boolean[][] visited;
    public static int T;
    public static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new boolean[N][M];
            visited = new boolean[N][M];

            int x;
            int y;
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                graph[y][x] = true;
            }

            int cnt = 0;
            // M*N으로 전체 좌표에 대해, 방문검증 & 배추있을 시, 방문처리 및 dfs 시작 & cnt++
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (graph[i][j] == true && visited[i][j] == false) {
                        cnt++;
                        dfs(j, i);
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void dfs(int cur_x, int cur_y) {
        visited[cur_y][cur_x] = true;
        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { -1, 0, 1, 0 };
        // 상우하좌(for) 배추유무확인 & 보드 유효성 검증
        // 검증 성공 시, 해당 좌표로 dfs 호출
        int mv_x, mv_y;
        for (int idxDir = 0; idxDir < 4; idxDir++) {
            mv_x = cur_x + dx[idxDir];
            mv_y = cur_y + dy[idxDir];
            if (mv_x >= 0 && mv_x < M && mv_y >= 0 && mv_y < N) {
                if (graph[mv_y][mv_x] == true && visited[mv_y][mv_x] == false) {
                    dfs(mv_x, mv_y);
                }
            }
        }
    }
}
