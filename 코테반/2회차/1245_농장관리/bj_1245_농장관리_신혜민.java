import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][];
    static int[] dx = {1,1,1,-1,-1,-1,0,0};
    static int[] dy = {0,1,-1,0,1,-1,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visit = new boolean[N][M]; // 방문처리
        boolean[][] t = new boolean[N][M]; // 산봉우리 체크
        int result = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0 && !visit[i][j]) {
                    boolean flag = true;
                    Queue<Top> q = new ArrayDeque<>();

                    q.offer(new Top(i, j));
                    visit[i][j] = true;

                    while(!q.isEmpty()) {
                        Top now = q.poll();

                        for(int k = 0; k < 8; k++) {
                            int nx = now.x + dx[k];
                            int ny = now.y + dy[k];

                            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                            if(map[nx][ny] > map[now.x][now.y]){
                                flag = false;
                            } 

                            if(map[nx][ny] == map[now.x][now.y]) {
                                if(!visit[nx][ny]){
                                    q.offer(new Top(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }

                    if(flag) {
                        result++;
                    }
                }
            }
        }
        System.out.println(result);
    }
}

class Top {
    int x, y;

    public Top(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
