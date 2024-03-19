import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static char[][] arr;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int check = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    dfs(i, j, arr[i][j]);
                    check++;
                }
            }
        }

        StringBuffer sb = new StringBuffer();

        sb.append(check + " ");

        visit = new boolean[N][N];
        check = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'R') {
                    arr[i][j] = 'G';
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    dfs(i, j, arr[i][j]);
                    check++;
                }
            }
        }

        sb.append(check);

        System.out.println(sb);
    }

    static void dfs(int x, int y, char C) {
        if (x < 0 || y < 0 || x >= N || y >= N || visit[x][y] || arr[x][y] != C) {
            return;
        }

        visit[x][y] = true;

        dfs(x + 1, y, C);
        dfs(x - 1, y, C);
        dfs(x, y + 1, C);
        dfs(x, y - 1, C);
    }
}
