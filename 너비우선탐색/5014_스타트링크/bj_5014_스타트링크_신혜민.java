import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int L, R, C;
    static int[] dl = { 0, 0, 0, 0, -1, 1 };
    static int[] dx = { 1, -1, 0, 0, 0, 0 };
    static int[] dy = { 0, 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0){
                break;
            }

            char[][][] arr = new char[L][R][C];
            boolean[][][] visit = new boolean[L][R][C];
            int check = -1;

            Queue<Exit> q = new LinkedList<>();

            int el = 0;
            int ex = 0;
            int ey = 0;

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < C; k++) {
                        arr[i][j][k] = str.charAt(k);

                        if (arr[i][j][k] == 'S') {
                            q.offer(new Exit(i, j, k, 0));
                            visit[i][j][k] = true;
                        }

                        if (arr[i][j][k] == 'E') {
                            el = i;
                            ex = j;
                            ey = k;
                        }
                    }
                }
                br.readLine();
            }

            while(!q.isEmpty()){
                Exit now = q.poll();

                if(now.l == el && now.x == ex && now.y == ey){
                    check = now.cnt;
                    break;
                }

                for(int i = 0; i < 6; i++){
                    int nl = now.l + dl[i];
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || ny < 0 || nl < 0 || nx >= R || ny >= C || nl >= L) continue;
                    if (arr[nl][nx][ny] == '#' || visit[nl][nx][ny]) continue;
                    q.offer(new Exit(nl, nx, ny, now.cnt+1));
                    visit[nl][nx][ny] = true;
                }
            }

            if(check == -1){
                sb.append("Trapped!").append("\n");
            }
            else{
                sb.append("Escaped in ").append(check).append(" minute(s).").append("\n");
            }
        }
        System.out.println(sb);
    }
}

class Exit {
    int l;
    int x;
    int y;
    int cnt;

    Exit(int l, int x, int y, int cnt) {
        this.l = l;
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
