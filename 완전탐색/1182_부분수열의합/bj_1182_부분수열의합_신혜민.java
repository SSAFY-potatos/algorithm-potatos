import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분수열의합 {
    static int N, S, check = 0;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        if(S == 0){
            check -= 1;
        }

        System.out.println(check);
    }

    static void dfs(int idx, int sum){
        if(idx == N){
            if(sum == S){
                check++;
            }
            return;
        }

        System.out.println("idx:" + idx + " sum:" + sum);

        dfs(idx+1, sum + arr[idx]);
        dfs(idx+1, sum);
    }
}
