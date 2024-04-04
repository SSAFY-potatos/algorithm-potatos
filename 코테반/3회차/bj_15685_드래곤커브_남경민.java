
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_15685_드래곤커브_남경민 {
    static int N;
    static Dragon[] dragons;
    static List<Integer> direction;
    static boolean[][] graph;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static class Dragon{
        int x,y,d,n;

        public Dragon(int x, int y, int d, int n) {
            super();
            this.x = x;
            this.y = y;
            this.d = d;
            this.n = n;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        dragons = new Dragon[N];
        graph = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            dragons[i] = new Dragon(x, y, d, n);
            dragonCurve(dragons[i]);
        }

        System.out.println(cntCurve());

    }
    static void dragonCurve(Dragon dragon) {
        direction = new ArrayList<>(); // 세대순으로 방향 담는 리스트
        graph[dragon.x][dragon.y] = true; //처음 좌표 방문 처리

        int endX = dragon.x;
        int endY = dragon.y;

        endX +=dx[dragon.d];
        endY +=dy[dragon.d];

        graph[endX][endY] = true;
        direction.add(dragon.d);

        for (int i = 0; i < dragon.n; i++) {
            for (int j = direction.size()-1; j >=0 ; j--) {
                int dr = rotation(direction.get(j)); //반시계방향 90도 회전

                endX +=dx[dr];
                endY +=dy[dr];

                graph[endX][endY] = true;
                direction.add(dr);
            }
        }
    }
    //반시계 방향 회전
    static int rotation(int d) {
        if(d==3) return 0;
        return d+1;
    }

    // 정사각형 개수 구하는 함수
    static int cntCurve() {
        int cnt =0;
        int[] dx = {1,0,1};
        int[] dy = {0,1,1};
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 1. 오른쪽(1,0)
                // 2. 아래쪽(0,1)
                // 3. 대각선 오른쪽 아래 (1,1)
                if(!graph[i][j]) continue;
                if(!graph[i+dx[0]][j+dy[0]] || !graph[i+dx[1]][j+dy[1]] || !graph[i+dx[2]][j+dy[2]]) continue;
                cnt++;
            }
        }
        return cnt;
    }
}
