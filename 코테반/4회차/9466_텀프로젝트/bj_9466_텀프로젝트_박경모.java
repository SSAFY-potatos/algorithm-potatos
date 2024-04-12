import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] graph;
	static int n, connected;
	static boolean[] visited, done; // 팀을 고른 학생들
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			graph = new int[n+1];
			connected = 0;
			
			visited = new boolean[n+1]; 
			done = new boolean[n+1];  // 팀 구성 끝난 노드들 
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=n; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
				if(i == graph[i]) {
					connected++; 
					done[i] = true;
				}
			}
			
			for(int i=1; i<=n; i++) {
				if(!done[i]) {
					dfs(i);
				}
			}
			
			sb.append(n - connected + "\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int node) {
		if(visited[node]) {
			done[node] = true;
			connected++;
		}else {
			visited[node] = true;
		}
		
		if(!done[graph[node]]) {
			dfs(graph[node]);
		}
		visited[node] = false;
		done[node] = true;
	}
	


}
