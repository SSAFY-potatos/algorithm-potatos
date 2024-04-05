import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11660_구간합구하기5_조성우 {
  static int[][] cumSumBoard;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    cumSumBoard = new int[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= N; j++) {
        int value = Integer.parseInt(st.nextToken());
        cumSumBoard[i][j] = cumSumBoard[i - 1][j] + cumSumBoard[i][j - 1] - cumSumBoard[i - 1][j - 1] + value;
      }
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());

      int result = cumSumBoard[x2][y2] - cumSumBoard[x1 - 1][y2] - cumSumBoard[x2][y1 - 1] + cumSumBoard[x1 - 1][y1 - 1];
      System.out.println(result);
    }
  }
}