import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_5014_스타트링크_조성우 {

    public static int[] board;
    public static boolean[] visited;
    public static Queue<Integer> q = new LinkedList<>();
    public static int F, G, S;
    public static int U, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[F + 1];
        visited = new boolean[F + 1];

        int res = bfs(S);

        if (res == -1) {
            System.out.println("use the stairs");
        } else {
            System.out.println(res);
        }
    }

    public static int bfs(int S) {
        q.add(S);
        visited[S] = true;

        int curPos;
        while (!q.isEmpty()) {
            curPos = q.poll();
            if (curPos == G) {
                return board[curPos];
            }

            int mvPos;
            for (int idxDir = 0; idxDir < 2; idxDir++) {
                mvPos = idxDir == 0 ? curPos + U : curPos - D;
                if (mvPos < 1 || mvPos > F || visited[mvPos]) {
                    continue;
                }

                q.add(mvPos);
                visited[mvPos] = true;
                board[mvPos] = board[curPos] + 1;

            }
        }
        return -1;
    }
}
