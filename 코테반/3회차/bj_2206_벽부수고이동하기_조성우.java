import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2206_벽부수고이동하기_조성우 {

  static Queue<Point> q = new ArrayDeque<>();
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {-1, 0, 1, 0};
  static int[][] board;
  static boolean[][][] visited; // [ y ][ x ][ 0: 아직 힘안쓰고 방문했는가 T/F , 1: 힘을 이미 쓰고 방문했는가 T/F ]
  static int N, M;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    board = new int[N][M];
    visited = new boolean[N][M][2];

    //= start: 입력
    for (int y = 0; y < N; y++) {
        String row = br.readLine();
        for (int x = 0; x < M; x++) {
            board[y][x] = Integer.parseInt(String.valueOf(row.charAt(x)));
        }
    }
    // end : 입력


    //= start: BFS
    q.offer(new Point(0, 0, 1, false));
    int res = bfs(q);
    // end

    System.out.println(res);
  }

  
  static int bfs( Queue<Point> q ) {
    int result = (N == 1 && M == 1) ? 1 : -1; // 시작점, 끝점이 처음부터 같다면 결과는 1

    OUTER_LOOP:
    while (!q.isEmpty()){
      Point cur = q.poll();

      for (int dir = 0; dir < 4; dir++){
        int mvX = cur.x + dx[dir];
        int mvY = cur.y + dy[dir];
        int mvTime = cur.time + 1;
        boolean mvUsedPower = cur.usedPower; // 벽 부쉈는지(힘을 소진했는지) 여부

        if (!isOnBoard(mvX, mvY)){
          continue;
        }

        //= start: 방문검증 
        //   힘을 소진한 채로 여기에 왔을 때, 있는채로 왔을 때를 다르게 방문검증
        if ( visited[mvY][mvX][ mvUsedPower ? 1 : 0 ] ) {
          continue;
        }
        
        if (board[mvY][mvX] == 1){  // 움직이려는 좌표가 벽일때
          if (mvUsedPower){             // 힘을 이미 소진했다면
            continue;                       // 스킵
          }
                                        // 아니라면
          mvUsedPower = true;               // 벽을 부수고 이동
        }
        // end : 방문검증

        
        //= 탈출
        if ( mvX == M-1 && mvY == N-1 ) {
          result = mvTime;
          break OUTER_LOOP;
        }
        //


        visited[mvY][mvX][ mvUsedPower ? 1 : 0 ] = true;
        q.offer( new Point(mvX, mvY, mvTime, mvUsedPower) );
      }
    } // end: while 

    return result;
  }

  static boolean isOnBoard(int x, int y) {
    if (x < 0 || y < 0 || x >= M || y >= N){
      return false;
    }
    return true;
  }

  static class Point {
    int x, y;
    int time;
    boolean usedPower;

    public Point (int x, int y, int time, boolean usedPower){
      this.x = x;
      this.y = y;
      this.time = time;
      this.usedPower = usedPower;
    }
  }

}
