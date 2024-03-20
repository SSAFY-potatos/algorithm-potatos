import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj_20040_사이클게임 {
    static int N,M;
    static int[] parent;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        map = new int[M][2];

        for (int i = 0; i <M ; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        makeSet();

        int ans = 0;
        for (int i = 0; i < M; i++) {
            if(!union(map[i][0],map[i][1])){
                ans = i+1;
                break;
            }
        }
        System.out.println(ans);
    }
    static void makeSet(){
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }
    static int findSet(int a){
        if(parent[a] ==a) return a;
        return parent[a] = findSet(parent[a]);
    }

    static boolean union(int a,int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if( aRoot == bRoot ) return false;
        parent[bRoot] = aRoot;
        return true;
    }
}
