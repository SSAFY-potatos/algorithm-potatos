import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] arr;
    static boolean[][] visit;
    static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];
        visit = new boolean[R][C];

        Queue<E> q = new LinkedList<>();
        Queue<E> water = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);

                if (arr[i][j] == 'S') {
                    q.add(new E(i, j, 0));
                    visit[i][j] = true;
                }

                if (arr[i][j] == '*') {
                    water.add(new E(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            int size = water.size();
            for(int i = 0; i < size; i++){
                E w = water.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = w.x + dx[j];
                    int ny = w.y + dy[j];
    
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                        continue;

                    if (arr[nx][ny] == '.'){
                        water.offer(new E(nx, ny));
                        arr[nx][ny] = '*';
                    } 
                }
            }

            size = q.size();

            for(int i = 0; i < size; i++){
                E now = q.poll();

                if (arr[now.x][now.y] == 'D') {
                    
                    System.out.println(now.cnt);
                    System.exit(0);
                }

                for (int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                        continue;
                    if (arr[nx][ny] == 'X' || visit[nx][ny] || arr[nx][ny] == '*')
                        continue;
                    q.offer(new E(nx, ny, now.cnt + 1));
                    visit[nx][ny] = true;
                }
            }
        }
        System.out.println("KAKTUS");
    }
}

class E {
    int x;
    int y;
    int cnt;

    E(int x, int y) {
        this.x = x;
        this.y = y;
    }

    E(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}