import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] graph;
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                dfs(i, j, graph[i][j]);
                cnt++;
            }
        }

        System.out.printf(cnt + " ");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 'R') graph[i][j] = 'G';
            }
        }

        visited = new boolean[N][N];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                dfs(i, j, graph[i][j]);
                cnt++;
            }
        }

        System.out.print(cnt);
    }

    static void dfs(int y, int x, char color) {
        if (y < 0 || y >= N || x < 0 || x >= N) return;
        if (visited[y][x] || graph[y][x] != color) return;

        visited[y][x] = true;
        dfs(y + 1, x, color);
        dfs(y - 1, x, color);
        dfs(y, x + 1, color);
        dfs(y, x - 1, color);
    }
}