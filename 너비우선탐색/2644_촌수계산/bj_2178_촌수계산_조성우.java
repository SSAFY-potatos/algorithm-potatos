import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2178_촌수계산_조성우 {

    static boolean[] visited;
    static int[][] graph;
    static Queue<Integer> q = new LinkedList<>();

    static int N;
    static int E;
    static int tar1;
    static int tar2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tar1 = Integer.parseInt(st.nextToken());
        tar2 = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            graph[node1][node2] = graph[node2][node1] = 1;
        }

        visited = new boolean[N + 1];
        System.out.println(bfs(tar1, tar2));

    }

    public static int bfs(int start, int target) {
        q.add(start);
        visited[start] = true;
        int[] dist = new int[N + 1];

        while (!q.isEmpty()) {
            int curNode = q.poll();

            if (curNode == target) {
                return dist[curNode];
            }
            for (int i = 1; i <= N; i++) { // i 는 타겟 노드 식별자
                if (graph[curNode][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                    dist[i] = dist[curNode] + 1;
                }
            }
        }
        return -1;
    }
}