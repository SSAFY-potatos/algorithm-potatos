import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1495_기타리스트_조성우 {

  static int N, S, M;
  static int[] volumes;
  static boolean[][] dp;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    volumes = new int[N];
    dp = new boolean[N + 1][M + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      volumes[i] = Integer.parseInt(st.nextToken());
    }

    dp[0][S] = true;

    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= M; j++) {
        if (dp[i - 1][j]) {
          if (j - volumes[i - 1] >= 0) {
            dp[i][j - volumes[i - 1]] = true;
          }
          if (j + volumes[i - 1] <= M) {
            dp[i][j + volumes[i - 1]] = true;
          }
        }
      }
    } //

    int result = -1;
    for (int i = M; i >= 0; i--) {
      if (dp[N][i]) {
        result = i;
        break;
      }
    }

    System.out.println(result);
  }
} 