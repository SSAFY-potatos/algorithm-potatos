import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int S;
    static int[] arr;
    static int count;
    static int sum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // logic
        dfs(0);
        System.out.println(count);
    }

    static void dfs(int depth) {
        if (depth == N) return;
        if (sum + arr[depth] == S) count++;

        dfs(depth + 1);

        sum += arr[depth];
        dfs(depth + 1);

        sum -= arr[depth];
    }
}