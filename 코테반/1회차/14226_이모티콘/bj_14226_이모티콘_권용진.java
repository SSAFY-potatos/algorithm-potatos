import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[2 * S][2 * S];
        q.add(new int[]{1, 0});
        visited[1][0] = true;
        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] curr = q.poll();
                if (curr[0] == S) {
                    return time;
                }

                for (int i = 0; i < 3; i++) {
                    int next;
                    int nextClip;
                    if (i == 0) {
                        next = curr[0] + curr[1];
                        nextClip = curr[1];
                    } else if (i == 1) {
                        next = curr[0];
                        nextClip = curr[0];
                    } else {
                        next = curr[0] - 1;
                        nextClip = curr[1];
                    }

                    if (next < 0 || next >= 2 * S) continue;
                    if (visited[next][nextClip]) continue;

                    q.add(new int[] {next, nextClip});
                    visited[next][nextClip] = true;
                }
            }
            time++;
        }
        return 0;
    }
}