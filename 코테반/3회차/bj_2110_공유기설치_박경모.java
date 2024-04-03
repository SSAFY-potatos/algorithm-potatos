//package baekjoon;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int Ans = 0; 
	static int N, C;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[] house = new int[N];
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(house);
		
		// UPPER BOUND 알고리즘 
		// left : 0, right : 배열의 길이로 설정해야 한다고 함(배열의 길이 -1 아님) 
		// 이유 : 배열의 맨 끝에 값을 삽입해야 하는 경우가 존재
		int left = 1; // 최소 거리
		int right = house[N-1] - house[0] + 1; // 최대 거리
		while(left<right) {
			int middle = (left+right)/2; 
			int res = selectedWifi(middle, house);
			if(res < C) { // 공유기를 너무 많이 설치했으면
				right = middle; //거리를 줄여본다. 
			}else{ // 공유기를 더 설치할 수 있으면 
				left = middle + 1; // 거리를 늘려본다. 
			}
			
		}
		System.out.println(left-1); // UPPER BOUND 알고리즘에서는 초과하는 첫번째를 찾으므로 -1
	}
	
	
	static int selectedWifi(int dist, int[] house) {
		int start = house[0];
		int cnt = 1; // 몇 개 선택되었는지 확인 용 카운터
		for(int i=1; i<N; i++) {
			if(house[i] - start >= dist) {
				cnt++; 
				start = house[i];
			}
		}
		return cnt; 
	}
}
