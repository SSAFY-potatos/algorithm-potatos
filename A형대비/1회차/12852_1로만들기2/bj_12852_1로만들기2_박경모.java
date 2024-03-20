import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static Integer[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new Integer[N+1];
		dp[0] = dp[1] = 0;
		
		System.out.println(calc(N));
		int idx = N;
		while(idx != 1) {
			if(dp[idx-1] == dp[idx] -1) {
				System.out.print(idx+ " ");
				idx--;
			}else if(idx%2 == 0 && dp[idx / 2] == dp[idx] -1) {
				System.out.print(idx+ " ");
				idx /= 2;
			}else if(idx%3 == 0 && dp[idx / 3] == dp[idx] -1) {
				System.out.print(idx+ " ");
				idx /= 3;
			}
		}
		System.out.print("1"+'\n');
	}
	
	static int calc(int n) {
		if(dp[n] == null) {
			if(n % 6 == 0) {
				dp[n] = Math.min(calc(n/3), Math.min(calc(n/2), calc(n-1)))+1;
			}else if(n % 3 == 0) {
				dp[n] = Math.min(calc(n/3), calc(n-1))+1;
			}else if(n%2 == 0) {
				dp[n] = Math.min(calc(n/2), calc(n-1))+1;
			}else {
				dp[n] = calc(n-1)+1;
			}
		}
		
		return dp[n];
	}
}
