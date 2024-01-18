import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1018_체스판다시칠하기_조성우 {
  // correctBoard, 비교 부분 리팩토링 예정
  public static void main(String [] args) throws IOException {
    // 비교할 원본 체스판 2타입 초기화
    int[][] correctBoard_B = {{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'}};
    int[][] correctBoard_W = {{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'}};

    // 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] InputBoard = new int[N][M];
    for (int i = 0; i < N; i++){
      String line = br.readLine();
      for (int j = 0; j < M; j++){
        InputBoard[i][j] = line.charAt(j);
      }
    }

    // CorrectBoard와 비교하면서 슬라이딩

    int minDiffCnt = Integer.MAX_VALUE;
    // 전체 판에 대해, 88체스보드가 각 방향으로 슬라이딩할수 있는 횟수만큼 반복
    for (int n = 0; n < N - correctBoard_B.length + 1; n++) {
      int tempDiffCnt_B = 0;
      int tempDiffCnt_W = 0;
      for (int i = n; i < n + 8; i++) {
        for (int j = n; j < n + 8; j++) {
          if (InputBoard[i][j] == correctBoard_W[i - n][j - n]) {
            tempDiffCnt_W++;
          }
          if (InputBoard[i][j] == correctBoard_B[i - n][j - n]) {
            tempDiffCnt_B++;
          }
        }
      }
      minDiffCnt = Math.min(minDiffCnt, Math.min(tempDiffCnt_W, tempDiffCnt_B));
    }
    System.out.println(minDiffCnt);
  }
}