import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static char[][] graph;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = s.charAt(j);
            }
        }

        int answer = bfs();
        System.out.println((answer == -1) ? "KAKTUS" : answer);
    }


    static int bfs() {
        boolean[][] visited = new boolean[R][C];
        Queue<Node> q = new ArrayDeque<>();
        Queue<Node> wq = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (graph[i][j] == 'S') {
                    q.add(new Node(i, j));
                    visited[i][j] = true;
                } else if (graph[i][j] == '*') {
                    wq.add(new Node(i, j));
                }
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            //물 이동
            int wqSize = wq.size();
            for (int w = 0; w < wqSize; w++) {
                Node curr = wq.poll();

                for (int i = 0; i < 4; i++) {
                    int ny = curr.y + dy[i];
                    int nx = curr.x + dx[i];

                    if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                    if (graph[ny][nx] == '*') continue;
                    if (graph[ny][nx] == 'X' || graph[ny][nx] == 'D') continue;

                    wq.add(new Node(ny, nx));
                    graph[ny][nx] = '*';
                }
            }

            //고슴도치 이동
            int qSize = q.size();
            for (int s = 0; s < qSize; s++) {
                Node curr = q.poll();

                if (graph[curr.y][curr.x] == 'D') {
                    return time;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = curr.y + dy[i];
                    int nx = curr.x + dx[i];

                    if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
                    if (graph[ny][nx] == '*') continue;
                    if (graph[ny][nx] == 'X' || visited[ny][nx]) continue;

                    q.add(new Node(ny, nx));
                    visited[ny][nx] = true;
                }
            }

            time++;
        }

        return -1;
    }

    static class Node {
        int y;
        int x;
        Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}