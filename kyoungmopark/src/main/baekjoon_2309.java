package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_2309 {
		static int sum =0; 
		static int[] inputArray = new int[9];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) {
			inputArray[i] = Integer.parseInt(br.readLine());
			sum+= inputArray[i];
		}
		Arrays.sort(inputArray);
		int a=0, b=0;
		for(int i=0; i<9; i++) {
			for(int j=i+1; j<9; j++) {
				if(inputArray[i]+inputArray[j] == sum-100) {
					a = i;
					b = j; 
					break;
				}
			}
		}
		
		for(int i=0; i<9; i++) {
			if(i != a && i != b)
				System.out.println(inputArray[i]);
		}
		
	}
}
