package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class baekjoon_1260 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//Read input 
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		int V = Integer.parseInt(line[2]); // 시작 정점 
		
		LinkedList<Integer>[] adjList = new LinkedList[N+1];
		for(int i=0; i<=N; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
				
		for(int i=0; i<M; i++) {
			line = br.readLine().split(" ");
			int v1 = Integer.parseInt(line[0]);
			int v2 = Integer.parseInt(line[1]);
			adjList[v1].add(v2);
			adjList[v2].add(v1);
		}
//		System.out.println(Arrays.deepToString(adjList));	
		// sort(정점 번호가 작은 것을 먼저 방문)
		for(int i=0; i<=N; i++) {
			Collections.sort(adjList[i]);
		}
		
		System.out.println(Arrays.deepToString(adjList));
		
		//BFS
		boolean[] bfs_visited = new boolean[N+1];
		boolean[] dfs_visited = new boolean[N+1];
		
		dfs_list(V, adjList, dfs_visited);
		System.out.println();
		bfs_list(V, adjList, bfs_visited);
	}
	
	public static void bfs_list(int v, LinkedList<Integer>[] adjList, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[v] = true; 
		queue.add(v);

		while(queue.size() != 0) { 
			v = queue.poll(); 
			System.out.print(v + " ");

			Iterator<Integer> iter = adjList[v].listIterator();
			while(iter.hasNext()) { 
				int w = iter.next(); 
				if(!visited[w]) { 
					visited[w] = true; 
					queue.add(w); 
				} 
			}
		}
	}
	
	public static void dfs_list(int v, LinkedList<Integer>[] adjList, boolean[] visited) {
		visited[v] = true; 
		System.out.print(v + " "); 

		Iterator<Integer> iter = adjList[v].listIterator(); 
		while (iter.hasNext()) {
			int w = iter.next();
			if (!visited[w]) // not visited
				dfs_list(w, adjList, visited); // 재귀
		}
	}
}
