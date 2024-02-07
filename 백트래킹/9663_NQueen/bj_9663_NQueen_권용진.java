import java.util.Scanner;

public class Main {
    static int N;
    static int[][] table;
    static int answer;
    static int[] dy = {1, 1, 1};
    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        table = new int[N][N];

        sol(0, 0);
        System.out.println(answer);
    }

    static void sol(int depth, int y) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int x = 0; x < N; x++) {
            if (table[y][x] != 0) continue;
            checking(y, x, true);
            sol(depth + 1, y + 1);
            checking(y, x, false);
        }
    }

    static void checking(int y, int x, boolean flag) {
        if (flag) table[y][x]++;
        else table[y][x]--;

        for (int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            while (true) {
                if (ny < 0 || ny >= N || nx < 0 || nx >= N) break;

                if (flag) table[ny][nx]++;
                else table[ny][nx]--;
                ny = ny + dy[i];
                nx = nx + dx[i];
            }
        }
    }
}