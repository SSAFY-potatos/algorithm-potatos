import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	
	static boolean[][] map;
	
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = (s.charAt(j) == '1');
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		boolean[][][] visited = new boolean[2][N][M];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, 0, 0});
		visited[0][0][0] = true;
		int time = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int t = 0; t < size; t++) {
				int[] curr = q.poll();
				
				if (curr[0] == N - 1 && curr[1] == M - 1) {
					return time;
				}
				
				for (int i = 0; i < 4; i++) {
					int ny = curr[0] + dy[i];
					int nx = curr[1] + dx[i];
					
					if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
					if (visited[curr[2]][ny][nx]) continue;
					
					if (map[ny][nx]) {
						if (curr[2] == 0) {
							q.add(new int[] {ny, nx, 1});
							visited[1][ny][nx] = true;
						}
						continue;
					}
					
					q.add(new int[] {ny, nx, curr[2]});
					visited[curr[2]][ny][nx] = true;
				}
			}
			time++;
		}
		return -1;
	}
}