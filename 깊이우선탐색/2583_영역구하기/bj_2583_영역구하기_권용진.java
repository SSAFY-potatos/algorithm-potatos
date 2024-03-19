import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static int N;
    static int K;
    static boolean[][] graph;
    static List<Integer> answers;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new boolean[M][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int r = y1; r < y2; r++) {
                for (int c = x1; c < x2; c++) {
                    graph[r][c] = true;
                }
            }
        }

        answers = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j]) continue;
                int cnt = dfs(i, j);
                answers.add(cnt);
            }
        }

        Collections.sort(answers);
        sb.append(answers.size()).append("\n");
        for (Integer answer : answers) {
            sb.append(answer).append(" ");
        }

        System.out.println(sb);
    }

    static int dfs(int r, int c) {
        Deque<Integer[]> stack = new ArrayDeque<>();
        stack.add(new Integer[] {r, c});
        int cnt = 0;
        while (!stack.isEmpty()) {
            Integer[] curr = stack.pollLast();
            int y = curr[0];
            int x = curr[1];

            if (graph[y][x]) continue;
            graph[y][x] = true;
            cnt++;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
                stack.add(new Integer[] {ny, nx});
            }
        }

        return cnt;
    }
}