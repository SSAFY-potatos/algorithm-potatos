import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, arr[][];
    static int min = Integer.MAX_VALUE;
    static boolean[][] visit;
    static List<Node> list;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1}; 
    static int max = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        list = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] == 2){
                    list.add(new Node(i, j));
                }
            }
        }
        wall(0);
        System.out.println(max);
    }

    static void wall(int cnt){
        if(cnt == 3){
            bfs();
            return;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(arr[i][j] == 0){
                    arr[i][j] = 1;
                    wall(cnt+1);
                    arr[i][j] = 0;
                }
            }
        }   
    }

    static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        visit = new boolean[N][M];
        int[][] map = new int[N][M];

        for(int i = 0; i < list.size(); i++){
            q.offer(list.get(i));
            visit[list.get(i).x][list.get(i).y] = true;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = arr[i][j];
            }
        }

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] || map[nx][ny] == 1) continue;

                visit[nx][ny] = true;
                map[nx][ny] = 2;
                q.offer(new Node(nx, ny));
            }
        }
        safeMax(map);
    }

    static void safeMax(int[][] map){
        int cnt = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0){
                    cnt++;
                }
            }
        }

        max = Math.max(cnt, max);
    }
}

class Node{
    int x, y;

    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}