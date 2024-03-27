import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int S;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] V = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N + 1][M + 1];

        dp[0][S] = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                if (!dp[i][j]) continue;

                if (j + V[i] <= M) {
                    dp[i + 1][j + V[i]] = true;
                }
                if (j - V[i] >= 0) {
                    dp[i + 1][j - V[i]] = true;
                }
            }
        }

        for (int i = M; i >= 0; i--) {
            if (dp[N][i]) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }
}