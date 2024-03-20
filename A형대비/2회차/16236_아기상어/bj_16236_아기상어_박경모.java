import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Shark{
		int[] idx; 
		int size; 
		int eatCnt;
		
		public Shark(int[] idx) {
			super();
			this.idx = idx;
			this.size = 2; 
			this.eatCnt = 0;
		} 
	}
	
	static int N, Time;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0}; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Shark baby = null;
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					baby = new Shark(new int[] {i,j});
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			int[] nextIdx = bfs(map, new int[N][N], baby);
			if(nextIdx == null) break; // 초 출력 
			map[nextIdx[0]][nextIdx[1]] = 0;
		}
//		System.out.println("Time : " + Time);
		System.out.println(Time);
	}
	
	static int[] bfs(int[][] map, int[][] visited, Shark baby) {
//		System.out.println("baby Idx : "+Arrays.toString(baby.idx));
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(baby.idx);
//		visited[baby.idx[0]][baby.idx[1]] = 0;
		
		while(!queue.isEmpty()) {
			int[] cIdx = queue.poll();
			
			for(int i=0; i<4; i++) {
				int ny = cIdx[0] + dy[i];
				int nx = cIdx[1] + dx[i];
				
				if(ny<0 || ny>N-1 || nx<0 || nx>N-1) continue;
				
				if(visited[ny][nx] == 0 && map[ny][nx] <= baby.size) {
					queue.offer(new int[] {ny, nx});
					visited[ny][nx] = visited[cIdx[0]][cIdx[1]] + 1;
				}
			}
			
		}
		int min = Integer.MAX_VALUE;
		int[] nextIdx = null;
//		System.out.println("Visited: ");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println("Map: ");
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j] >0 && map[i][j] !=0 && map[i][j] < baby.size) {
					if(visited[i][j] < min) {
						min = visited[i][j];
						nextIdx = new int[] {i,j};
					}
				}
			}
		}
		
//		System.out.println(Arrays.toString(nextIdx));
		if(nextIdx != null) {
			Time += visited[nextIdx[0]][nextIdx[1]];
			baby.eatCnt += 1;
			if(baby.eatCnt == baby.size) {
//				System.out.println("baby grownup!");
				baby.size += 1;
				baby.eatCnt = 0;
			}
			baby.idx = nextIdx;
		}
		return nextIdx;
	}
}
