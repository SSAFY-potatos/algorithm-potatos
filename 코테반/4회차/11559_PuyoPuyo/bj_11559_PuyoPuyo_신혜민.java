import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static char[][] arr;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] visit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[12][6];

        for(int i = 0; i < 12; i++) {
            String str = br.readLine();
            for(int j = 0; j < 6; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        int result = 0;

        while(true){
            boolean flag = check();
            if(!flag) break;

            result++;
            down();
        }

        System.out.println(result);
    }

    static boolean check() {
        visit = new boolean[12][6];
        boolean flag = false;

        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(!visit[i][j] && arr[i][j] != '.'){
                    visit[i][j] = true;
                    if(bfs(i, j)) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    static boolean bfs(int x, int y) {
        List<Node> list = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, y));
        char C = arr[x][y];

        while(!q.isEmpty()) {
            Node now = q.poll();
            list.add(now);

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;
                if(visit[nx][ny] || arr[nx][ny] != C) continue;

                q.offer(new Node(nx, ny));
                visit[nx][ny] = true;
            }
        }

        if(list.size() >= 4){
            for(Node now : list){
                arr[now.x][now.y] = '.';
            }

            return true;
        }
        return false;
    }

    static void down() {
        for(int i = 0; i < 6; i++) {
            int bottom = 11;

            for(int j = 11; j >= 0; j--){
                if(arr[j][i] != '.'){
                    if(bottom != j){
                        arr[bottom][i] = arr[j][i];
                        arr[j][i] = '.';
                    }
                    bottom--;
                }
            }

        }
    }
}

class Node{
    int x, y;

    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}