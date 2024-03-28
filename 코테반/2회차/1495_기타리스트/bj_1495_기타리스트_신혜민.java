import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S, M, V[]; // 곡의 개수, 시작 볼륨, 최대 볼륨수, 볼륨리스트

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        V = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N+1][M+1];
        dp[0][S] = true;

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= M; j++) {
                if(dp[i-1][j]) {
                    int v1 = j + V[i-1];
                    int v2 = j - V[i-1];

                    if(v1 <= M) dp[i][v1] = true;
                    if(v2 >= 0) dp[i][v2] = true;
                }
            }
        }

        int result = -1;

        for(int i = M; i >= 0; i--) {
            if(dp[N][i]) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
    
}