import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class bj_2583_영역구하기_조성우 {

	public static int[] dx = { 0, -1, 0, 1 };
	public static int[] dy = { 1, 0, -1, 0 };
	public static boolean[][] graph;
	public static int maxDep;
	public static List<Integer> depList = new ArrayList<>();
	public static int M, N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new boolean[M][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			int end_x = Integer.parseInt(st.nextToken());
			int end_y = Integer.parseInt(st.nextToken());

			for (int y = start_y; y < end_y; y++) {
				for (int x = start_x; x < end_x; x++) {
					graph[y][x] = true;
				}
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (graph[i][j] == false) {
					maxDep = 1;
					depList.add(dfs(j, i));

				}
			}
		}

		Collections.sort(depList);
		System.out.println(depList.size());
		for (int i = 0; i < depList.size(); i++) {

			if (i == depList.size() - 1) {
				System.out.print(depList.get(i));
				continue;
			}
			System.out.print(depList.get(i) + " ");
		}
	}

	public static int dfs(int x, int y) {
		graph[y][x] = true;
		for (int idxDir = 0; idxDir < 4; idxDir++) {
			int mv_x = x + dx[idxDir];
			int mv_y = y + dy[idxDir];

			if (mv_x < 0 || mv_x >= N || mv_y < 0 || mv_y >= M) {
				continue;
			}
			if (graph[mv_y][mv_x]) {
				continue;
			}
			maxDep++;
			dfs(mv_x, mv_y);
		}
		return maxDep;
	}
}