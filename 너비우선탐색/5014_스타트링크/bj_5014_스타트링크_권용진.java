import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] d = {U, -D};

        boolean[] visited = new boolean[F + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        visited[S] = true;
        int time = 0;
        boolean flag = false;
        outerloop:
        while (!q.isEmpty()) {
            int size = q.size();
            for (int t = 0; t < size; t++) {
                int curr = q.poll();

                if (curr == G) {
                    flag = true;
                    break outerloop;
                }

                for (int i = 0; i < 2; i++) {
                    int next = curr + d[i];
                    if (next < 1 || next > F) continue;
                    if (visited[next]) continue;
                    q.add(next);
                    visited[next] = true;
                }
            }

            time++;
        }


        System.out.println(flag ? time : "use the stairs");
    }
}