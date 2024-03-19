import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Person{
    private int x;
    private int d;

    public Person(int x, int d) {
        super();
        this.x = x;
        this.d = d;
    }

    public int getX() {
        return x;
    }

    public int getD() {
        return d;
    }
}
public class Main {

    public static int bfs(Person p,int n,ArrayList<Integer>[] graph,boolean[] visited) {
        Queue<Person> queue = new LinkedList<Person>();
        int cnt =-1;
        visited[p.getX()] = true;
        queue.add(p);

        while(queue.size()!=0) {
            Person v= queue.poll();
            if(v.getX() == n) { //촌수 계산 완료
                cnt = v.getD();
                return cnt;

            }
            for(Integer pi : graph[v.getX()]) {
                if(visited[pi]==false) {
                    queue.add(new Person(pi, v.getD()+1));
                    visited[pi] = true;
                }
            }
        }
        return cnt;

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for(int i=0;i<=N;i++) {
            graph[i] = new ArrayList<Integer>();
        }

        boolean[] visited = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

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

        Person person1 = new Person(p1, 0);

        System.out.println(bfs(person1,p2, graph, visited));

    }
}
