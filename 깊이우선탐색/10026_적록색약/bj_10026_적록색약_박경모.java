import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N; 
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("./src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		char[][] tmap = new char[N][N]; // 적록색약용 배열 
		boolean[][] visited = new boolean[N][N];
		boolean[][] tvisited = new boolean[N][N]; 
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
				if(s.charAt(j) == 'R') {
					tmap[i][j] = 'G';
				}else {
					tmap[i][j] = s.charAt(j);
				}
			}
		}
		int answer = 0;
		int tanswer = 0; // 적록색약
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dfs(map, i, j, map[i][j], visited) == 1) {
					++answer;
				}
				if(dfs(tmap, i, j, tmap[i][j], tvisited) == 1) {
					++tanswer;
				}
			}
		}
		
		System.out.println(answer + " " + tanswer);
		
		
	}
	
	static int dfs(char[][] map, int i, int j, char color, boolean[][] visited) {
		if(i <0 || j <0 || i > N-1 || j > N-1) {
			return 0;
		}
		
		if(color == map[i][j]&& !visited[i][j]) {
			visited[i][j] = true;
			dfs(map, i-1, j, color, visited);
			dfs(map, i+1, j, color, visited);
			dfs(map, i, j-1, color, visited);
			dfs(map, i, j+1, color, visited);
			
			return 1;
			
		}
		
		return 0;
		
		
	}

}
