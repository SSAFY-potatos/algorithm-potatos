import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static boolean[][] graph;
    static boolean[][] visited;
    static int answer;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new boolean[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            graph[y][x] = true;
        }

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || !graph[i][j]) continue;
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    static int dfs(int r, int c) {
        Stack<Node> stack = new Stack<>();
        stack.add(new Node(r, c));
        int count = 0;
        while (!stack.isEmpty()) {
            Node curr = stack.pop();

            if (visited[curr.y][curr.x]) continue;
            visited[curr.y][curr.x] = true;
            count++;

            for (int i = 0; i < 4; i++) {
                int ny = curr.y + dy[i];
                int nx = curr.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                if (!graph[ny][nx]) continue;

                stack.add(new Node(ny, nx));
            }
        }

        return count;
    }

    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}