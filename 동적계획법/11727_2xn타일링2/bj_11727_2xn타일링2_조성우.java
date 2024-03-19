import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_11727_2xn타일링2_조성우 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];

        Arrays.fill(dp, -1);

        if (N >= 1) {
            dp[1] = 1;
        }
        if (N >= 2) {
            dp[2] = 3;
        }

        for (int n = 3; n <= N; n++) {
            dp[n] = (dp[n - 1] + dp[n - 2] * 2) % 10007;
        }
        System.out.println(dp[N] % 10007);
    }
}