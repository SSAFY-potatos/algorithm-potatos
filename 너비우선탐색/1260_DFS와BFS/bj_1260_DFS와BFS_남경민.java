import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void bfs(int v,ArrayList<Integer>[] graph,boolean[] visited) {
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[v] = true;
        queue.add(v);

        while(queue.size()!=0) {
            v = queue.poll();
            System.out.print(v+" ");

            for(Integer n:graph[v]) {
                if(visited[n] ==false){
                    queue.add(n);
                    visited[n]=true;
                }
            }
        }
    }

    public static void dfs(int v,ArrayList<Integer>[] graph,boolean[] visited) {
        visited[v] = true;
        System.out.print(v+" ");
        for(Integer n:graph[v]) {
            if(visited[n]==false) {
//				visited[n] = true;
                dfs(n, graph, visited);
            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        boolean visited[] = new boolean[N+1];
        boolean visited2[] = new boolean[N+1];

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        // 오름차순 정렬
        for(int i=0;i<=N;i++) {
            Collections.sort(graph[i]);
        }

        dfs(V, graph, visited2);
        System.out.println();
        bfs(V, graph, visited);


    }

}
