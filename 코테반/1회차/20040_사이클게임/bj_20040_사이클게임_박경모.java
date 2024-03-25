package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_20040 {
	static int[] parent;
	static int N, M, result; 
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N];
		
		for(int i=0; i<N; i++) {
			parent[i] = i;
		}
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(!union(x, y)) {
				result = i;
				break; 
			}
		}
		System.out.println(result);
	}
	public static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y)
			return false;
		
		parent[y] = x;
		return true;
	}

}
