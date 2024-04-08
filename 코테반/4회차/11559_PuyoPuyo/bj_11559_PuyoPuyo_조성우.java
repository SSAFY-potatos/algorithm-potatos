/*
< 내 풀이전략 >

 1. 바닥(Y-1)부터 돌면서 방문안한 뿌요(startPuyo) 를 찾는다. 
 2. bfs( startPuyo )로 4개이상으로 이뤄진 뿌요그룹을 찾아 반환받고, MatchedPuyos에 추가(addAll)한다.
 3. 1~2의 반복 끝에 찾아진, 즉 한 스텝에서 발생한 모든 유효한 뿌요그룹 내 뿌요들(MatchedPuyos)를 제거하고, 연쇄(result)++
    -> 3-ESCAPE) 만일 MatchedPuyos.size()가 0인 경우, 즉 유효한 뿌요가 없는 경우 즉시 종료하고 결과를 출력한다.
 4. applyGravity()로 중력을 적용한다.
 5. 3-ESCAPE로 탈출할 때 까지 반복한다.

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class bj_11559_PuyoPuyo_조성우 {

  static int result = 0;

  static int Y = 12, X = 6;
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {-1, 0, 1, 0};

  static char[][] board = new char[Y][X];
  static boolean[][] visited = new boolean[Y][X];

  static List<Point> MatchedPuyos = new ArrayList<>();

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //= start: 입력
    for (int y = 0; y < Y; y++) {
      String str = br.readLine();
      for (int x = 0; x < X; x++) {
        board[y][x] = str.charAt(x);
      }
    }
    // end : 입력

    //= start: 메인로직
    while (true) {
      for (int y = Y - 1; y >= 0; y--) {
        boolean anyPuyoInThisRow = false;   // 한 행 내 아무런 뿌요도 없다면(false) 그 위는 볼필요도 없음

        for (int x = 0; x < X; x++) {
          //= start: if - 방문 안한 뿌요가 있다면 bfs() 고고
          if (board[y][x] != '.') {
              anyPuyoInThisRow = true;
              if (!visited[y][x]) {
                  Point startPuyo = new Point(x, y);
                  List<Point> bfsResult = bfs(startPuyo); // bfs()

                  MatchedPuyos.addAll( // bfs결과가 4개 이상의 뿌요그룹인 경우 제거대상 추가
                          (bfsResult != null) ? bfsResult : new ArrayList<>());
              }
          } // end: if
        } // end : for - x
        
        if (!anyPuyoInThisRow) { 
          break;
        }
      } // end: for - y
      
      if (MatchedPuyos.size() == 0) {   // 종료조건: 매치된 뿌요그룹이 하나도 없다면 종료
          break;
      } 

      //= start: 한스텝 내 매치된 모든 뿌요그룹 제거 & 재초기화
      for (Point matchedPuyo : MatchedPuyos) {
        board[matchedPuyo.y][matchedPuyo.x] = '.';
      }
      MatchedPuyos.clear();
      visited = new boolean[Y][X];
      result++;
      // end: 뿌요그룹 제거

      applyGravity(); // 중력처리
    } // end: while
    // end : 메인로직

    System.out.println(result);
  }

  // BFS 메서드(startPuyo가 속한 뿌요그룹 탐색)
  static List<Point> bfs(Point startPuyo) {
    char color = board[startPuyo.y][startPuyo.x];
    List<Point> groupedPuyos = new ArrayList<>();

    Queue<Point> q = new ArrayDeque<>();
    q.offer(startPuyo);
    groupedPuyos.add(startPuyo);
    visited[startPuyo.y][startPuyo.x] = true;

    // start: while
    while (!q.isEmpty()) {
      Point cur = q.poll();

      for (int dir = 0; dir < 4; dir++) {
        int mvX = cur.x + dx[dir];
        int mvY = cur.y + dy[dir];

        if (mvX < 0 || mvY < 0 || mvX >= X || mvY >= Y || visited[mvY][mvX]) {
          continue;
        }
        if (board[mvY][mvX] != color) {
          continue;
        }
        Point newPuyo = new Point(mvX, mvY);

        groupedPuyos.add(newPuyo);
        q.offer(newPuyo);
        visited[mvY][mvX] = true;
      }
    }
    // end: while

    //= bfs() 결과 반환
    if (groupedPuyos.size() < 4) {
      return null;
    }
    return groupedPuyos;
  }

  //=METHOD: 중력처리
  static void applyGravity() {
    for (int x = 0; x < X; x++) {
      for (int y = Y - 2; y >= 0; y--) { // 바닥에서 두 번째 줄부터 시작
        if (board[y][x] != '.') {
          int fallToY = y;
          while (fallToY + 1 < Y && board[fallToY + 1][x] == '.') {
            fallToY++; // 빈 공간을 찾아서 한 칸씩 아래로
          }
          if (fallToY != y) {
            board[fallToY][x] = board[y][x]; // 뿌요를 아래로 이동
            board[y][x] = '.'; // 이동한 자리는 빈 공간으로 설정
          }
        }
      }
    }
  }

  static class Point {

    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
