import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int F;
	static int U;
	static int D;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken()); // 총 F층
		int S = Integer.parseInt(st.nextToken()); // start
		int G = Integer.parseInt(st.nextToken()); // goal 
		U = Integer.parseInt(st.nextToken()); // 위로 U층만큼이동
		D = Integer.parseInt(st.nextToken()); // 아래로 D층만큼이동 
		
		int[] map = new int[F+1];
		int result = bfs(map, S, G);
		if(result == 0) {
			System.out.println("use the stairs");
		}else if(result == -1){
			System.out.println("0");
		}else
			System.out.println(result);
	}
	
	public static int bfs(int[]map, int start, int end) {
		if(start == end) {
			return -1;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int y = queue.poll();
			int yUp = y + U;
			int yDown = y - D;
			
			if(U !=0 && yUp <=F && map[yUp] == 0) {
				map[yUp] = map[y] + 1;
				queue.offer(yUp);
			}
			if(D != 0 && yDown >=1 && map[yDown] == 0) {
				map[yDown] = map[y] + 1;
				queue.offer(yDown);
			}
		}
		
		return map[end];
	}

}
