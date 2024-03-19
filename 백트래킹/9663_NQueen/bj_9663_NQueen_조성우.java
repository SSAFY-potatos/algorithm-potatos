import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_9663_NQueen_조성우 {

  // 보드 상 한 행에, 한 퀸만 착퀸할 수 있기 때문에, 착퀸지점 외 모든 col은 필요없다.
  // 따라서, 1차원 배열로 표현할 수 있다.
  // row를 인덱스로 잡고, 해당 row상 착퀸하는 column 지점을 value로 새긴다.
  static int[] boardByRow;
  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    boardByRow = new int[N];

    dfs(0);
    System.out.println(cnt);
  }

  static int cnt;

  static void dfs(int r) {

    if (r == N) {
      cnt++;
      return;
    }

    for (int c = 0; c < N; c++) {
      boardByRow[r] = c; // 행 배열 내 착퀸하는
      // valid branch가 아닌경우 다음 column
      if (!isValidBranch(r)) {
        continue;
      }
      dfs(r + 1);
    }

  }

  static boolean isValidBranch(int r) {

    // 지나온 행에 대해 검증 ( curChk_r <r )
    for (int curChk_r = 0; curChk_r < r; curChk_r++) {
      // 열 검증 (행은 한 row(즉, 한번의 dfs)에 한 퀸만 놓고 r+1하는 것으로 이미 유효함)
      // 현재 row상 착퀸할 column 위치가, 이전 행 중 착퀸했던 column과 중복인 경우
      // 열 중복으로 false
      if (boardByRow[r] == boardByRow[curChk_r]) {
        return false;
      }

      // 대각 검증
      // 주 대각선 검증
      if (r - curChk_r == boardByRow[r] - boardByRow[curChk_r]) {
        return false;
      }
      // 부 대각선 검증
      if (r - curChk_r == -(boardByRow[r] - boardByRow[curChk_r])) {
        return false;
      }
    }
    return true;
  }
}