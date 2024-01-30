import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_6593_상범빌딩_조성우 {

    public static int[] dx = { 1, 0, -1, 0, 0, 0 };
    public static int[] dy = { 0, -1, 0, 1, 0, 0 };
    public static int[] dz = { 0, 0, 0, 0, -1, 1 };

    public static Queue<Coord> q;
    public static int[][][] board;
    public static Coord start_coord;
    public static Coord end_coord;
    public static int L, R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            q = new LinkedList<Coord>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                return;
            }

            board = new int[L][R][C];

            for (int i = 0; i < L; i++) {

                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        char c = line.charAt(k);
                        if (c == '.') {
                            board[i][j][k] = 0;
                        } else if (c == '#') {
                            board[i][j][k] = -1;
                        } else if (c == 'S') {
                            board[i][j][k] = 0;
                            start_coord = new Coord(k, j, i);
                        } else if (c == 'E') {
                            board[i][j][k] = 0;
                            end_coord = new Coord(k, j, i);
                        }
                    }
                }
                br.readLine();
            }

            System.out.println(bfs(start_coord));
        }
    }

    public static String bfs(Coord start_coord) {
        q.add(start_coord);
        board[start_coord.getZ()][start_coord.getY()][start_coord.getX()] = 1;

        Coord cur_coord;
        while (!q.isEmpty()) {
            cur_coord = q.poll();
            int cur_x = cur_coord.getX();
            int cur_y = cur_coord.getY();
            int cur_z = cur_coord.getZ();

            if (cur_x == end_coord.getX() && cur_y == end_coord.getY() && cur_z == end_coord.getZ()) {
                return "Escaped in " + (board[cur_z][cur_y][cur_x] - 1) + " minute(s).";
            }

            int mv_x, mv_y, mv_z;
            for (int i = 0; i < 6; i++) {
                mv_x = cur_x + dx[i];
                mv_y = cur_y + dy[i];
                mv_z = cur_z + dz[i];

                if (mv_z < 0 || mv_z >= L || mv_y < 0 || mv_y >= R || mv_x < 0 || mv_x >= C) {
                    continue;
                }
                if (board[mv_z][mv_y][mv_x] > 0 || board[mv_z][mv_y][mv_x] == -1) {
                    continue;
                }

                q.add(new Coord(mv_x, mv_y, mv_z));
                board[mv_z][mv_y][mv_x] = board[cur_z][cur_y][cur_x] + 1;
            }
        }
        return "Trapped!";
    }

    public static class Coord {

        private int x;
        private int y;
        private int z;

        public Coord(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }
}
