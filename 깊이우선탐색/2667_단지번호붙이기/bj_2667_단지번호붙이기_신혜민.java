import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] visit;
    static int check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && !visit[i][j]) {
                    check = 0;
                    dfs(i, j);
                    list.add(check);
                }
            }
        }

        list.sort(null);

        StringBuffer sb = new StringBuffer();

        sb.append(list.size());

        for (int i = 0; i < list.size(); i++) {
            sb.append("\n").append(list.get(i));
        }

        System.out.println(sb);
    }

    static void dfs(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N || arr[x][y] == 0 || visit[x][y]) {
            return;
        }

        visit[x][y] = true;
        check++;

        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}