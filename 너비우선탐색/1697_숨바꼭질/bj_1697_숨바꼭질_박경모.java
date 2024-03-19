import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int[] dx = { -1, 1, 2 };
	static int[] Visited = new int[200000];

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(bfs(N, K));
	}

	public static int bfs(int start, int end) {
		if (start == end)
			return 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);

		while (!queue.isEmpty()) {
			int x = queue.poll();
			int nx = 0;
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
				case 1:
					nx = x + dx[i];
					break;
				case 2:
					nx = x * dx[i];
					break;
				}
				if(nx == K) {
					return Visited[x] + 1;
				}
				if (nx < 0 || nx > Visited.length - 1) {
					continue;
				}
				if (Visited[nx] != 0) {
					continue;
				}
				Visited[nx] = Visited[x] + 1;
				queue.offer(nx);
			}
		}
		return Visited[end];
	}
}
