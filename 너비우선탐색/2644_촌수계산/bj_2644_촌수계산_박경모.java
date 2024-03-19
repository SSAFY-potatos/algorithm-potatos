import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] line = br.readLine().split(" ");
        int start = Integer.parseInt(line[0]);
        int end = Integer.parseInt(line[1]);
        int M = Integer.parseInt(br.readLine());

        LinkedList<Integer>[] adjList = new LinkedList[N+1];
        for(int i=0; i<=N; i++) {
            adjList[i] = new LinkedList<Integer>();
        }

        for(int i=0; i<M; i++) {
            line = br.readLine().split(" ");
            int v1 = Integer.parseInt(line[0]);
            int v2 = Integer.parseInt(line[1]);
            adjList[v1].add(v2);
            adjList[v2].add(v1);
        }

        //System.out.println(Arrays.deepToString(adjList));
        int[] visited = new int[N+1];
        Arrays.fill(visited, -1);
        bfs_list(start, end, adjList, visited);
        System.out.println(visited[end]);
    }

    public static void bfs_list(int v, int e, LinkedList<Integer>[] adjList, int[] visited) {
        Queue<Integer> queue = new LinkedList<Integer>();
        visited[v] = 0;
        queue.add(v);

        while (queue.size() != 0) {
            v = queue.poll();
            //System.out.print(v + " ");
            Iterator<Integer> iter = adjList[v].listIterator();
            while (iter.hasNext()) {
                int w = iter.next();
                if (visited[w] == -1) {
                    visited[w] = visited[v]+1;
                    queue.add(w);
                }
            }
        }
    }
}