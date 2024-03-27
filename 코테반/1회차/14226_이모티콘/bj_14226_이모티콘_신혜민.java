import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        boolean[][] visit = new boolean [1001][1001];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 1, 1));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cnt == N) {
                System.out.println(now.time);
                break;
            }

             if (now.cnt < 0 || now.cnt > 1000 || now.sum > 1000 || visit[now.cnt][now.sum]) continue;

            visit[now.cnt][now.sum] = true;

            pq.offer(new Node(now.cnt + now.sum, now.time + 1, now.sum));
            pq.offer(new Node(now.cnt, now.time + 1, now.cnt));
            pq.offer(new Node(now.cnt - 1, now.time + 1, now.sum));
        }
    }
}

class Node implements Comparable<Node> {
    int cnt, time, sum;

    Node(int cnt, int time, int sum) {
        this.cnt = cnt;
        this.time = time;
        this.sum = sum;
    }

    @Override
    public int compareTo(Node o) {
        return time - o.time;
    }
}