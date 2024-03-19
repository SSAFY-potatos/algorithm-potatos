import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int N, MAX;

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		List<Integer> answer = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String inputS = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = inputS.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (dfs(map, i, j) != 0) {
					answer.add(MAX);
					MAX = 0;
				}
			}
		}
        Collections.sort(answer);
		System.out.println(answer.size());
		for (int ob : answer) {
			System.out.println(ob);
		}

	}

	static int dfs(int[][] map, int i, int j) {
		if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
			return 0;
		}

		if (map[i][j] == 1) {
			MAX++;
			map[i][j] = 0;

			dfs(map, i + 1, j);
			dfs(map, i, j + 1);
			dfs(map, i - 1, j);
			dfs(map, i, j - 1);

			return 1;
		}

		return 0;

	}
}
