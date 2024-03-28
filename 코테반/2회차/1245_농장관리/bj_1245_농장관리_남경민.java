
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class bj_1245_농장관리_남경민 {
    static int N,M;
    static int[] dx = {1,-1,0,0,1,1,-1,-1};
    static int[] dy = {0,0,1,-1,1,-1,1,-1};
    static int[][] map;
    static boolean flag;
    static int[][] visited;
    static class Node{
        int x,y;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        // 1: 방문 한것 ,  0: 방문X , 2: 이미 다른 봉우리 판단 시, 해당 봉우리보다 낮아서 가지치기 한 곳
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] ==0 || visited[i][j] > 0) continue;
                flag = true;
                BFS(new Node(i,j));
                if(flag) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);


    }
    static void BFS(Node n) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(n);
        visited[n.x][n.y] = 1;

        while(!queue.isEmpty()) {
            Node v = queue.poll();

            // 인접 영역 탐색
            for (int i = 0; i < 8; i++) {
                int xi = dx[i] + v.x;
                int yi = dy[i] + v.y;
                if(xi<0 || xi>=N || yi <0 || yi>=M) continue;

                //자기보다 큰 경우
                if(map[xi][yi] > map[v.x][v.y]) {
                    flag = false;
                    continue;
                }

                // 자기보다 작은 경우
                // => 다른 곳을 탐색할 때 이미 봉우리가 아닌 곳 가지치기 하기 위해 visited = 2
                else if(map[xi][yi] < map[v.x][v.y] ) {
                    visited[xi][yi] =2;
                    continue;
                }

                // 자기랑 같은 경우
                else if(map[xi][yi] == map[v.x][v.y]) {
                    if(visited[xi][yi] > 1) flag = false; // 봉우리가 아니라고 정해진 곳
                    else if(visited[xi][yi] == 1) continue; //방문은 했지만 아직 봉우리 아니라고 판단하지 않은 곳
                    else { // 방문하지 않은 곳
                        queue.add(new Node(xi,yi));
                        visited[xi][yi] = 1;
                    }
                }
            }
        }
    }
}
