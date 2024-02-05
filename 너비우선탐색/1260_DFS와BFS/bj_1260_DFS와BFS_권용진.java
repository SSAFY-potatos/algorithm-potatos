import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int V;
    static boolean[][] graph;
    static List<Integer> bfsAnswer = new ArrayList<>();
    static List<Integer> dfsAnswer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u][v] = true;
            graph[v][u] = true;
        }

        dfs();
        for (int i = 0; i < dfsAnswer.size(); i++) {
            System.out.print(dfsAnswer.get(i) + " ");
        }
        System.out.println();

        bfs();
        for (int i = 0; i < bfsAnswer.size(); i++) {
            System.out.print(bfsAnswer.get(i) + " ");
        }
    }

    static void dfs() {
        boolean[] visited = new boolean[N + 1];
        Stack<Integer> stack = new Stack<>();
        stack.add(V);
        while (!stack.isEmpty()) {
            int curr = stack.pop();

            if (visited[curr]) continue;
            visited[curr] = true;
            dfsAnswer.add(curr);

            for (int next = N; next > 0; next--) {
                if (!graph[curr][next]) continue;
                stack.add(next);
            }
        }
    }

    static void bfs() {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(V);
        visited[V] = true;
        bfsAnswer.add(V);
        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next = 1; next < N + 1; next++) {
                if (!graph[curr][next] || visited[next]) continue;
                bfsAnswer.add(next);
                q.add(next);
                visited[next] = true;
            }
        }
    }
}