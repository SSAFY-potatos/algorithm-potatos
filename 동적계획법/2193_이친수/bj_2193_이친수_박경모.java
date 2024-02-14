import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static long[] dp; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new long[N];
		System.out.println(fibo(N-1));

	}
	
	static long fibo(int n) {
		if(n == 0 || n == 1) {
			return 1;
		}
		if(dp[n] == 0) {
			dp[n] = fibo(n-1) + fibo(n-2);
		}
		return dp[n]; 
	}

}
