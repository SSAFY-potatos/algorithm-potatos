import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.PolicyQualifierInfo;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    private int r;
    private int c;

    public Node() {
        super();
    }
    public Node(int r, int c) {
        super();
        this.r = r;
        this.c = c;
    }
    public int getR() {
        return r;
    }
    public int getC() {
        return c;
    }
}

public class Main {
    static Queue<Node> queue = new LinkedList<>();
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] visited;
    static int R,C;
    static int[][] water,S;
    static char[][] graph;

    public static void WaterBFS() {
        while(!queue.isEmpty()) {
            Node n = queue.poll();
            visited[n.getR()][n.getC()] = true;

            for(int i=0;i<4;i++) {
                int xi = dx[i] + n.getR();
                int yi = dy[i] + n.getC();

                if(xi>=0 && xi<R && yi>=0 && yi<C) {
                    if(visited[xi][yi] == false && water[xi][yi] ==0) {
                        queue.add(new Node(xi,yi));
                        water[xi][yi] = water[n.getR()][n.getC()] + 1;
                        visited[xi][yi] = true;
                    }
                }
            }
        }
    }

    public static int SBFS(Node s,int R,int C) {
        queue.add(s);

        while(!queue.isEmpty()) {
            Node n = queue.poll();
            visited[n.getR()][n.getC()] = true;

//			if(graph[xi][yi] == 'D') {
//				return S[n.getR()][n.getC()];
//			}

            for(int i=0;i<4;i++) {
                int xi = dx[i] + n.getR();
                int yi = dy[i] + n.getC();

                if(xi>=0 && xi<R && yi>=0 && yi<C) {
                    if(visited[xi][yi] == false) {
                        if(graph[xi][yi] == 'D') {
                            return S[n.getR()][n.getC()];
                        } else if(graph[xi][yi] == '.') {
                            if((water[xi][yi]>0 && water[xi][yi] > S[n.getR()][n.getC()]+1) ||water[xi][yi] ==0) {
                                queue.add(new Node(xi,yi));
                                S[xi][yi] = S[n.getR()][n.getC()] + 1;
                                visited[xi][yi] = true;
                            }

                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        water = new int[R][C];
        S = new int[R][C];
        visited = new boolean[R][C];
        graph = new char[R][C];
        Node ns = new Node();
        Node nd = new Node();

        for(int i=0;i<R;i++) {
            String s = br.readLine();
            for(int j=0;j<C;j++) {
                graph[i][j] = s.charAt(j);
                if(s.charAt(j)=='D') {
                    water[i][j] = -1;
                }else if(s.charAt(j)=='X') {
                    water[i][j] = -1;
                    S[i][j] = -1;
                }else if(s.charAt(j)=='*') {
                    S[i][j] = -1;
                    queue.add(new Node(i,j));
                    water[i][j] = 1;
                }else if(s.charAt(j)=='S') {
                    ns = new Node(i,j);
                    S[i][j] = 1;
                }
            }
        }

        WaterBFS();

        visited = new boolean[R][C];
        int answer = SBFS(ns,R,C);

        if(answer <0) {
            System.out.println("KAKTUS");
        }else {
            System.out.println(answer);
        }

    }

}
