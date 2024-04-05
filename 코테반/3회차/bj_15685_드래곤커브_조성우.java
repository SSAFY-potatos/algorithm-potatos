import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj_15685_드래곤커브_조성우 {

  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, -1, 0, 1};
  static boolean[][] board = new boolean[101][101];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    for (int n = 0; n < N; n++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      simulateCurve(x, y, d, g);
    }

    System.out.println(countSquares());
  }

  static void simulateCurve(int x, int y, int d, int g) {
    List<Integer> dirList = new ArrayList<>();
    dirList.add(d);
    board[y][x] = true;

    x += dx[d];
    y += dy[d];
    board[y][x] = true;

    for (int i = 0; i < g; i++) {
      int size = dirList.size();
      for (int j = size - 1; j >= 0; j--) {
        d = (dirList.get(j) + 1) % 4;
        x += dx[d];
        y += dy[d];
        board[y][x] = true;
        dirList.add(d);
      }
    }
  }

  static int countSquares() {
    int cnt = 0;
    for (int i = 0; i < 100; i++) {
      for (int j = 0; j < 100; j++) {
        if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1]) {
          cnt++;
        }
      }
    }
    return cnt;
  }
}