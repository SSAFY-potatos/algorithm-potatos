import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] table;
    static int cnt;
    static List<Integer> answers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        table = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                table[i][j] = s.charAt(j) - '0';
            }
        }

        answers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (table[i][j] != 1) continue;
                cnt = 0;
                dfs(i, j);
                answers.add(cnt);
            }
        }

        Collections.sort(answers);
        System.out.println(answers.size());
        for (Integer answer : answers) {
            System.out.println(answer);
        }
    }

    static void dfs(int y, int x) {
        if (y >= N || y < 0 || x >= N || x < 0) return;
        if (table[y][x] != 1)  return;

        cnt++;
        table[y][x] = 2;
        dfs(y + 1, x);
        dfs(y - 1, x);
        dfs(y, x + 1);
        dfs(y, x - 1);
    }
}