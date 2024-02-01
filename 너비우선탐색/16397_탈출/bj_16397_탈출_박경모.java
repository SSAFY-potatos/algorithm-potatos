import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, T, G;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		
		int[] map = new int [100000];
		int answer = bfs(map, N, G);
		
		if(answer == -1) {
			System.out.println(0);
		}else if(answer <= T && answer != 0) {
			System.out.println(answer);
		}else {
			System.out.println("ANG");
		}
		
	}
	public static int bfs(int[] map, int start, int end) {
		if(start == end) return -1;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			int nx1 = x +1;
			int nx2 = (int) (x*2 - Math.pow(10, ((int)(Math.log10(x*2)))));
			
			if(nx1 <= 100000-1 && map[nx1] == 0) {
				map[nx1] = map[x] + 1;
				queue.offer(nx1);
				
			}
			
			if(x*2 < 100000 -1 && nx2 < 100000-1 && map[nx2] ==0) {
				map[nx2] = map[x] + 1;
				queue.offer(nx2);
			}
		}
		
		return map[end];
	}
}
