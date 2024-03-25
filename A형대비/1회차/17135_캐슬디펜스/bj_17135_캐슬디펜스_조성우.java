import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_17135_캐슬디펜스_조성우 {

    static boolean[][] board;
    static boolean[][] boardOrigin;
    static boolean[][] visited;
    static boolean[] castleArr;
    static int[] castleIdxs;
    static int N, M, D;
    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        boardOrigin = new boolean[N][M];
        board = new boolean[N][M];
        visited = new boolean[N][M];
        castleArr = new boolean[M];
        castleIdxs = new int[M];
        for (int i = 0; i < M; i++) {
            castleIdxs[i] = i;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                boardOrigin[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }
        makeCombination(0, new int[3], 0);
        System.out.print(maxScore);
    }

    static void makeCombination(int toSelect, int[] selected, int startIdx) {
        if (toSelect == 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = boardOrigin[i][j];
                }
            }

            visited = new boolean[N][M];
            simulation(selected);
            return;
        }
        for (int i = startIdx; i < castleIdxs.length; i++) {
            selected[toSelect] = castleIdxs[i];
            makeCombination(toSelect + 1, selected, i + 1);
        }
    }

    static void simulation(int[] selected) {
        // 0. 적 이동: 적 앞으로 한칸씩 이동을 n의 변화로 설정
        int cur_score = 0;
        for (int n = N - 1; n >= 0; n--) {

            // 1. 궁수 공격
            OUTER_LOOP: for (int col_curArcher : selected) {
                // 1-1 : 정면으로 단일탐색 1회 : 정면 성공 시 탈출
                Coord cur_searchCoord = new Coord(col_curArcher, n);
                if (board[cur_searchCoord.y][cur_searchCoord.x]) {
                    if (!visited[cur_searchCoord.y][cur_searchCoord.x]) {
                        cur_score++;
                        visited[cur_searchCoord.y][cur_searchCoord.x] = true;
                    }
                    continue;
                }
                // 정면 0면,
                // 1-2 : 기준 궁수에 대해 삼각 탐색 수행
                int step = 0;
                int mv_x = cur_searchCoord.x;
                int mv_y = cur_searchCoord.y;
                while (step + 1 < D) { // distance 검증
                    // 좌
                    mv_x -= 1 + (step * 2);
                    if (mv_x >= 0 && mv_x < M && mv_y >= 0 && mv_y <= n) {
                        if (board[mv_y][mv_x]) {
                            if (!visited[mv_y][mv_x]) {
                                cur_score++;
                                visited[mv_y][mv_x] = true;
                            }
                            step++;
                            continue OUTER_LOOP; // 적 찾으면 중복 발사든 아니든 기준궁수에 대한 탐색종료
                        }
                    }

                    // 우상
                    for (int i = 0; i < step + 1; i++) {
                        mv_x = mv_x + 1;
                        mv_y = mv_y - 1;
                        if (mv_x >= 0 && mv_x < M && mv_y >= 0 && mv_y <= n) {
                            if (board[mv_y][mv_x]) {
                                if (!visited[mv_y][mv_x]) {
                                    cur_score++;
                                    visited[mv_y][mv_x] = true;
                                }
                                step++;
                                continue OUTER_LOOP; // 적 찾으면 중복 발사든 아니든 기준궁수에 대한 탐색종료
                            }
                        }
                    }
                    // 우하
                    for (int i = 0; i < step + 1; i++) {
                        mv_x = mv_x + 1;
                        mv_y = mv_y + 1;
                        if (mv_x >= 0 && mv_x < M && mv_y >= 0 && mv_y <= n) {
                            if (board[mv_y][mv_x]) {
                                if (!visited[mv_y][mv_x]) {
                                    cur_score++;
                                    visited[mv_y][mv_x] = true;
                                }
                                step++;
                                continue OUTER_LOOP; // 적 찾으면 중복 발사든 아니든 기준궁수에 대한 탐색종료
                            }
                        }
                    }
                    step++;
                }

            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] && board[i][j]) {
                        board[i][j] = false;
                    }
                }
            }
        }
        maxScore = Math.max(maxScore, cur_score);
    }

    static class Coord {

        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}