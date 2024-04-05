import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[101][101];
		
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			map[y][x] = true;
			List<Integer> graph = new ArrayList<>();
			graph.add(d);
			
			for (int i = 0; i <= g; i++) {
				int size = graph.size();
				for (int j = size - 1; j >= 0; j--) {
					int dir;
					if (i == 0) {
						dir = graph.get(j);
						y += dy[dir];
						x += dx[dir];
						
					} else {
						dir = (graph.get(j) + 1) % 4;
						y += dy[dir];
						x += dx[dir];
						graph.add(dir);
					}
					map[y][x] = true;
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}