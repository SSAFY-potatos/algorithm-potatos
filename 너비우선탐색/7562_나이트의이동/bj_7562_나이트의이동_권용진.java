import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int L;
    static int endY;
    static int endX;
    static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            L = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            endY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());

            System.out.println(bfs(startY, startX));
        }
    }


    static int bfs(int r, int c) {
        boolean[][] visited = new boolean[L][L];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r, c));
        visited[r][c] = true;
        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int t = 0; t < size; t++) {
                Node curr = q.poll();

                if (curr.y == endY && curr.x == endX) {
                    return time;
                }

                for (int i = 0; i < 8; i++) {
                    int ny = curr.y + dy[i];
                    int nx = curr.x + dx[i];

                    if (ny < 0 || ny >= L || nx < 0 || nx >= L) continue;
                    if (visited[ny][nx]) continue;
                    q.add(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }

            time++;
        }

        return 0;
    }


    static class Node {
        int y;
        int x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}