import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int IDX = 1; // 섬 구분용 IDX 
	static int MIN = Integer.MAX_VALUE;
	static class Node{
		int y, x;
		int time;
		public Node(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 구분 짓기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					bfs(map, visited, new Node(i,j, -1));
					IDX++;
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		//섬 탐색하기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 0) {
//					System.out.println(i + " : " + j);
					bfs2(map, new boolean[N][N], new Node(i,j, 0));
				}
			}
		}
		System.out.println(MIN);
	}
	
	static void bfs(int[][] map, boolean[][] visited, Node start) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start.y][start.x] = true;
		
		while(!queue.isEmpty()) {
			Node temp = queue.poll();
			int cy = temp.y;
			int cx = temp.x;

			map[cy][cx] = IDX;
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0|| ny>N-1|| nx<0 || nx>N-1)
					continue;
				
				if(!visited[ny][nx] && map[ny][nx] != 0) {
					map[ny][nx] = IDX;
					queue.offer(new Node(ny, nx, -1));
					visited[ny][nx] = true;
				}

			}
		}

	}
	
	static void bfs2(int[][] map, boolean[][] visited, Node start) {
		Queue<Node> queue = new ArrayDeque<>();
		visited[start.y][start.x] = true; 
		queue.offer(start);
		int startIdx = map[start.y][start.x];

		//"BFS는 큐에서 뺀 다음이 아닌, 큐에 넣을 때 방문 체크를 해야 중복 방문이 일어나지 않습니다."
		
		while(!queue.isEmpty()) {
			Node temp = queue.poll();
			int cy = temp.y;
			int cx = temp.x;
			int ct = temp.time;
			
			for(int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				
				if(ny<0|| ny>N-1|| nx<0 || nx>N-1)
					continue;
				
				if(map[ny][nx] !=0 && map[ny][nx] != startIdx) {
					MIN = ct<MIN ? ct : MIN;
					return;
				}
				
				if(!visited[ny][nx] && map[ny][nx] != startIdx) {
					queue.offer(new Node(ny, nx, ct+1));
					visited[ny][nx] = true;
				}
			}
		}

	}

}
