import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N, M, V;

    static int[][] arr;
    static boolean[] check;

    static StringBuilder sb = new StringBuilder();

    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 정점 개수
        M = sc.nextInt(); // 간선 개수
        V = sc.nextInt(); // 정점 시작 번호

        arr = new int[N + 1][N + 1];
        check = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();

            arr[s][e] = arr[e][s] = 1;
        }

        dfs(V);
        sb.append("\n");

        check = new boolean[N + 1];
        bfs(V);

        System.out.println(sb);
    }

    static void dfs(int start) {
        check[start] = true;

        sb.append(start + " ");

        for (int i = 0; i <= N; i++) {
            if (arr[start][i] == 1 && !check[i]) {
                dfs(i);
            }
        }
    }

    static void bfs(int start) {
        q.add(start);
        check[start] = true;

        while (!q.isEmpty()) {
            start = q.poll();
            sb.append(start + " ");

            for (int i = 1; i <= N; i++) {
                if (arr[start][i] == 1 && !check[i]) {
                    q.add(i);
                    check[i] = true;
                }
            }
        }
    }
}