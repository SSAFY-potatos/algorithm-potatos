package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_1912 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(input[i]);
		
		int posSum = 0;
		int negSum = 0;
		int max = arr[0];
		
//		for (int i = 0; i < N-1 ; i++) {
//			if(arr[i] > 0 && arr[i+1] > 0)
//			
//		}
	}
}
		
		

