import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_5427_불_조성우 {
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {-1, 0, 1, 0};
  static Queue<Point> q = new ArrayDeque<>();
  static Queue<Point> qFire = new ArrayDeque<>();
  static char[][] board;
  static boolean[][] visited;
  static int W, H;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    for (int tc = 1; tc < T + 1; tc++){
      q = new ArrayDeque<>();
      qFire = new ArrayDeque<>();
      //= start: 입력
      int result = -1;
      StringTokenizer st = new StringTokenizer(br.readLine());
      W = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());

      board = new char[H][W];
      visited = new boolean[H][W];
      for (int y = 0; y < H; y++){
        String str = br.readLine();
        for (int x = 0; x < W; x++){
          char curChar = str.charAt(x);
          if (curChar == '@'){
            q.offer( new Point(x, y, 0) );
            visited[y][x] = true;
            board[y][x] = '.';
            continue;
          }
          if (curChar == '*'){
            qFire.offer( new Point(x, y) );
          }
          board[y][x] = curChar;
        }
      }
      // end: 입력

      result = bfs();
      System.out.println( ( result == -1 ) ? "IMPOSSIBLE" : result );
    } // for : Test Case;

  }

  static int bfs(){
    int result = -1;

    OUTER_LOOP:
    while (!q.isEmpty()){
      int qSize = q.size();
      extendFire();

      for (int i = 0; i < qSize; i++){
        Point cur = q.poll();
        for (int dir = 0; dir < 4; dir++){
          int mvX = cur.x + dx[dir];
          int mvY = cur.y + dy[dir];
          int mvTime = cur.time + 1;

          //= start: 탈출
          if ( mvX < 0 || mvY < 0 || mvX >= W || mvY >= H ){
            result = mvTime;
            break OUTER_LOOP;
          } // end

          //= start: 검증
          if ( visited[mvY][mvX] || board[mvY][mvX] != '.'){
            continue;
          } // end: 검증

          visited[mvY][mvX] = true;
          q.offer( new Point(mvX, mvY, mvTime) );
        }

      } // while : q
    }

    return result;
  }

  static void extendFire(){
    int qSize = qFire.size();
    for (int i = 0; i < qSize; i++){
      Point cur =  qFire.poll();

      for (int dir = 0; dir < 4; dir++){
        int mvX = cur.x + dx[dir];
        int mvY = cur.y + dy[dir];

        if (mvX < 0 || mvX >= W || mvY < 0 || mvY >= H){
          continue;
        }
        if (board[mvY][mvX] != '.' || board[mvY][mvX] == '*'){
          continue;
        }

        board[mvY][mvX] = '*';
        qFire.offer( new Point(mvX, mvY) );
      } // for : dir
    } // end: qSize
  }

  static class Point{
    int x, y, time;

    public Point(int x, int y){
      this.x = x;
      this.y = y;
    }
    public Point(int x, int y, int time){
      this.x = x;
      this.y = y;
      this.time = time;
    }
  }
}