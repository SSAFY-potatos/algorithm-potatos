package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// visited가 필요 없음. (visited = maze) 
public class baekjoon_1012 {
	static int testCase;
	static int M;
	static int N;
	static int K;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int[][] maze;
	//static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		testCase = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= testCase; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			maze = new int[N][M];
//			visited = new boolean[N][M];
			int x, y;
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				maze[y][x] = 1;

			}
			int result = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(dfs(i, j) == true)
						result++;
				}
			}
			System.out.println(result);
		}
	}
	
	public static boolean dfs(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=M)
			return false;
		
		if(maze[x][y] == 1) {
			maze[x][y] =0;
			
			dfs(x-1, y);
			dfs(x+1, y);
			dfs(x, y-1);
			dfs(x, y+1);
			return true;
		}
		return false;
		
	}
	
}
	
