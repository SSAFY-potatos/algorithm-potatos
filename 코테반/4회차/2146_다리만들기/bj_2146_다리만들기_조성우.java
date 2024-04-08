import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2146_다리만들기_조성우 {

  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {-1, 0, 1, 0};
  public static void main(String[] args) throws IOException {
    int resultMinBridge = Integer.MAX_VALUE;

    //= start : 입력
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    List<List<Point>> islandList = new ArrayList<>();
    boolean[][] board = new boolean[N][N];

    for (int y = 0; y < N; y++){
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int x = 0; x < N; x++){
        board[y][x] = (Integer.parseInt(st.nextToken()) == 1) ? true : false;
      }
    }
    // end : 입력

    //= start: BFS() 섬 그룹 모두 찾기
    boolean[][] visited = new boolean[N][N];
    for (int y = 0; y < N; y++){
      for (int x = 0; x < N; x++){
        if (visited[y][x] || !board[y][x] ){
          continue;
        }

        Point start = new Point(x, y);

        Queue<Point> q = new ArrayDeque<>();
        List<Point> startIsland = new ArrayList<>();
        q.offer(start);
        startIsland.add(start);

        visited[start.y][start.x] = true;

        List<Point> island = bfs( q, startIsland, board, visited );
        islandList.add(island);
      }
    }

    //= start: 두 섬 사이 거리 비교 ( 모든 섬들에 대해, 최외곽 간 비교 )
    for (int i = 0; i < islandList.size(); i++){
      for (int j = i + 1; j < islandList.size(); j++){
        List<Point> islandA = islandList.get(i);
        List<Point> islandB = islandList.get(j);
        resultMinBridge = Math.min(resultMinBridge, compareIslandBorders(islandA, islandB));
      }
    }

    // end
    System.out.println(resultMinBridge);
  }

  static int compareIslandBorders(List<Point> A, List<Point> B){
    int minBridgeLen = Integer.MAX_VALUE;
    for (Point a : A){
      if (!a.isBorder){
        continue;
      }
      for (Point b : B){
        if (!b.isBorder){
          continue;
        }
        minBridgeLen = Math.min(minBridgeLen, calcBridgeLength(a, b));
      }
    }
    return minBridgeLen;
  }

  static int calcBridgeLength(Point A, Point B){
    return Math.abs(A.x  - B.x) + Math.abs(A.y - B.y) - 1;
  }
  static List<Point> bfs ( Queue<Point> q, List<Point> island, boolean[][] board, boolean[][] visited ){
    while(!q.isEmpty()){
      Point cur = q.poll();

      boolean[][] addedBorder = new boolean[board.length][board.length];
      for (int dir = 0; dir < 4; dir++){
        int mvX = cur.x + dx[dir];
        int mvY = cur.y + dy[dir];

        if ( mvY < 0 || mvX < 0 || mvX >= board.length || mvY >= board.length ){
          continue;
        }
        if (visited[mvY][mvX]){
          continue;
        }
        if ( !board[mvY][mvX] ){ // mv가 바다라는건 cur이 바다랑 인접했다는 뜻이므로, cur의 isBorder를 true로 바꿔줌
          if (addedBorder[cur.y][cur.x]){
            continue;
          }
          addedBorder[cur.y][cur.x] = true;
          island.get(island.indexOf(cur)).isBorder = true;
          continue;
        }

        visited[mvY][mvX] = true;
        Point newPoint = new Point(mvX, mvY);
        q.offer(newPoint);
        island.add(newPoint);
      }
    }
    return island;
  }
  static class Point{
    int x, y;
    boolean isBorder;

    public Point(int x, int y){
      this.x = x;
      this.y = y;
    }
    public Point(int x, int y, boolean isBorder){
      this.x = x;
      this.y = y;
      this.isBorder = isBorder;
    }
  }
}
