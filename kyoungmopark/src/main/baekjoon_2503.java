package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class baekjoon_2503 {

	public static void main(String[] args) throws IOException {
		List<Integer> answer = new ArrayList<Integer>();
		
		// 초기 정답군 설정
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j ++) {
				for(int k = 1; k <= 9; k++) {
					if(i != j && j !=k && i != k) {
						answer.add(i * 100 + j * 10 + k);
					}
				}
			}
		}
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] inputArray = new int[N][3];
		
		for(int i=0; i<N; i++) {
			String token[] = br.readLine().split(" ");
			for(int j=0; j<3; j++) {
				inputArray[i][j] = Integer.parseInt(token[j]);
			}
		}
		
		//System.out.println(Arrays.deepToString(inputArray));
		
		for(int i=0; i<N; i++) {
			int guess = inputArray[i][0];
			int strike = inputArray[i][1];
			int ball = inputArray[i][2];
			int wrong = 3 - strike - ball;
			
			
			
		}
	}

}
