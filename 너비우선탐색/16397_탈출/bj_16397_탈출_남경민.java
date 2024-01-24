import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Escape{
    private int node;
    private int cnt;

    public Escape(int node, int cnt) {
        this.node = node;
        this.cnt = cnt;
    }

    public int getNode() {
        return node;
    }

    public int getCnt() {
        return cnt;
    }
}

public class Main {
    static final int MAX_NUM = 100000;
    static int bfs(Escape n, int T,int G, boolean[] visited){

        Queue<Escape> queue = new LinkedList<>();
        queue.add(n);
        visited[n.getNode()]=true;

        while(queue.size()!=0){
            Escape v = queue.poll();
            if(v.getNode() == G){
                return v.getCnt();
            }

            if(v.getCnt() + 1 > T) continue;

            // A버튼
            if(v.getNode()+1 < MAX_NUM && !visited[v.getNode()+1]){
                queue.add(new Escape(v.getNode()+1,v.getCnt()+1));
                visited[v.getNode()+1] = true;
            }

            // B버튼
            if(v.getNode()*2>0 && v.getNode()*2 < MAX_NUM){
                int dN = ((v.getNode() * 2) - (int) Math.pow(10, (String.valueOf(v.getNode() * 2).length()) - 1));
                if (dN >= 0 && dN < MAX_NUM && !visited[dN]) {
                    queue.add(new Escape(dN, v.getCnt() + 1));
                    visited[dN] = true;
                }
            }

        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
//        int N = 712;
//        //거듭제곱 구하는 방법
//        System.out.println(N - (int)Math.pow(10,(String.valueOf(N).length())-1));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[MAX_NUM];

        int answer = bfs(new Escape(N,0),T,G,visited);

        if(answer <0){
            System.out.println("ANG");
        }else{
            System.out.println(answer);
        }

    }
}
