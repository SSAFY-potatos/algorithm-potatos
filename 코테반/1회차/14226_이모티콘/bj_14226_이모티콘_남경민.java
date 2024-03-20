import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
public class bj_14226_이모티콘_남경민 {
    static int S;
    static class Node{
        int n,board,cnt;

        public Node(int n, int board, int cnt) {
            this.n = n;
            this.board = board;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());
        System.out.println(BFS());
    }

    static int BFS(){
        Queue<Node> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[2001][2001];
        queue.add(new Node(1,0,0));
        visited[1][0] = visited[0][0] = true;

        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node.n == S) return node.cnt;

            int[] dn = {node.n+node.board,node.n,node.n-1};
            int[] dBoard = {node.board,node.n,node.board};

            for (int i = 0; i < 3; i++) {
                if(visited[dn[i]][dBoard[i]]) continue;

                // 2번 연산 조건
                if(i==0 && (dn[i] < 0 || dn[i]>S)) continue;

                // 3번 연산 조건
                if(i==2 && dn[i]<1) continue;

                visited[dn[i]][dBoard[i]] = true;
                queue.add(new Node(dn[i],dBoard[i],node.cnt+1));
            }
        }
        return -1;
    }
}
