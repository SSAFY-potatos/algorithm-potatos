//package baekjoon;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] table = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] sum_dp = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sum_dp[i][j] = sum_dp[i-1][j] + sum_dp[i][j-1] - sum_dp[i-1][j-1] 
								+table[i][j];
			}
		}
		
//		System.out.println(Arrays.deepToString(sum_dp));
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			System.out.println(sum_dp[x2][y2] - sum_dp[x2][y1-1] - sum_dp[x1-1][y2]
								+sum_dp[x1-1][y1-1]);
		}
	}
}
