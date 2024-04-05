import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, arr[][];
    static boolean[][][] visit;

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visit = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Wall> q = new ArrayDeque<>();

        q.offer(new Wall(0, 0, 1, 1));
        visit[1][0][0] = true;

        while (!q.isEmpty()) {
            Wall now = q.poll();

            if (now.x == N - 1 && now.y == M - 1) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if (arr[nx][ny] == 0) { // 벽이 아닐 때
                    if (!visit[now.check][nx][ny]) {
                        visit[now.check][nx][ny] = true;
                        q.offer(new Wall(nx, ny, now.cnt + 1, now.check));
                    }
                } else { // 벽일 때
                    if (now.check != 0 && !visit[0][nx][ny]) { // 벽 부순 적 없음
                        q.offer(new Wall(nx, ny, now.cnt + 1, 0)); // 벽 부수기
                        visit[0][nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}

class Wall {
    int x, y, cnt, check;

    Wall(int x, int y, int cnt, int check) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.check = check;
    }
}