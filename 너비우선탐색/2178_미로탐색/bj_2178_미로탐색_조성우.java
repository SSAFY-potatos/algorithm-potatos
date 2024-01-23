import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2178_미로탐색_조성우 {

    public static int N, M;
    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { -1, 0, 1, 0 };
    public static int[][] board;
    public static boolean[][] visited;
    public static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String strLine = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(strLine.charAt(j)));
            }
        }
        System.out.println(bfs(0, 0));
    }

    public static int bfs(int startX, int startY) {
        int cur_x, cur_y;
        int mv_x = 0, mv_y = 0;
        int[] cur_pos;
        q.add(new int[] { startX, startY });

        visited[startY][startX] = true;
        board[startY][startX] = 1;

        while (!q.isEmpty()) {
            cur_pos = q.poll();
            cur_x = cur_pos[0];
            cur_y = cur_pos[1];

            if (cur_x == M - 1 && cur_y == N - 1) {
                return board[cur_y][cur_x];
            }

            for (int idxDir = 0; idxDir < 4; idxDir++) {
                mv_x = cur_x + dx[idxDir];
                mv_y = cur_y + dy[idxDir];

                if (mv_x < 0 || mv_x >= M || mv_y >= N || mv_y < 0) {
                    continue;
                }
                if (board[mv_y][mv_x] == 1 && !visited[mv_y][mv_x]) {
                    visited[mv_y][mv_x] = true;
                    board[mv_y][mv_x] = board[cur_y][cur_x] + 1;
                    q.add(new int[] { mv_x, mv_y });
                }
            }
        }
        return -1;
    }
}
