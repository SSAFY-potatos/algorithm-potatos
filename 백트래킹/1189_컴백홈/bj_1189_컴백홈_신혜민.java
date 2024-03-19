import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R,C,K;
    static int startX, startY, endX, endY;
    static char[][] map;
    static boolean[][] visit;
    static int check = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());;

        startX = R-1;
        startY = 0;

        endX = 0;
        endY = C-1;

        map = new char[R][C];
        visit = new boolean[R][C];

        for(int i = 0; i <R; i++){
            String str = br.readLine();
            for(int j = 0; j<C; j++){
                map[i][j] = str.charAt(j);
            }
        }

        back(startX, startY, 1);

        System.out.println(check);

    }
    static void back(int x, int y, int cnt){
        if(x < 0 || y < 0 || x >= R || y >= C || map[x][y] == 'T' || visit[x][y]){
            return;
        }


        if(x == endX && y == endY){
            if(cnt == K){
                check++;
            }
            return;
        }


        visit[x][y] = true;
        back(x+1, y, cnt+1);
        back(x-1, y, cnt+1);
        back(x, y+1, cnt+1);
        back(x, y-1, cnt+1);
        visit[x][y] = false;
    }
}