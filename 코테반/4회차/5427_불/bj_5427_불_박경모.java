import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
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
	
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static int h, w;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			char[][] map = new char[h][w];
			boolean[][] visited = new boolean[h][w];
			Node start = null;
			Queue<Node> fireQueue = new ArrayDeque<>();
			
			for(int i=0; i<h; i++) {
				String s = br.readLine();
				for(int j=0; j<w; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j] =='@') {
						start = new Node(i, j, 0);
					}else if(map[i][j] == '*') {
						fireQueue.offer(new Node(i, j, -1));
					}
				}
			}
			//bfs 
			int result = bfs(map, visited, start, fireQueue);
			sb.append(result == -1 ? "IMPOSSIBLE" : result);
			sb.append("\n");
			//System.out.println(result == -1 ? "IMPOSSIBLE" : result);

		}
		System.out.println(sb);
	}
	
	static int bfs(char[][] map, boolean[][] visited, Node start, Queue<Node> fireQueue) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int fireIter = fireQueue.size();
			for(int i=0; i<fireIter; i++) { // move fire
				Node fireTemp = fireQueue.poll();
				int fcy = fireTemp.y;
				int fcx = fireTemp.x;
				
				for(int j=0; j<4; j++) {
					int fny = fcy + dy[j];
					int fnx = fcx + dx[j];
					
					if(fny<0 || fny > h-1 || fnx<0 || fnx >w-1)
						continue;
					
					if(map[fny][fnx] == '.' || map[fny][fnx] == '@') {
						map[fny][fnx] = '*';
						fireQueue.offer(new Node(fny, fnx, -1));
					}
				}
			}
			
			int iter = queue.size();
			for(int k=0; k<iter; k++) {
				Node temp = queue.poll();
				int cy = temp.y;
				int cx = temp.x;
				int ct = temp.time;
				visited[cy][cx] = true;
				
				for(int i=0; i<4; i++) {
					int ny = cy + dy[i];
					int nx = cx + dx[i];
					
					if(ny<0 || ny>h-1 || nx<0 || nx>w-1) {
						return temp.time + 1;
					}
					
					if(!visited[ny][nx] && map[ny][nx] == '.') {
						map[ny][nx] = '@';
						queue.offer(new Node(ny, nx, ct+1));
					}
				}
			}

		}
		return -1;
	}
}
