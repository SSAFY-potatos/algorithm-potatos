import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_3055_탈출_조성우 {

    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { -1, 0, 1, 0 };
    public static char[][] board;
    public static int R, C;
    public static Coord start_coord;
    public static Coord end_coord;
    public static Queue<Coord> q = new LinkedList<>();
    public static Queue<Coord> water_q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'S') {
                    start_coord = new Coord(j, i);
                } else if (board[i][j] == 'D') {
                    end_coord = new Coord(j, i);
                } else if (board[i][j] == '*') {
                    water_q.add(new Coord(j, i));
                }
            }
        }

        int res = bfs();
        System.out.println(res > 0 ? res : "KAKTUS");
    }

    public static int bfs() {

        boolean[][] visited = new boolean[R][C];
        q.add(start_coord);
        visited[start_coord.y][start_coord.x] = true;

        int dep = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            int water_size = water_q.size();

            for (int i = 0; i < water_size; i++) {
                Coord cur_water = water_q.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int mv_waterX = cur_water.x + dx[dir];
                    int mv_waterY = cur_water.y + dy[dir];
                    if (mv_waterX >= 0 && mv_waterX < C && mv_waterY >= 0 && mv_waterY < R
                            && board[mv_waterY][mv_waterX] == '.') {
                        board[mv_waterY][mv_waterX] = '*';
                        water_q.add(new Coord(mv_waterX, mv_waterY));
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                Coord cur_coord = q.poll();
                if (cur_coord.x == end_coord.x && cur_coord.y == end_coord.y) {
                    return dep;
                }
                for (int dir = 0; dir < 4; dir++) {
                    int mv_x = cur_coord.x + dx[dir];
                    int mv_y = cur_coord.y + dy[dir];
                    if (mv_x >= 0 && mv_x < C && mv_y >= 0 && mv_y < R && !visited[mv_y][mv_x]
                            && (board[mv_y][mv_x] == '.'
                                    || board[mv_y][mv_x] == 'D')) {
                        visited[mv_y][mv_x] = true;
                        q.add(new Coord(mv_x, mv_y));
                    }
                }
            }
            dep++;
        }
        return -1;
    }

    public static class Coord {
        int x, y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}