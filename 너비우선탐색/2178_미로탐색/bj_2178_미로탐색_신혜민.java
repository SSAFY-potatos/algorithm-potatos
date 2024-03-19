import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        boolean[][] check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        Queue<Search> q = new LinkedList<>();

        q.offer(new Search(0, 0, 1));
        check[0][0] = true;

        int[] x = { -1, 1, 0, 0 };
        int[] y = { 0, 0, -1, 1 };

        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Search cnt = q.poll();

            if (cnt.x == N - 1 && cnt.y == M - 1) {
                min = Math.min(cnt.r, min);
            }

            for (int i = 0; i < 4; i++) {
                int nextX = cnt.x + x[i];
                int nextY = cnt.y + y[i];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
                    if (arr[nextX][nextY] == 1 && !check[nextX][nextY]) {
                        check[nextX][nextY] = true;
                        q.offer(new Search(nextX, nextY, cnt.r + 1));
                    }

                }
            }
        }

        System.out.println(min);
    }
}

class Search {
    int x;
    int y;
    int r;

    public Search(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}