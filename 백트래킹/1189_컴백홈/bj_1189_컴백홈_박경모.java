import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int CNT;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		boolean[][] visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		dfs(R - 1, 0, 0, visited);
		System.out.println(CNT);
	}

	static void dfs(int i, int j, int cnt, boolean[][] visited) {	
		if(cnt == K && i == 0 && j == C) {
			CNT++;
			return;
		}

		if (i < 0 || i > R - 1 || j < 0 || j > C - 1) {
			return;
		}
		
		if(map[i][j] == '.' && !visited[i][j]) {
			for(int d=0; d<4; d++) {
				int ni = i + dy[d];
				int nj = j + dx[d];
				visited[i][j] = true;
				dfs(ni, nj, cnt+1, visited);
				visited[i][j] = false;
			}
		}
	}

}
