import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1189_컴백홈_조성우 {

    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };
    static char[][] board;
    static boolean[][] visited;
    static int R, C, K;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = inputLine.charAt(j);
            }
        }

        visited[R - 1][0] = true;
        dfs(0, new Coord(0, R - 1));
        System.out.println(cnt);
    }

    static void dfs(int dep, Coord cur_coord) {
        int cur_x = cur_coord.x;
        int cur_y = cur_coord.y;

        if (dep + 1 == K) {
            if (cur_y == 0 && cur_x == C - 1) {
                cnt++;
            }
            return;
        }

        int mv_x, mv_y;
        for (int idxDir = 0; idxDir < 4; idxDir++) {
            mv_x = cur_x + dx[idxDir];
            mv_y = cur_y + dy[idxDir];

            if (mv_x < 0 || mv_x >= C || mv_y < 0 || mv_y >= R) {
                continue;
            }
            if (board[mv_y][mv_x] == 'T') {
                continue;
            }
            if (visited[mv_y][mv_x]) {
                continue;
            }

            visited[mv_y][mv_x] = true;
            dfs(dep + 1, new Coord(mv_x, mv_y));
            visited[mv_y][mv_x] = false;

        }
    }

    static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}