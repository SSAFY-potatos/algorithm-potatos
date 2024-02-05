import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bj_2667_단지번호붙이기_조성우 {
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };
	public static boolean[][] graph;
	public static List<Integer> cntHouseList = new ArrayList<>() {
	};
	public static int maxDep;

	public static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String inputLine = br.readLine();
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(String.valueOf(inputLine.charAt(j)));
				if (n == 1) {
					graph[i][j] = true;
					continue;
				}
				graph[i][j] = false;
			}
		}

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j]) {
					maxDep = 1;

					int cntHouse = dfs(j, i, 0);
					cntHouseList.add(cntHouse);
				}
			}
		}
		Collections.sort(cntHouseList);
		System.out.println(cntHouseList.size());
		for (int i = 0; i < cntHouseList.size(); i++) {
			System.out.println(cntHouseList.get(i));
		}
	}

	public static int dfs(int x, int y, int dep) {

		graph[y][x] = false;
		for (int idxDir = 0; idxDir < 4; idxDir++) {
			int mv_x = x + dx[idxDir];
			int mv_y = y + dy[idxDir];
			if (mv_x >= 0 && mv_x < N && mv_y >= 0 && mv_y < N) {
				if (graph[mv_y][mv_x]) {
					maxDep++;
					dfs(mv_x, mv_y, dep + 1);
				}
			}
		}
		return maxDep;
	}
}