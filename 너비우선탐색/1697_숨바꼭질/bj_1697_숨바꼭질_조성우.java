import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1697_숨바꼭질_조성우 {

    public static Queue<Integer> q = new LinkedList<>();
    public static int[] board = new int[100001];
    public static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
        } else {
            bfs(N);
        }

    }

    public static void bfs(int N) {
        q.add(N);
        board[N] = 1;

        int curPos;
        int mvPos = 0;
        while (!q.isEmpty()) {
            curPos = q.poll();

            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0:
                        mvPos = curPos * 2;
                        break;
                    case 1:
                        mvPos = curPos + 1;
                        break;
                    case 2:
                        mvPos = curPos - 1;
                }
                if (curPos == K) {
                    System.out.println(board[curPos] - 1);
                    return;
                }

                if (mvPos >= 0 && mvPos < board.length && board[mvPos] == 0) {
                    q.add(mvPos);
                    board[mvPos] = board[curPos] + 1;
                }
            }
        }
    }
}