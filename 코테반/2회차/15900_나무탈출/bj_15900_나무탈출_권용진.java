import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int cnt;

    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[N + 1];
        dfs(1, 0);
        if (cnt % 2 == 1) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    static void dfs(int node, int depth) {
        visited[node] = true;
        boolean flag = true;
        for (int nextNode : graph.get(node)) {
            if (!visited[nextNode]) {
                dfs(nextNode, depth + 1);
                flag = false;
            }
        }

        if (flag) {
            cnt += depth;
        }
    }
}