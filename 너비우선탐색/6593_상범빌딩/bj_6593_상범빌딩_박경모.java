import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] start = new int[3];
	static int[] end = new int [3];
	static int[] dx = {1, -1 , 0, 0, 0, 0};
	static int[] dy = {0, 0, 1, -1, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	static int[][][] isvisited;
	static int N;
	static int R;
	static int C;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			if (N == 0 && R == 0 && C == 0)
				break;
			char[][][] map = new char[N][R][C];
			isvisited = new int [N][R][C];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < R; j++) {
					String s = br.readLine();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = s.charAt(k);
						if(s.charAt(k) == 'S') {
							start[0] = i;
							start[1] = j;
							start[2] = k;
						}
						else if(s.charAt(k) == 'E') {
							end[0] = i;
							end[1] = j;
							end[2] = k;
						}
						
					}
				}
				br.readLine();
			}
			int answer = bfs(map, start, end);
			if(answer == 0) {
				System.out.println("Trapped!");
				
			}else {
				System.out.println("Escaped in "+answer+" minute(s).");
			}
			
		}
	}
	
	public static int bfs(char[][][] map, int[] start, int[] end) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int z = cur[0];
			int x = cur[1];
			int y = cur[2];
			
			for(int i=0; i<6; i++) {
				int nz = z + dz[i];
				int nx = x + dx[i]; 
				int ny = y + dy[i];
				
				// 범위 터지는지 확인 
				if(nz < 0 || nz> N-1 || nx < 0 || nx > R-1 || ny < 0 || ny > C-1) {
					continue;
				}
				if(map[nz][nx][ny] == '#' || isvisited[nz][nx][ny] != 0) {
					continue;
				}
				
				isvisited[nz][nx][ny] = isvisited[z][x][y] + 1;
				int[] nstart = new int[3]; 
				nstart[0] = nz;
				nstart[1] = nx;
				nstart[2] = ny;
				
				queue.offer(nstart);
			}
		}
		return isvisited[end[0]][end[1]][end[2]];
		
	}
}
