import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_11403_경로찾기_조성우 {

    public static boolean[][] graph;
    public static boolean[][] visited;
    public static int[][] resBoard;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N][N];

        resBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        for (int cur_node = 0; cur_node < N; cur_node++) {
            isStart = true;
            visited = new boolean[N][N];
            dfs(cur_node, cur_node);
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sb.append(resBoard[r][c]);
                sb.append(c == N - 1 ? "" : " ");
            }
            sb.append(r == N - 1 ? "" : "\n");
        }
        String str = sb.toString();
        System.out.print(str);
    }

    public static boolean isStart = true;

    public static void dfs(int search_node, int start_node) {
        // System.out.println("search_node, start_node : " + search_node + " " +
        // start_node);

        if (isStart) {
            isStart = !isStart;
        } else {
            if (start_node == search_node) {
                return;
            }
        }
        for (int i = 0; i < N; i++) {
            if (graph[search_node][i] && resBoard[start_node][i] != 1 && !visited[search_node][i]) {
                visited[search_node][i] = false;
                resBoard[start_node][i] = 1;
                // cnt++;
                // System.out.println(cnt);
                dfs(i, start_node);
            }
        }
    }

    public static int cnt = 0;
}