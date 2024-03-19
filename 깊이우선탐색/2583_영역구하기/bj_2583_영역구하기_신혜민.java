import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static int[][] arr;
    static boolean[][] visit;
    static int check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());

            for (int x = lx; x < rx; x++) {
                for (int y = ly; y < ry; y++) {
                    arr[y][x] = 1;
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                check = 0;
                dfs(i, j);
                if (check != 0) {
                    list.add(check);
                }
            }
        }

        list.sort(null);

        sb.append(list.size() + "\n");

        for (int t : list) {
            sb.append(t + " ");
        }

        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M || visit[x][y] || arr[x][y] == 1) {
            return;
        }

        visit[x][y] = true;
        check++;

        dfs(x + 1, y);
        dfs(x, y + 1);
        dfs(x - 1, y);
        dfs(x, y - 1);
    }
}
