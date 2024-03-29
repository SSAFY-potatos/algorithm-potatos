import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] V = new int[N];
		for(int i=0; i<N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		 Stack<Integer> stackEven = new Stack<>(); 
		 Stack<Integer> stackOdd = new Stack<>(); 
		 
		 stackEven.push(S);
		 int temp; 
		 for(int i=0; i<N; i++) {
			 if(i%2 == 0) {
				 while(!stackEven.isEmpty()) {
					 temp = stackEven.pop();
					 if(temp+V[i]<=M) {
						 stackOdd.push(temp+V[i]);
					 }
					 if(temp-V[i]>=0) {
						 stackOdd.push(temp-V[i]);
					 }
				 }
			 }if(i%2 != 0) {
				 while(!stackOdd.isEmpty()) {
					 temp = stackOdd.pop();
					 if(temp+V[i]<=M) {
						 stackEven.push(temp+V[i]);
					 }
					 if(temp-V[i]>=0) {
						 stackEven.push(temp-V[i]);
					 }
				 }
			 }			 
		 }

		 int Ans = -1; 
		 if(N%2 ==0) {
			 while(!stackEven.isEmpty()) {
				 int temp2 = stackEven.pop();
				 Ans = (Ans > temp2) ? Ans :temp2;
			 }
		 }else {
			 while(!stackOdd.isEmpty()) {
				 int temp2 = stackOdd.pop();
				 Ans = (Ans > temp2) ? Ans :temp2;
			 }
		 }
		 
		 System.out.println(Ans);
		 
			 
		
	}
}


/* 풀이2 (메모리초과 : 큐 사용)
package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1495_기타리스트 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] V = new int[N];
		for(int i=0; i<N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(S); 
		int cycle = 1; 
		for(int i=0; i<N; i++) {
			int cnt = 0; 
			for(int j=0; j<cycle; j++) {
				int temp = queue.poll();
				if(temp - V[i] >= 0) {
					queue.offer(temp - V[i]);
					cnt++;
				}
				if(temp + V[i] <= M) {
					queue.offer(temp + V[i]);
					cnt++;
				}
			}
			cycle = cnt;
		}
		int max = -1;
		while(!queue.isEmpty()) {
			int item = queue.poll();
			max = (max>item) ? max : item;
		}
		System.out.println(max);
	}
}



*/
