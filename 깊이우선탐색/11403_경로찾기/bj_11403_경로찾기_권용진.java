import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();


        N = Integer.parseInt(br.readLine());
        graph = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i);
            for (int j = 0; j < N; j++) {
                if (visited[j]) sb.append(1).append(" ");
                else sb.append(0).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int v) {
        for (int i = 0; i < N; i++) {
            if (graph[v][i] && !visited[i]) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
}