import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2193_이친수_남경민 {
    static long[] dp;
    static long result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N+1];
        dp[1] = 1;
        System.out.println(sol(N));
    }

    static long sol(int n) {
        if(n<=2) return 1;

        if(dp[n]!=0) {
            return dp[n];
        }

        result = sol(n-1) + sol(n-2);
        dp[n] = result;

        return result;
    }
}
