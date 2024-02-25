import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N, cnt, min;
    static Shark s;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    s = new Shark(i, j);
                }
            }
        }

        while (true) {
            boolean flag = search();
            if (!flag) {
                break;
            }
        }
        System.out.println(s.time);
    }

    static boolean search() {
        min = Integer.MAX_VALUE;
        int x = -1, y = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] < s.size && map[i][j] != 0) {
                    if (check(i, j) && min > cnt) {
                        min = cnt;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        if (x != -1) {
            map[x][y] = 9;
            map[s.x][s.y] = 0;
            s.time += min;
            s.cnt++;
            s.x = x;
            s.y = y;

            if (s.cnt == s.size) {
                s.size++;
                s.cnt = 0;
            }
            return true;
        } else {
            return false;
        }
    }

    static boolean check(int x, int y) { // 먹을 수 있는지
        Queue<S> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        cnt = 0;

        q.offer(new S(s.x, s.y, 0));
        visit[s.x][s.y] = true;

        while (!q.isEmpty()) {
            S now = q.poll();

            if (now.x == x && now.y == y) {
                cnt = now.cnt;
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if (map[nx][ny] > s.size || visit[nx][ny] || min <= now.cnt)
                    continue;
                visit[nx][ny] = true;
                q.offer(new S(nx, ny, now.cnt + 1));
            }
        }
        return false;
    }
}

class Shark {
    int x, y, size = 2, cnt, time;

    Shark(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class S {
    int x, y, cnt;

    S(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}