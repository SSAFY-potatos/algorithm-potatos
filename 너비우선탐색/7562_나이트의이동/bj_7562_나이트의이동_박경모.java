import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int[][] board;
	static int[] curr;
	static int[] goal; 
	static int dx[] = {2, 1, -1, -2, 2, 1, -1, -2};
	static int dy[] = {1, 2, 2, 1, -1, -2, -2, -1};
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			board = new int[L][L]; // == isvisited와 같음 
			curr = new int[2]; // 현재 나이트의 위치
			goal = new int[2];  // 나이트가 이동하려고 하는 위치
			
			st = new StringTokenizer(br.readLine());
			curr[0] = Integer.parseInt(st.nextToken());
			curr[1] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			goal[0] = Integer.parseInt(st.nextToken());
			goal[1] = Integer.parseInt(st.nextToken());
			
			System.out.println(bfs(board, curr, goal));
		}
	}
	
	public static int bfs(int[][] map, int[] start, int[] end) {
		if(start[0] == end[0] && start[1] == end[1])
			return 0;
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0];
			int y = cur[1]; 
			
			for(int i=0; i<8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				//map의 범위를 벗어나는 경우 
				if(nx <0 || nx > map.length-1 || ny < 0 || ny > map[0].length-1) {
					continue;
				}
				
				//벽이거나 이미 방문한 곳일때 
				if(map[nx][ny] != 0) {
					continue;
				}
				map[nx][ny] = map[x][y] + 1;
				//갈 수 있는 경우, 해당 위치 좌표를 다시 queue에 넣는다. 
				queue.offer(new int[] {nx,ny});
			}
		}
		return map[end[0]][end[1]];
	}

}
