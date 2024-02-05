import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1182_부분수열의합_조성우 {

    static int N, S;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        board = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
        System.out.println(S == 0 ? cnt - 1 : cnt);
    }

    static int cnt = 0;

    static void dfs(int dep, int sum) {
        if (dep == N) {
            if (sum == S) {
                cnt++;
            }
            return;
        }

        dfs(dep + 1, sum);
        dfs(dep + 1, sum + board[dep]);

    }
}