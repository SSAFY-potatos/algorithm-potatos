import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2206_벽부수고이동하기_남경민{
    static int N,M;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int ans=-1;
    static class Node{
        int x,y,d;
        boolean isBlock;
        public Node(int x, int y, int d, boolean isBlock) {
            super();
            this.x = x;
            this.y = y;
            this.d = d;
            this.isBlock = isBlock;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }

        BFS(new Node(0,0,1,false));
        System.out.println(ans);
    }
    static void BFS(Node n) {
        Queue<Node> queue = new ArrayDeque<>();
        // 방문처리 : (1인경우 벽을 부수던지 or 안부수기 둘다 저장하기)
        // 0 : 벽을 안부수는 경우의 수
        // 1 : 벽을 부수는 경우의 수
        boolean[][][] visited = new boolean[N][M][2];
        queue.add(n);
        visited[n.x][n.y][0] = true;

        while(!queue.isEmpty()) {
            Node v = queue.poll();
            if(v.x == N-1 && v.y ==M-1) {
                ans = v.d;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int xi = v.x + dx[i];
                int yi = v.y + dy[i];
                if(xi <0 || xi >=N || yi <0 || yi >=M) continue;

                // 벽인 경우
                if(map[xi][yi] ==1) {
                    // 벽을 부순적이 없고 지나간 적이 없는경우
                    if(!visited[xi][yi][0] && !v.isBlock) {
                        visited[xi][yi][0] = true;
                        queue.add(new Node(xi,yi,v.d+1,true));
                    }

                }else { //벽이 아닌 경우
                    //벽을 부순 적이 없고 지나간 적이 없는 경우
                    if(!visited[xi][yi][0] && !v.isBlock) {
                        visited[xi][yi][0] = true;
                        queue.add(new Node(xi,yi,v.d+1,false));
                    }

                    //벽을 부순 적이 있고 지나간 적이 없는 경우
                    if(!visited[xi][yi][1] && v.isBlock) {
                        visited[xi][yi][1] = true;
                        queue.add(new Node(xi,yi,v.d+1,true));
                    }
                }
            }
        }
    }
}
