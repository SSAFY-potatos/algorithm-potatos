import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dh = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (L + R + C == 0) break;

            boolean[][][] visited = new boolean[L][R][C];
            char[][][] graph = new char[L][R][C ];
            Queue<Node> q = new ArrayDeque<>();

            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String s = br.readLine();

                    for (int c = 0; c < C; c++) {
                        graph[l][r][c] = s.charAt(c);
                        if (graph[l][r][c] == 'S') {
                            q.add(new Node(l, r, c));
                            visited[l][r][c] = true;
                        }
                    }
                }

                br.readLine();
            }


            int time = 0;
            boolean flag = false;
            outerloop:
            while (!q.isEmpty()) {
                int size = q.size();
                for (int t = 0; t < size; t++) {
                    Node curr = q.poll();

                    if (graph[curr.h][curr.y][curr.x] == 'E') {
                        flag = true;
                        break outerloop;
                    }

                    for (int i = 0; i < 6; i++) {
                        int nh = curr.h + dh[i];
                        int ny = curr.y + dy[i];
                        int nx = curr.x + dx[i];

                        if (nh < 0 || nh >= L || ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                        if (visited[nh][ny][nx] || graph[nh][ny][nx] == '#') continue;
                        q.add(new Node(nh, ny, nx));
                        visited[nh][ny][nx] = true;
                    }
                }

                time++;
            }

            if (flag) {
                System.out.println("Escaped in " + time + " minute(s).");
            } else {
                System.out.println("Trapped!");
            }
        }
    }

    static class Node {
        int h;
        int y;
        int x;

        Node(int h, int y, int x) {
            this.h = h;
            this.y = y;
            this.x = x;
        }
    }
}