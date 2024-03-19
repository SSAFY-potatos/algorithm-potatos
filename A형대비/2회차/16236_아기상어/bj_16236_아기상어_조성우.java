import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_16236_아기상어_조성우 {
    static int N;
    static int[] fishNumBySize = new int[7];// key: 물고기크기, val: 보드상 남은 마릿수
    static int[][] board;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };
    static boolean[][] visited;
    static List<Coord> targetFishes = new ArrayList<>();
    static int isEdible = -1;
    static int res;
    static SharkState shark = new SharkState(2, 0);
    static Queue<Coord> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int state = Integer.parseInt(st.nextToken());
                if (state > 0 && state <= 6) {
                    fishNumBySize[state] += 1;
                } else if (state == 9) {
                    Coord start_coord = new Coord(j, i, 0);
                    q.add(start_coord);
                    state = 0;
                }
                board[i][j] = state;
            }
        }

        while (!q.isEmpty()) {
            Coord cur_coord = q.poll();
            int cur_x = cur_coord.x;
            int cur_y = cur_coord.y;
            int cur_sec = cur_coord.sec;

            // 먹거나 움직일 여지가 없는지 판별, 탈출 --
            boolean anyEdibleOrPassableFish = false;
            for (int i = 1; i < 7; i++) {
                if (fishNumBySize[i] > 0) {
                    if (shark.size >= i) {
                        anyEdibleOrPassableFish = true;
                        break;
                    }
                }
            }
            if (!anyEdibleOrPassableFish) {
                break;
            }
            // --

            // isEdible 유효확인 및 sec과의 비교에 의한 다음 큐 수행 or 식사 수행 결정
            if (isEdible != -1 && isEdible != cur_sec) {
                // fishList 최우선 생선찾기
                Coord priorFish = new Coord(targetFishes.get(0).x, targetFishes.get(0).y, targetFishes.get(0).sec);
                for (int i = 1; i < targetFishes.size(); i++) {
                    if (priorFish.y > targetFishes.get(i).y) {
                        priorFish.x = targetFishes.get(i).x;
                        priorFish.y = targetFishes.get(i).y;
                    } else if (priorFish.y == targetFishes.get(i).y) {
                        if (priorFish.x > targetFishes.get(i).x) {
                            priorFish.x = targetFishes.get(i).x;
                            priorFish.y = targetFishes.get(i).y;
                        }
                    }
                }

                // 초기화
                shark.eat();
                fishNumBySize[board[priorFish.y][priorFish.x]]--;
                res += priorFish.sec;
                board[priorFish.y][priorFish.x] = 0;

                visited = new boolean[N][N];
                visited[priorFish.y][priorFish.x] = true;
                targetFishes.clear();

                isEdible = -1;
                q.clear();
                q.add(new Coord(priorFish.x, priorFish.y, 0));
                continue;
            }

            int mv_x, mv_y, mv_sec;
            for (int idxDir = 0; idxDir < 4; idxDir++) {
                mv_x = cur_x + dx[idxDir];
                mv_y = cur_y + dy[idxDir];
                mv_sec = cur_sec + 1;

                // 지나갈 수 없는 경우
                if (mv_x < 0 || mv_x >= N || mv_y < 0 || mv_y >= N || visited[mv_y][mv_x]) {
                    continue;
                }
                if (board[mv_y][mv_x] > shark.size) {
                    continue;
                }

                q.add(new Coord(mv_x, mv_y, mv_sec));
                visited[mv_y][mv_x] = true;

                // 먹이판별
                if (board[mv_y][mv_x] < shark.size && board[mv_y][mv_x] != 0) {
                    if (isEdible == -1) {
                        isEdible = cur_sec;
                    }
                    targetFishes.add(new Coord(mv_x, mv_y, mv_sec));
                }
            }

        }
        System.out.print(res);

    }

    static class SharkState {
        int size;
        int ate;

        public SharkState(int size, int ate) {
            this.size = size;
            this.ate = ate;
        }

        public void eat() {
            this.ate++;
            if (this.ate == this.size) {
                this.size++;
                this.ate = 0;
            }
        }
    }

    static class Coord {
        int x, y;
        int sec;
        int step;

        public Coord(int x, int y, int sec) {
            this.x = x;
            this.y = y;
            this.sec = sec;
        }
    }
}