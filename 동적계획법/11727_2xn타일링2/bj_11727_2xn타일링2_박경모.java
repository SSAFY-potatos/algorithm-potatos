import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		fibo(N);
	}
	
	static void fibo(int n) {
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i=2; i<=n; i++) {
			dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;
		}
		
		System.out.println(dp[n]);
	}
	
	

}
