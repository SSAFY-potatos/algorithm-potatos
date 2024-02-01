package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class baekjoon_2644 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		int V1 = Integer.parseInt(line[0]);
		int V2 = Integer.parseInt(line[1]);
		int M = Integer.parseInt(br.readLine());
		
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
		
		System.out.println(Arrays.deepToString(adjList));
		boolean[] visited = new boolean[N+1];
		bfs_list(V1, adjList, visited);
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
}
