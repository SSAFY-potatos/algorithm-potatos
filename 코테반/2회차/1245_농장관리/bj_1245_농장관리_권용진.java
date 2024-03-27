import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int cnt;

    static int[][] map;
    static boolean[][] visited;

    static int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[2], o1[2]);
            }
        });

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                pq.add(new int[] {i, j, map[i][j]});
            }
        }

        visited = new boolean[N][M];
        int answer = 0;

        while (cnt < N * M) {
            int[] curr = pq.poll();

            if (visited[curr[0]][curr[1]]) continue;
            ++answer;
            bfs(curr[0], curr[1]);
        }
        System.out.println(answer);
    }

    static void bfs(int r, int c) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            ++cnt;

            for (int i = 0; i < 8; i++) {
                int ny = curr[0] + dy[i];
                int nx = curr[1] + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (visited[ny][nx]) continue;
                if (map[curr[0]][curr[1]] < map[ny][nx]) continue;

                q.add(new int[] {ny, nx});
                visited[ny][nx] = true;
            }
        }
    }
}