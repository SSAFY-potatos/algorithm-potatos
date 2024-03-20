import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        int[] num = { 2, 3 };

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }

        sb.append(dp[N]).append("\n");

        int idx = N;

        while (true) {
            sb.append(idx + " ");

            if (idx == 1) {
                break;
            }

            int next = idx - 1;
            int min = dp[idx - 1];

            for (int i = 0; i < 2; i++) {
                if (idx % num[i] == 0) {
                    if (dp[idx / num[i]] < min) {
                        next = idx / num[i];
                        min = dp[idx / num[i]];
                    }
                }
            }
            idx = next;
        }
        System.out.println(sb);
    }
}