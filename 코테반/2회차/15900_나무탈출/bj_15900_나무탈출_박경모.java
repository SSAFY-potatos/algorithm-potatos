import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int Ans = 0;
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// 리스트+배열 초기화
		List<Integer>[] tree= new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			tree[i] = new ArrayList<>();
		}
		boolean[] visited = new boolean[N+1];
		
		for(int i=0; i<N-1; i++) { // tree 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			tree[a].add(b);
			tree[b].add(a);
		}
	
		dfs(tree, 1, visited, 0);
		System.out.println(Ans%2==0 ? "No" : "Yes");
	}
	
	static void dfs(List<Integer>[] tree, int currNode, boolean[] visited, int height) {
		visited[currNode] = true; 
		
		if(tree[currNode].size() == 1) {
			Ans += height;
		}
		
		for(int nextNode : tree[currNode]) {
			if(!visited[nextNode])
				dfs(tree, nextNode, visited, height+1);
		}
	}
}

/* 망한 풀이1 (시간초과) 
 		int[] parentInfo = new int[N+1]; // 0번째 idx는 사용 x 
		int Ans = 0; 
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a == 1) { // a가 루트 노드인 경우 
				parentInfo[b] = a;
			}else if(b == 1) { // b가 루트 노드인 경우
				parentInfo[a] = b;
			}else {
				if(parentInfo[a] != 0) { //a의 부모가 이미 있는 경우
					parentInfo[b] = a;
				}else { // b의 부모가 이미 있는 경우
					parentInfo[a] = b;
				}
			}
		}
		 
		for(int i=2; i<N+1; i++) {
			int flag = 1;
			for(int j=2; j<N+1; j++) {  // Leaf 노드인지 검사
				if(parentInfo[j] == i) {
					flag = 0;
					break; // 누군가의 부모이면 break; 
				}	
			}
			if(flag == 0) continue; // leaf  노드가 아닌 경우	
			int height = 0;
			int start = i; 
			while(start != 1) { // 루트에 갈 때까지 높이 구하기
				start = parentInfo[start];
				height++;
			}
			Ans += height; 
		}
		System.out.println(Ans%2==0 ? "No" : "Yes");
		
		망한 풀이2(bfs)
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		int height = 1;
		int Ans = 0;
		queue.offer(1); 
		
		while(!queue.isEmpty()) {
			int cNode = queue.poll();
			System.out.println("cNode" + cNode);
			visited[cNode] = true;
			for(int nNode : tree[cNode]) {
				if(tree[nNode].size() == 1) {
					Ans += height;
					System.out.println("height: " + height  + " node: "+nNode);
					visited[nNode] = true; 
				}else if(!visited[nNode]){
					queue.offer(nNode);
				}
			}
			height += 1;
		}
 */
