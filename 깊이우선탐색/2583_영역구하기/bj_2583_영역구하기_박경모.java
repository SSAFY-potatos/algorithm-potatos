import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int M, N, MAX;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int xS = Integer.parseInt(st.nextToken());
			int yS = Integer.parseInt(st.nextToken());
			int xE = Integer.parseInt(st.nextToken());
			int yE = Integer.parseInt(st.nextToken());
			
			for(int j = yS; j<yE; j++) {
				for(int k = xS; k<xE; k++) {
					map[M-1-j][k] = 1;
				}
			}
		}
		List<Integer> answer = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(dfs(map, i, j) != 0) {
					answer.add(MAX);
					MAX = 0;
				}
			}
		}
		answer.sort(Comparator.naturalOrder());
		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append("\n");
		for(int ob: answer) {
			sb.append(ob).append(" ");
		}
		System.out.print(sb);
	}
	
	static int dfs(int[][] map, int i, int j) {
		if(i<0 || i>M-1 || j<0 ||j>N-1)
			return 0;
		if(map[i][j] == 0) {
			map[i][j] = 1;
			MAX +=1; 
			
			dfs(map, i-1, j);
			dfs(map, i+1, j);
			dfs(map, i, j-1);
			dfs(map, i, j+1);
			
			return 1;
		}
		return 0;
	}

}
