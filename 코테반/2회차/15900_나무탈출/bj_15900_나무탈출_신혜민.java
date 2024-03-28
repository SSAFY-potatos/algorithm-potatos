import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, result = 0;
    static List<Integer>[] list;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        visit = new boolean[N+1];

        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        visit[1] = true;
        dfs(1, 0);

        if(result % 2 == 0){
            System.out.println("No");
        }
        else {
            System.out.println("Yes");
        }
    }

    static void dfs(int x, int cnt) {
        for(int next : list[x]){
            if(!visit[next]) {
                visit[next] = true;
                dfs(next, cnt+1);
            }
        }

        if(list[x].size() == 1 && x != 1) {
            result += cnt;
        }
    }
}