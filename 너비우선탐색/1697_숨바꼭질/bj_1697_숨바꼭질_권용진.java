import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);
        visited[N] = true;
        int time = 0;
        outerloop:
        while (!q.isEmpty()) {
            int size = q.size();
            for (int t = 0; t < size; t++) {
                int curr = q.poll();

                if (curr == K) {
                    break outerloop;
                }

                int[] temp = {curr + 1, curr - 1, curr * 2};
                for (int next : temp) {
                    if (next < 0 || next > 100000) continue;
                    if (visited[next]) continue;
                    q.add(next);
                    visited[next] = true;
                }
            }

            time++;
        }

        System.out.println(time);
    }
}