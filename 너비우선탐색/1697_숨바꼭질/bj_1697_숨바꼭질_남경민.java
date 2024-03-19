import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int x;
    int d;

    public Node(int x, int d) {
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
    static int[] dx = {-1,1,2};
    static final int MAX_VALUE = 100000;
    static int bfs(Node n,int K,boolean[] visited){
        Queue<Node> queue = new LinkedList<>();
        queue.add(n);
        visited[n.getX()] = true;

        while(queue.size()!=0){
            Node v = queue.poll();
            if(v.getX() == K){
                return v.getD();
            }

            for(int i=0;i<3;i++){
                int xi = (dx[i] == 2)? v.getX()*dx[i] : v.getX()+dx[i];
                if(xi>=0 && xi<=MAX_VALUE && visited[xi] == false){
                    queue.add(new Node(xi,v.getD()+1));
                    visited[xi] = true;
                }
            }
        }
        return n.getD();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[MAX_VALUE+1];

        System.out.println(bfs(new Node(N,0),K,visited));
    }
}