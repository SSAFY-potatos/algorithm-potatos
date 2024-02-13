import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        sol(N);
        System.out.println(dp[N]);
    }

    static int sol(int n) {
        if (n <= 1) return 0;
        if (dp[n] != 0) return dp[n];

        dp[n] = Integer.MAX_VALUE;
        if (n % 3 == 0) {
            dp[n] = Math.min(dp[n], sol(n / 3) + 1);
        }
        if (n % 2 == 0) {
            dp[n] = Math.min(dp[n], sol(n / 2) + 1);
        }

        dp[n] = Math.min(dp[n], sol(n - 1) + 1);
        return dp[n];
    }
}