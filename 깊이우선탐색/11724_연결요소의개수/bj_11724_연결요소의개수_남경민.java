import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int cnt =0;
    public static void dfs(int n, ArrayList<Integer>[] graph, boolean[] visited) {
        visited[n] = true;
        for(Integer v:graph[n]) {
            if(visited[v] == false) {
                dfs(v, graph, visited);
                visited[v] = true;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        boolean[] visited = new boolean[N+1];
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

        for(int i=0;i<=N;i++) {
            Collections.sort(graph[i]);
        }

        for(int i=1;i<=N;i++) {
            if(visited[i] ==false) {
                cnt++;
                dfs(i, graph, visited);
            }

        }

        System.out.println(cnt);
    }
}
