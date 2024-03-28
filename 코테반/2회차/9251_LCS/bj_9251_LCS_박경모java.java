package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_9251_LCS {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int s1Len = s1.length();
		int s2Len = s2.length();
		
		int[][] LCS = new int[s1Len+1][s2Len+1];
		
		for(int i =1; i<s1Len+1; i++) {
			for(int j=1; j<s2Len+1; j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					LCS[i][j] = LCS[i-1][j-1] + 1; 
				}else if(s1.charAt(i-1) != s2.charAt(j-1)) {
					LCS[i][j] = (LCS[i-1][j] > LCS[i][j-1]) ? LCS[i-1][j] : LCS[i][j-1];
				}
			}
		}
		int max = 0;
		
		for(int i=0; i<s1Len+1; i++) {
			for(int j=0; j<s2Len+1; j++) {
				max = (LCS[i][j] > max) ? LCS[i][j] : max;
			}
		}
		System.out.println(max);
	}
}
