import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] visit;
    static int check = 0;

    static int dx[] = { 1, -1, 1, -1, 0, 0, 1, -1 };
    static int dy[] = { 1, 1, -1, -1, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new boolean[N][N];

        dfs(0, 0, 0);

        System.out.println(check);

    }

    static void dfs(int x, int y, int cnt) {
        if (cnt == N) {
            check++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (check(i, y)) {
                visit[i][y] = true;
                dfs(i, y + 1, cnt + 1);
                visit[i][y] = false;
            }
        }
    }

    static boolean check(int x, int y) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            while (nx < N && nx >= 0 && ny < N && ny >= 0) {
                if (visit[nx][ny] == true) {
                    return false;
                }
                nx += dx[i];
                ny += dy[i];
            }
        }
        return true;
    }
}