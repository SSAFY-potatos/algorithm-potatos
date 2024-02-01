package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon_11724 {
	static int N;
	static int M;
	static LinkedList<Integer>[] adjList;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws IOException {
		//read input
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//make adjList, isVisited
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new LinkedList[N+1];
		for(int i=0; i<=N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		isVisited = new boolean[N+1];
		
		int v1, v2;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			adjList[v1].add(v2);
			adjList[v2].add(v1);
		}
		int answer = 0;
		for(int i=1; i<=N; i++) {
			if(!isVisited[i]) {
				dfs_list(i);
				answer++;
			}
		}
		System.out.println("\nanswer: " + answer);
	}
	
	public static void dfs_list(int v) {
		isVisited[v] = true; 
		//System.out.print(v + " "); 

		Iterator<Integer> iter = adjList[v].listIterator(); 
		while (iter.hasNext()) {
			int w = iter.next();
			if (!isVisited[w]) // not visited
				dfs_list(w); // 재귀
		}
	}

}
