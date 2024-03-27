package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class bj_14226 {
	
	public static class Node{
		int clip; 
		int screen;
		int time;
		
		public Node(int clip, int screen, int time) {
			this.clip = clip;
			this.screen = screen; 
			this.time = time;
		}
	}
	
	static boolean[][] visited = new boolean[1001][1001]; // clip, total 

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int S = Integer.parseInt(br.readLine());
		// 초기 상태 : 화면 -> 1개, 클립보드 ->0개 
		Node start = new Node(0, 1, 0);
		bfs(S, start);
		
	}
	
	static void bfs(int s, Node start) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[0][1] = true; 
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(curr.screen == s) {
				System.out.println(curr.time);
				return;
			}
			
			// 1. 복사 (화면 -> 클립보드)
			queue.offer(new Node(curr.screen, curr.screen, curr.time+1));
			// 2. 클립보드 -> 화면 
			if(curr.clip !=0 && curr.screen + curr.clip <= s && !visited[curr.clip][curr.screen + curr.clip]) {
				queue.offer(new Node(curr.clip, curr.screen + curr.clip, curr.time + 1));
				visited[curr.clip][curr.screen + curr.clip] = true; 
			}
			// 3. 화면에 있는 이모티콘 1개 삭제
			if(curr.screen >= 1 && !visited[curr.clip][curr.screen -1]) {
				queue.offer(new Node(curr.clip, curr.screen -1, curr.time +1));
				visited[curr.clip][curr.screen -1] = true;
			}		
		}
	}

}
