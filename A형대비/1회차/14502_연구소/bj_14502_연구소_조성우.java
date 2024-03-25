import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_14502_연구소_조성우 {
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {-1, 0, 1, 0};
  static Queue<Coord> q = new LinkedList<>();
  static int[][] board;
  static List<Coord> virusCoords = new ArrayList<>();
  static List<Coord> emptyCoords = new ArrayList<>();
  static int N, M;
  static int INITIAL_EMPTY_CNT;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        int curItem = Integer.parseInt(st.nextToken());

        if (curItem == 0) {
          emptyCoords.add(new Coord(j, i));
        } else if (curItem == 2) {
          virusCoords.add(new Coord(j, i));
        }
        board[i][j] = curItem;
      }
    }
    INITIAL_EMPTY_CNT = emptyCoords.size();

    // 빈공간에 벽 3개 배치 bruteForce by 조합
    boolean[] comb_visited = new boolean[INITIAL_EMPTY_CNT];
    combination(emptyCoords, comb_visited, 0, INITIAL_EMPTY_CNT, 3);
    System.out.print((INITIAL_EMPTY_CNT - minInfected - 3)); // 배치한 벽 세개만큼 차감
  }

  static class Coord {
    int x, y;

    public Coord(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int cnt = 0;

  static void combination(List<Coord> arr, boolean[] visited, int start, int n, int r) {
    if (r == 0) {
      simulation(arr, visited, n);
      return;
    }

    for (int i = start; i < n; i++) {
      visited[i] = true;
      cnt++;
      combination(arr, visited, i + 1, n, r - 1);
      visited[i] = false;
    }
  }

  static void simulation(List<Coord> arr, boolean[] visited, int n) {
    // 1. Combination된 3개 벽 배치
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        board[arr.get(i).y][arr.get(i).x] = 1;
      }
    }

    /* 2. bfs로 바이러스 전파 로직 */

    // 큐에 바이러스 원점들 추가
    for (Coord virusCoord : virusCoords) {
      q.offer(virusCoord);
    }

    //바이러스 전파

    List<Coord> infectedCoords = new ArrayList<>();
    int cntInfected = 0;
    while (!q.isEmpty()) {
      Coord cur_coord = q.poll();
      int cur_x = cur_coord.x;
      int cur_y = cur_coord.y;

      int mv_x = 0, mv_y = 0;
      for (int idxDir = 0; idxDir < 4; idxDir++) {
        mv_x = cur_x + dx[idxDir];
        mv_y = cur_y + dy[idxDir];

        if (mv_x < 0 || mv_x >= M || mv_y < 0 || mv_y >= N) {
          continue;
        }
        if (board[mv_y][mv_x] != 0) {
          continue;
        }
        board[mv_y][mv_x] = 2;
        Coord mv_coord = new Coord(mv_x, mv_y);

        infectedCoords.add(mv_coord); // 감염 원상복구용 기록
        q.offer(mv_coord);
        cntInfected++;
      }
    }

    minInfected = Math.min(minInfected, cntInfected);
    // board를 감염 전 원본상태로 복구
    for (Coord infectedCoord : infectedCoords) {
      board[infectedCoord.y][infectedCoord.x] = 0;
    }

    // 벽 배치 취소
    for (int i = 0; i < n; i++) {
      if (visited[i]) {
        board[arr.get(i).y][arr.get(i).x] = 0;
      }
    }
    // 고민해 볼 것 : 감염 원상복구 & 벽배치 취소보다 원본 board 카피해놓은 spare 보드를 카피해 board를 복구하는게 더 빠르려나?
  }

  static int minInfected = Integer.MAX_VALUE;
}