import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_9663_NQueen_남경민 {
    static int N;
    static int[] chess;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        chess = new int[N];


        dfs(0);
        System.out.println(ans);
    }

    // 같은 열이나 대각선 상에 퀸 놓여있으면 false
    private static boolean promising(int row) {
        for (int i = 0; i <row; i++) {
            if(chess[i] == chess[row] ||
                    row-i == Math.abs(chess[i]-chess[row])) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int row) {
        if(row ==N) {
            ans++;
            return;
        }

        for (int col = 0; col < N; col++) {
            // (row,col)에 체스를 놓아본다.
            chess[row] = col;

            // 유망하면 그 다음 행에 놓기(깊이 우선 탐색)
            // 그렇지 않으면 가지치기(현재 행의 다음 열로 바로 이동)
            if(promising(row)) {
                dfs(row+1);
            }
        }
    }
}
