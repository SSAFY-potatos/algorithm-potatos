import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_11726_2xn타일링_조성우 {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        Arrays.fill(dp, -1);

        System.out.println(dfs(N));
    }

    static long dfs(int n) {
        if (dp[n] > -1) {
            return dp[n];
        }
        if (n == 1) {
            return dp[n] = 1;
        }
        if (n == 2) {
            return dp[n] = 2;
        }
        dp[n] = (dfs(n - 1) + dfs(n - 2)) % 10007;
        return dp[n];
    }
}