import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class bj_10026_적록색약_조성우 {

    public static int[] dx = { 1, 0, -1, 0 };
    public static int[] dy = { 0, 1, 0, -1 };
    public static HashMap<Character, Integer> rgbMap = new HashMap<Character, Integer>();
    public static HashMap<Character, Integer> rbMap = new HashMap<Character, Integer>();
    public static char[][] board;
    public static char[][] board_rb;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        board_rb = new char[N][N];
        for (int i = 0; i < N; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = inputLine.charAt(j);
                board[i][j] = c;

                if (c == 'G') {
                    board_rb[i][j] = 'R';
                    continue;
                }
                board_rb[i][j] = c;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 'X') {
                    rgbMap.put(board[i][j], rgbMap.getOrDefault(board[i][j], 0) + 1);
                    dfs(board[i][j], j, i, board);
                }
                if (board_rb[i][j] != 'X') {
                    rbMap.put(board_rb[i][j], rbMap.getOrDefault(board_rb[i][j], 0) + 1);
                    dfs(board_rb[i][j], j, i, board_rb);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = rgbMap.values().stream().mapToInt(Integer::intValue).sum();

        sb.append(sum);
        sb.append(" ");
        sum = rbMap.values().stream().mapToInt(Integer::intValue).sum();
        sb.append(sum);
        System.out.println(sb);
    }

    public static int maxDep = 0;

    public static void dfs(char cur_color, int cur_x, int cur_y, char[][] board) {
        int mv_x, mv_y;
        for (int idxDir = 0; idxDir < 4; idxDir++) {
            mv_x = cur_x + dx[idxDir];
            mv_y = cur_y + dy[idxDir];

            if (mv_x < 0 || mv_x >= N || mv_y < 0 || mv_y >= N) {
                continue;
            }
            if (board[mv_y][mv_x] != cur_color) {
                continue;
            }
            board[mv_y][mv_x] = 'X';
            dfs(cur_color, mv_x, mv_y, board);
        }
    }
}