import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class bj_1904_01타일_남경민 {
    static int[] dp;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i-1]+ dp[i-2]) % 15746;
        }
        System.out.println(dp[N]);
    }
}
