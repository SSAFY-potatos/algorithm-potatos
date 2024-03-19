import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[][] arr;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 1][N + 1];
        check = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u][v] = true;
            arr[v][u] = true;
        }

        int result = 0;

        for (int i = 1; i < N + 1; i++) {
            if (!check[i]) {
                dfs(i);
                result++;
            }
        }

        System.out.println(result);
    }

    static void dfs(int idx) {
        check[idx] = true;

        for (int i = 1; i < N + 1; i++) {
            if (arr[idx][i] && !check[i]) {
                dfs(i);
            }
        }
    }
}
