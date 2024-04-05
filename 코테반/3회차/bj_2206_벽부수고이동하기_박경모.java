//package baekjoon;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{ // 큐에 넣을 노드
		int y; 
		int x;
		boolean isUsed; // 벽 부수기(1회)를 사용했는지 여부 
		
		public Node(int y, int x, boolean isUsed) {
			super();
			this.y = y;
			this.x = x;
			this.isUsed = isUsed;
		}
	}
	
	static int N, M;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {
			//System.setIn(new FileInputStream("input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			int[][][] visited = new int[N][M][2]; // 최단 경로를 구하는 배열, 0 : 벽뚫기 x, 1 : 벽뚫기 O 

			
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<M; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			bfs(map, visited);
			
			if(visited[N-1][M-1][0] == 0 && visited[N-1][M-1][1]==0) {
				System.out.println(-1);
			}else if(visited[N-1][M-1][0] == 0) {
				System.out.println(visited[N-1][M-1][1]);
			}else if(visited[N-1][M-1][1] == 0) {
				System.out.println(visited[N-1][M-1][0]);
			}else {
				System.out.println(visited[N-1][M-1][0] < visited[N-1][M-1][1] ? 
						visited[N-1][M-1][0] : visited[N-1][M-1][1]);
			}
			
	}
	static void bfs(int[][] map, int[][][] visited) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0, false));
		visited[0][0][0] = 1; 
		
//		visited[0][0].dist = 1;
//		visited[0][0].breaked = false; 
		
		while(!queue.isEmpty()) {
			Node tNode = queue.poll(); // temp Node
			int cy = tNode.y;
			int cx = tNode.x;

			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0 || ny > N-1 || nx<0 || nx > M-1)	continue;
				
				if(tNode.isUsed) { // 벽뚫기를 한 경우 
					if(map[ny][nx] == 0 && visited[ny][nx][1] == 0) {
						visited[ny][nx][1] = visited[cy][cx][1] + 1; 
						queue.offer(new Node(ny, nx, true));
					}
				}else { // 벽뚫기를 하지 않은 경우 
					if(map[ny][nx] == 0 && visited[ny][nx][0] == 0) {
						visited[ny][nx][0] = visited[cy][cx][0] + 1; 
						queue.offer(new Node(ny, nx, false));
					}else{//map[ny][nx] == 1
						if(visited[ny][nx][1] == 0 || (visited[ny][nx][1] > visited[cy][cx][0] + 1)) {
							visited[ny][nx][1] = visited[cy][cx][0] + 1; 
							queue.offer(new Node(ny, nx, true));
						}

					}
				}
			}
		}
	}
}
