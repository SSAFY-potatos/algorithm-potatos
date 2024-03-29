import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, M;
	static boolean isTop = true;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		int Ans = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					isTop = true;
					bfs(map, visited, i, j);
					if(isTop) {
						Ans += 1; 
					}
				}
			}
		}
		
		System.out.println(Ans);
	}
	
	static void bfs(int[][] map, boolean[][] visited, int y, int x) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {y, x});
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			visited[curr[0]][curr[1]] = true; 
			int cv = map[curr[0]][curr[1]];
			for(int i=0; i<8; i++) {
				int ny = curr[0] + dy[i];
				int nx = curr[1] + dx[i];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>M-1)
					continue;
				
				int nv = map[ny][nx];
				// 더 높은 봉우리가 있으면 봉우리가 아님(false) 처리하고 탐색만 계속 진행 
				if(cv < nv)	isTop = false; 
				// 방문했거나 높이가 다르면 continue; 
				if(visited[ny][nx] || nv != cv) continue;
				
				queue.offer(new int[] {ny, nx});
			}
		}
	}

}
