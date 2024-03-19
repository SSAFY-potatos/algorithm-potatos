import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static boolean[][] arr;
    static boolean[][] visit;
    static int check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[r][c] = true;
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                check = 0;
                dfs(i, j);
                max = Math.max(max, check);
            }
        }

        System.out.println(max);
    }

    static void dfs(int x, int y) {
        if (x < 1 || y < 1 || x > N || y > M || !arr[x][y] || visit[x][y]) {
            return;
        }

        visit[x][y] = true;
        check++;

        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}
