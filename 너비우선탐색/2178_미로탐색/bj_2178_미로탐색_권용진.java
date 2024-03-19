import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] graph;
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

        graph = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = s.charAt(j) - '0';
            }
        }

        answer = bfs();
        System.out.println(answer);
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        visited[0][0] = true;
        int count = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int c = 0; c < qSize; c++) {
                Node curr = q.poll();

                if (curr.y == N - 1 && curr.x == M - 1) {
                    return ++count;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = curr.y + dy[i];
                    int nx = curr.x + dx[i];

                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (visited[ny][nx] || graph[ny][nx] == 0) continue;
                    q.add(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }

            count++;
        }

        return 0;
    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}