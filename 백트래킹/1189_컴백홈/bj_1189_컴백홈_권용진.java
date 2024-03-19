import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static int K;
    static char[][] graph;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = s.charAt(j);
            }
        }

        visited[R - 1][0] = true;
        sol(1, R - 1, 0);
        System.out.println(answer);
    }

    static void sol(int depth, int y, int x) {
        if (depth == K) {
            if (y == 0 && x == C - 1) answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
            if (visited[ny][nx] || graph[ny][nx] == 'T') continue;

            visited[ny][nx] = true;
            sol(depth + 1, ny, nx);
            visited[ny][nx] = false;
        }
    }
}