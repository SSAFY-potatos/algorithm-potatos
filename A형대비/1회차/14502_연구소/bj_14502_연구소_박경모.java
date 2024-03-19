import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int N, M;
	static boolean[][] visited;
	static int[][] map, wallArr;
	static ArrayList<int[]> zeroList, startList;
	static int CNT = 0;
	static int ANS = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		zeroList = new ArrayList<>();
		startList = new ArrayList<>();
		visited = new boolean[N][M];
		wallArr = new int[3][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 0) {
					zeroList.add(new int[] { i, j });
				} else if (temp == 2) {
					startList.add(new int[] { i, j });
				}
				map[i][j] = temp;
			}
		}
		comb(0, 0);
		System.out.println(zeroList.size() - ANS - 3);

	}

	static void comb(int cnt, int start) {
		if (cnt == 3) {
			makeNewMap();
			return;
		}

		for (int i = start; i < zeroList.size(); i++) {
			wallArr[cnt][0] = zeroList.get(i)[0];
			wallArr[cnt][1] = zeroList.get(i)[1];
			comb(cnt + 1, i + 1);
		}
	}

	static void makeNewMap() {
		int[][] newMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < 3; i++) {
			newMap[wallArr[i][0]][wallArr[i][1]] = 1;
		}

		int sum = 0;
		for (int i = 0; i < startList.size(); i++) {
			bfs(newMap, new boolean[N][M], startList.get(i), wallArr);
			sum += CNT;
			CNT = 0;
		}
		ANS = Math.min(ANS, sum);

	}

	static void bfs(int[][] map, boolean[][] visited, int[] start, int[][] wallArr) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int y = temp[0];
			int x = temp[1];
			visited[y][x] = true;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny < 0 || ny > N - 1 || nx < 0 || nx > M - 1)
					continue;

				if ((!visited[ny][nx]) && (map[ny][nx] == 0)) {
					queue.offer(new int[] { ny, nx });
					CNT++;
					map[ny][nx] = -1;
				}
			}

		}

	}
}
