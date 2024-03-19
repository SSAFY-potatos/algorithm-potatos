import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj_1463_1로만들기_조성우 {
    static int[] dp;
    static int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        dp = new int[MAX];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;

        for (int i = 1; i < X; i++) {
            if (i + 1 < MAX) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            }
            if (i * 2 < MAX) {
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            }
            if (i * 3 < MAX) {
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            }
        }
        System.out.println(dp[X]);
    }
}