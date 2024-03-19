import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;
    static int[][] maze;
    static int maxGarbage = 0;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        maze = new int[N][M];

        int r, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            maze[r - 1][c - 1] = 1;
        }

        //System.out.println(Arrays.deepToString(maze));
        int result = 0;

        for (r = 0; r < N; r++) {
            for (c = 0; c < M; c++) {
                if (dfs(r, c) != 0) {
                    if (result < maxGarbage) {
                        result = maxGarbage;
                        maxGarbage = 0;
                    }
                }
            }
        }
        System.out.println(result);
    }

    public static int dfs(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M) {
            return 0;
        }
        if (maze[r][c] == 1) {
            maxGarbage++;
            maze[r][c] = 0;

            dfs(r - 1, c);
            dfs(r + 1, c);
            dfs(r, c - 1);
            dfs(r, c + 1);

            return 1;
        }
        return 0;
    }
}
