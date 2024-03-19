import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    private int x;
    private int y;
    private int d;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Node(int x, int y, int d) {
        super();
        this.x = x;
        this.y = y;
        this.d = d;
    }
    public int getD() {
        return d;
    }
}
public class Main {

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    public static int bfs(Node n,int N,int M,int[][] graph, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(n);
        visited[n.getX()][n.getY()] = true;

        while(queue.size()!=0) {
            Node v = queue.poll();
            if(v.getX() == N-1 &&  v.getY()==M-1) {
                return v.getD();
            }

            for(int i=0;i<4;i++) {
                int xi = dx[i] + v.getX();
                int yi = dy[i] + v.getY();

                if(xi>=0 && xi<N && yi>=0&& yi<M) {
                    if(visited[xi][yi] == false && graph[xi][yi]==1) {
                        queue.add(new Node(xi, yi, v.getD()+1));
                        visited[xi][yi] = true;
                    }
                }
            }
        }
        return 0;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            String s = br.readLine();
            for(int j=0;j<M;j++) {
                graph[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }

        System.out.println(bfs(new Node(0,0,1), N, M, graph, visited));
    }
}
