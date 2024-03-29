import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj_15900_나무탈출_남경민 {
    static int N;
    static ArrayList<Integer>[] tree;
    static int ans=0;
    // 부모 노드 저장 추가
    static class Node{
        int x,d,parent;

        public Node(int x, int d,int parent) {
            this.x = x;
            this.d = d;
            this.parent = parent; //인접 노드인 부모 노드 표시
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];

        for (int i = 0; i < N+1; i++) {
            tree[i] = new ArrayList();
        }

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            tree[n1].add(n2);
            tree[n2].add(n1);
        }

        // 부모노드가 처음에는 없기때문에 -1에서 시작!
        DFS(new Node(1,0,-1));
        if(ans%2==0) System.out.println("No");
        else System.out.println("Yes");
    }

    // DFS
    static void DFS(Node n){
        //사이즈 1이고 그 하나가 부모노드라면 그 노드는 말단노드!!
        if(tree[n.x].size()==1 && tree[n.x].get(0) == n.parent){
            ans += n.d; //depth 더함
            return;
        }

        for(int num : tree[n.x]){
            if(num == n.parent) continue; //부모노드이면 이미 탐색했으므로 넘김
            DFS(new Node(num,n.d+1,n.x));
        }
    }
}