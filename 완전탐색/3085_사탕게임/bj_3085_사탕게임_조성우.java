import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_3085_사탕게임_조성우 {
    /*
     * 아이디어 :
     * - 방향 (T,L,R,B) 중 (R,B)만 수행
     * -(틀린 아이디어) 교환은 curPos, curPos + 1 상호 교환이므로 짝수 index마다 수행
     * ->(수정) R,B로만 향하니 매 인덱스에 수행해야 함
     * 1. 교환 좌표 보드 유효성 검증 (방향 별 초과체크)
     * 2. 교환 item간 서로 다름 검증
     * 3. 매 교환 후, 전체 행,열 합에 대해 max 갱신
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];

        int[] dx = { 1, 0 }; // RB
        int[] dy = { 0, 1 }; // RB
        int max = Integer.MIN_VALUE;

        // 입력
        for (int i = 0; i < N; i++) {
            String strLine = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = strLine.charAt(j);
            }
        }
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {

                for (int pos = 0; pos < 2; pos++) {
                    int mv_j = j + dx[pos];
                    int mv_i = i + dy[pos];

                    if (mv_j >= N || mv_i >= N) {
                        continue;
                    }
                    if (board[i][j] == board[mv_i][mv_j]) {
                        continue;
                    }
                    // 교환
                    char temp;
                    temp = board[i][j];
                    board[i][j] = board[mv_i][mv_j];
                    board[mv_i][mv_j] = temp;

                    // 최대 사탕열 계산, 갱신
                    // 행
                    for (int k = 0; k < board.length; k++) {
                        int count = 1;
                        for (int l = 1; l < board[k].length; l++) {
                            if (board[k][l] == board[k][l - 1]) {
                                count++;
                                max = Math.max(max, count);
                            } else {
                                count = 1;
                            }
                        }
                    }

                    // 열
                    for (int l = 0; l < board[0].length; l++) {
                        int count = 1;
                        for (int k = 1; k < board.length; k++) {
                            if (board[k][l] == board[k - 1][l]) {
                                count++;
                                max = Math.max(max, count);
                            } else {
                                count = 1;
                            }
                        }
                    }
                    // 교환복구
                    board[mv_i][mv_j] = board[i][j];
                    board[i][j] = temp;
                }

            }
        }

        System.out.println(max);
    }
}